package com.commerce.newbies.ecommerceproject.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class RatingAndReview {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(cascade =CascadeType.ALL)
		
	@JoinColumn(name = "users_id")
	@JsonBackReference(value = "user-prod")
	private Users user;
	
	@ManyToOne
	@JoinColumn(name = "product_id")
	@JsonBackReference(value = "ratingAndReview-prod")
	private Product product;
	
	private String reviewText;
	private int rating;
	
	public RatingAndReview() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public RatingAndReview(Long id, Users user, Product product, String reviewText, int rating) {
		super();
		this.id = id;
		this.user = user;
		this.product = product;
		this.reviewText = reviewText;
		this.rating = rating;
	}
	
	public RatingAndReview(Long id)
	{
		super();
		this.id = id;
	}
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Users getUser() {
		return user;
	}
	public void setUser(Users user) {
		this.user = user;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public String getReviewText() {
		return reviewText;
	}
	public void setReviewText(String reviewText) {
		this.reviewText = reviewText;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	@Override
	public String toString() {
		return "RatingAndReview [id=" + id + ", user=" + user + ", product=" + product + ", reviewText=" + reviewText
				+ ", rating=" + rating + "]";
	}
	
	
	

}
