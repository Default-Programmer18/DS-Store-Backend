package com.commerce.newbies.ecommerceproject.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.commerce.newbies.ecommerceproject.constants.RazorpayConstants;
import com.commerce.newbies.ecommerceproject.entities.OrdersEcom;
import com.commerce.newbies.ecommerceproject.entities.Users;
import com.commerce.newbies.ecommerceproject.repository.OrderRepository;
import com.commerce.newbies.ecommerceproject.repository.UserRepository;
import com.commerce.newbies.ecommerceproject.services.RazorpayServices;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;

@RestController

	@RequestMapping("/order/v1")
	public class RazorpayController {

		@Autowired
		private RazorpayServices razorpayOrderService;
		@Autowired
		private OrderRepository orderRepo;
		@Autowired
		private UserRepository userRepo;
		
		@PostMapping("/createOrderId")
		public ResponseEntity<OrdersEcom> createOrder(@RequestBody OrdersEcom ordersend)
		{
			String orderId=null;
		    try {
		        RazorpayClient razorpay = new RazorpayClient(RazorpayConstants.rzp_key_id,RazorpayConstants.rzp_key_secret);
		        org.json.JSONObject orderRequest = new org.json.JSONObject();
		        
		        orderRequest.put("amount", (ordersend.getAmount()+40)*100); // amount in the smallest currency unit
		        orderRequest.put("currency", "INR");
//		        orderRequest.put("receipt", "order_rcptid_11");
		        

		        com.razorpay.Order order = razorpay.Orders.create(orderRequest);
		        orderId = order.get("id");
		        System.out.println(orderId);
		    } catch (RazorpayException e) {
		        // Handle Exception
		        System.out.println(e.getMessage());
		    }
//		    ordersend.setId(45);
		    ordersend.setOrderId(orderId);
		    ordersend.setStatus("created");
		    System.out.println( ordersend);
		    Users  u=userRepo.findById(ordersend.getUser().getId()).orElse(null);
		    
		    			orderRepo.save(ordersend);
		    			ordersend.setUser(u);
		   
		    if(orderId!=null)
		    { 
		    	return new ResponseEntity(ordersend,HttpStatusCode.valueOf(201));
		   
		    }
		    return new ResponseEntity(HttpStatusCode.valueOf(400));
		  
		}
		
		@PostMapping("/orderPaymentStatus")
		public String orderSetPaymentStaus(@RequestBody OrdersEcom ordersend)
		{
			OrdersEcom  o=orderRepo.findByOrderId(ordersend.getOrderId()).orElse(null);
			o.setPaymentId(ordersend.getPaymentId());
			
			o.setOrderDate(LocalDate.now());
			o.setStatus("paid");
			orderRepo.save(o);
			System.out.println(o.toString());
			
			return  "Successful";
		}
		
}

