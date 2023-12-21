package com.commerce.newbies.ecommerceproject.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.commerce.newbies.ecommerceproject.entities.Category;
import com.commerce.newbies.ecommerceproject.entities.OrdersEcom;
import com.commerce.newbies.ecommerceproject.entities.Product;
import com.commerce.newbies.ecommerceproject.entities.Users;
import com.commerce.newbies.ecommerceproject.repository.OrderRepository;

@RestController
@RequestMapping("/order/v1")
public class OrderController {
	
	@Autowired
	private OrderRepository orderRepo; 
	
	@GetMapping("/orderInfo")
	public ResponseEntity<List<OrdersEcom>> retrieveOrderByUserId(@RequestParam long userId)
	{
		List<OrdersEcom> orders=orderRepo.getOrderByUserId(userId);
		if(orders.size()==0)
		return new ResponseEntity(null,HttpStatusCode.valueOf(400));	
		return new ResponseEntity(orders,HttpStatusCode.valueOf(201));
	}
	@PostMapping("/checkCancelOrderPossible")
	public String checkCancelOrderPossibleFunc(@RequestParam String orderId)
	{
		OrdersEcom  o=orderRepo.findByOrderId(orderId).orElse(null);
		  DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		  LocalDate now = LocalDate.now(); 
		  LocalDate now2 =o.getOrderDate();
		if(now2.compareTo(now)  <=7 )
		{
			o.setStatus("Refunded");
			orderRepo.save(o);
			return "successful";
		}
		
		
//		ArrayList<Product> a=new ArrayList<Product>(o.getProducts());
//		for(int i=0;i<a.size();i++)
//		{
//			
//			if(a.get(i).getId()==productId)
//				{
//				
////				//Date d=o.getOrderDate()+7*3600*24*1000 ;
////				if(o.getOrderDate().compareTo(Date.now())  <=7 )
//				LocalDate today=LocalDate.now();
//				LocalDate start=o.getOrderDate();
//				LocalDate end=o.getOrderDate().plusDays(7);
//				if(today.isEqual(start) ==true||today.isEqual(end) ==true 
//		              || (today.isBefore(start)==false && today.isAfter(end)==false))
//				{
//					o.setAmount((long)(o.getAmount()-a.get(i).getProductPrice()));
//					o.getProducts().remove(i);
//					System.out.println( "size:   "+o.getProducts().size());
//					orderRepo.save(o);
//					break;
//				}
//				
//				
//				
//				}
		
		
		
				
		
	
		
		
		return "Refund Failed.";
	}

	
}
