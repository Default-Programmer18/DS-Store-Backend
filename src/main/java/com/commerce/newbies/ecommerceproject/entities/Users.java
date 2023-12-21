package com.commerce.newbies.ecommerceproject.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;


@Entity
public class Users {

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;
	
	private String username;
	private String email;
	
	@JsonProperty( access=Access.WRITE_ONLY)
	private String password;
	
	private String phnNo;
	private String address;
	private String landmark;
	private String country;
	private String pincode;
	private String state;
	private String image;
	private String accountType;
	private String otp;
	
	@OneToMany(mappedBy ="seller" , fetch = FetchType.EAGER )
	@JsonManagedReference(value ="product-seller-relation")
	private List<Product> products;
	
	
	
	public Users(Long id, String username, String email, String password, String phnNo, String address, String landmark,
			String country, String pincode, String state, String image, String accountType, String otp,
			List<Product> products, List<RatingAndReview> rating_Review) {
		super();
		this.id = id;
		this.username = username;
		this.email = email;
		this.password = password;
		this.phnNo = phnNo;
		this.address = address;
		this.landmark = landmark;
		this.country = country;
		this.pincode = pincode;
		this.state = state;
		this.image = image;
		this.accountType = accountType;
		this.otp = otp;
		this.products = products;
		this.rating_Review = rating_Review;
	}
	public List<Product> getProducts() {
		return products;
	}
	public void setProducts(List<Product> products) {
		this.products = products;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getPincode() {
		return pincode;
	}
	public void setPincode(String pincode) {
		this.pincode = pincode;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public List<RatingAndReview> getRating_Review() {
		return rating_Review;
	}
	public void setRating_Review(List<RatingAndReview> rating_Review) {
		this.rating_Review = rating_Review;
	}
	@OneToMany(mappedBy ="user", fetch = FetchType.EAGER,cascade =CascadeType.ALL,orphanRemoval =true )
	@JsonManagedReference(value = "user-prod")
	private List<RatingAndReview> rating_Review;
	 
	public Users() {
		
		super();
		// TODO Auto-generated constructor stub
	}
	public Users(Long id, String username, String email, String password, String phnNo, String address, String landmark,
			String image, String accountType, String otp) {
		super();
		this.id = id;
		this.username = username;
		this.email = email;
		this.password = password;
		this.phnNo = phnNo;
		this.address = address;
		this.landmark = landmark;
		this.image = image;
		this.accountType = accountType;
		this.otp = otp;
	}
	public Users( String username, String email, String password,
			 String otp) {
		
	
		this.username = username;
		this.email = email;
		this.password = password;
	
		
		this.otp = otp;
	}
	
	
	public Users(Long id) {
		super();
		this.id = id;
	}
	public Users(String email,String otp)
	{this.email = email;
		this.otp = otp;
	}
	public Users(String email)
	{this.email = email;
		
	}
	public Users(String email,String password,String otp)
	{this.email = email;
	this.password = password;
		this.otp = otp;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPhnNo() {
		return phnNo;
	}
	public void setPhnNo(String phnNo) {
		this.phnNo = phnNo;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getLandmark() {
		return landmark;
	}
	public void setLandmark(String landmark) {
		this.landmark = landmark;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	public String getOtp() {
		return otp;
	}
	public void setOtp(String otp) {
		this.otp = otp;
	}
	@Override
	public String toString() {
		return "Users [id=" + id + ", username=" + username + ", email=" + email + ", password=" + password + ", phnNo="
				+ phnNo + ", address=" + address + ", landmark=" + landmark + ", image=" + image + ", accountType="
				+ accountType + ", Otp=" + otp + "]";
	}
	
	}
	
	
	
	




