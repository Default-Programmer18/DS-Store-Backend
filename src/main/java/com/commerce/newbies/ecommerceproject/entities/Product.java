package com.commerce.newbies.ecommerceproject.entities;

import java.util.List;


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
import jakarta.persistence.OneToOne;

@Entity
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String productname;
	private String productDesc;
	private double productPrice;
	
	@ManyToOne
	@JoinColumn(name = "category_id")
	@JsonBackReference(value = "cat-prod")
	private Category category;
	
	@ManyToOne
	@JoinColumn(name = "sub_category_id")
	@JsonBackReference(value = "subcat-prod")
	private SubCategory subcategory;
	
	private String companyName;
	
	@OneToMany(mappedBy ="product" , fetch = FetchType.EAGER )
	@JsonManagedReference(value = "ratingAndReview-prod")
	private List<RatingAndReview> ratingAndReview;
	 
	private double avg_rating_count=0;
	
	private String productImage;
	private List<String> productFeatures;
	private long productQuantity;
	
	@ManyToOne
	@JoinColumn(name = "seller")
	@JsonBackReference(value ="product-seller-relation")
	private Users seller;

	

	




	public Users getSeller() {
		return seller;
	}





	public void setSeller(Users seller) {
		this.seller = seller;
	}





	public Product(Long id, String productname, String productDesc, double productPrice, Category category,
			SubCategory subcategory, String companyName, List<RatingAndReview> ratingAndReview, double avg_rating_count,
			String productImage, List<String> productFeatures, long productQuantity, Users seller) {
		super();
		this.id = id;
		this.productname = productname;
		this.productDesc = productDesc;
		this.productPrice = productPrice;
		this.category = category;
		this.subcategory = subcategory;
		this.companyName = companyName;
		this.ratingAndReview = ratingAndReview;
		this.avg_rating_count = avg_rating_count;
		this.productImage = productImage;
		this.productFeatures = productFeatures;
		this.productQuantity = productQuantity;
		this.seller = seller;
	}





	public Product(Long id, String productname, String productDesc, double productPrice, Category category,
			SubCategory subcategory, String companyName, List<RatingAndReview> ratingAndReview, double avg_rating_count,
			String productImage, List<String> productFeatures, long productQuantity) {
		super();
		this.id = id;
		this.productname = productname;
		this.productDesc = productDesc;
		this.productPrice = productPrice;
		this.category = category;
		this.subcategory = subcategory;
		this.companyName = companyName;
		this.ratingAndReview = ratingAndReview;
		this.avg_rating_count = avg_rating_count;
		this.productImage = productImage;
		this.productFeatures = productFeatures;
		this.productQuantity = productQuantity;
	}





	public long getProductQuantity() {
		return productQuantity;
	}





	public void setProductQuantity(long productQuantity) {
		this.productQuantity = productQuantity;
	}





	




	public List<String> getProductFeatures() {
		return productFeatures;
	}





	public void setProductFeatures(List<String> productFeatures) {
		this.productFeatures = productFeatures;
	}





	public Product(double avg_rating_count) {
		super();
		this.avg_rating_count = avg_rating_count;
	}





	public Product(String productname, String productDesc, double productPrice, Category category,
			SubCategory subcategory, String companyName) {
		super();
		this.productname = productname;
		this.productDesc = productDesc;
		this.productPrice = productPrice;
		this.category = category;
		this.subcategory = subcategory;
		this.companyName = companyName;
		
	}





	public Product(Long id, String productname, String productDesc, double productPrice, Category category,
			SubCategory subcategory, String companyName, List<RatingAndReview> ratingAndReview,
			double avg_rating_count) {
		super();
		this.id = id;
		this.productname = productname;
		this.productDesc = productDesc;
		this.productPrice = productPrice;
		this.category = category;
		this.subcategory = subcategory;
		this.companyName = companyName;
		this.ratingAndReview = ratingAndReview;
		this.avg_rating_count = avg_rating_count;
		
	}


	public String getProductImage() {
		return productImage;
	}

	public void setProductImage(String productImage) {
		this.productImage = productImage;
	}


	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public double getAvg_rating_count() {
		return avg_rating_count;
	}

	public void setAvg_rating_count(double avg_rating_count) {
		this.avg_rating_count = avg_rating_count;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getProductname() {
		return productname;
	}
	public void setProductname(String productname) {
		this.productname = productname;
	}
	public String getProductDesc() {
		return productDesc;
	}
	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
	}
	public double getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(double productPrice) {
		this.productPrice = productPrice;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public SubCategory getSubcategory() {
		return subcategory;
	}
	public void setSubcategory(SubCategory subcategory) {
		this.subcategory = subcategory;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public List<RatingAndReview> getRatingAndReview() {
		return ratingAndReview;
	}
	
	
	

	public Product(Long id) {
		super();
		this.id = id;
	}

	public void setRatingAndReview(List<RatingAndReview> ratingAndReview) {
		this.ratingAndReview = ratingAndReview;
	}

	
	

}