package com.commerce.newbies.ecommerceproject.entities;



import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;


@Entity
public class OrdersEcom {
	
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String orderId;
	private long amount;
	
	@ManyToOne
	private Users user;
	private long deliveryCharges;
	private String status;
	@ManyToMany
	@JoinColumn(name ="productList")
	private List<Product> products;
	private String paymentId;
	private LocalDate orderDate;
	
	
	
	

	




	public LocalDate getOrderDate() {
		return orderDate;
	}




	public OrdersEcom(String orderId, String paymentId) {
		super();
		this.orderId = orderId;
		this.paymentId = paymentId;
	}




	public OrdersEcom(long id, String orderId, long amount, Users user, long deliveryCharges, String status,
			List<Product> products, String paymentId) {
		super();
		this.id = id;
		this.orderId = orderId;
		this.amount = amount;
		this.user = user;
		this.deliveryCharges = deliveryCharges;
		this.status = status;
		this.products = products;
		this.paymentId = paymentId;
	}




	public OrdersEcom(long id, String orderId, long amount, Users user, long deliveryCharges, String status,
			List<Product> products) {
		super();
		this.id = id;
		this.orderId = orderId;
		this.amount = amount;
		this.user = user;
		this.deliveryCharges = deliveryCharges;
		this.status = status;
		this.products = products;
	}

	
	

	public OrdersEcom(String orderId, long amount, Users user, long deliveryCharges, String status,
			List<Product> products) {
		super();
		this.orderId = orderId;
		this.amount = amount;
		this.user = user;
		this.deliveryCharges = deliveryCharges;
		this.status = status;
		this.products = products;
	}






	public OrdersEcom(String orderId, long amount, Users user, long deliveryCharges, String status) {
		super();
		this.orderId = orderId;
		this.amount = amount;
		this.user = user;
		this.deliveryCharges = deliveryCharges;
		this.status = status;
	}

	public OrdersEcom() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	public OrdersEcom(String orderId, String status, String paymentId, LocalDate orderDate) {
		super();
		this.orderId = orderId;
		this.status = status;
		this.paymentId = paymentId;
		this.orderDate = orderDate;
	}




	public OrdersEcom(long id, String orderId, long amount, Users user, long deliveryCharges, String status,
			List<Product> products, String paymentId, LocalDate orderDate) {
		super();
		this.id = id;
		this.orderId = orderId;
		this.amount = amount;
		this.user = user;
		this.deliveryCharges = deliveryCharges;
		this.status = status;
		this.products = products;
		this.paymentId = paymentId;
		this.orderDate = orderDate;
	}




	public String getPaymentId() {
		return paymentId;
	}




	public LocalDate LocalDate() {
		return orderDate;
	}




	public void setOrderDate(LocalDate orderDate) {
		this.orderDate = orderDate;
	}




	public void setPaymentId(String paymentId) {
		this.paymentId = paymentId;
	}

	
	public long getDeliveryCharges() {
		return deliveryCharges;
	}


	public void setDeliveryCharges(long deliveryCharges) {
		this.deliveryCharges = deliveryCharges;
	}


	public long getId() {
		return id;
	}




	public void setId(long id) {
		this.id = id;
	}




	public String getStatus() {
		return status;
	}
	public List<Product> getProducts() {
		return products;
	}


	public void setProducts(List<Product> products) {
		this.products = products;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public String getOrderId()
	{
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public long getAmount() {
		return amount;
	}
	public void setAmount(long amount) {
		this.amount = amount;
	}
	
	
	public OrdersEcom(Users user) {
		super();
		this.user = user;
	}


	public Users getUser() {
		return user;
	}


	public void setUser(Users user) {
		this.user = user;
	}


	
	@Override
	public String toString() {
		return "OrdersEcom [id=" + id + ", orderId=" + orderId + ", amount=" + amount + ", user=" + user
				+ ", deliveryCharges=" + deliveryCharges + ", status=" + status + ", products=" + products
				+ ", paymentId=" + paymentId + "]";
	}

	

}
