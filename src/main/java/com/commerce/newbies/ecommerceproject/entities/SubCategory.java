package com.commerce.newbies.ecommerceproject.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;


@Entity
public class SubCategory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String subCategoryName;
	
	@ManyToOne
	@JoinColumn(name="category_id")
	@JsonBackReference
	private Category category;
	
	@OneToMany(mappedBy = "subcategory", fetch = FetchType.EAGER)
	@JsonManagedReference(value="subcat-prod")
	private List<Product> products;
	private String subcategoryImage;
	private String subcategoryDesc;
	public SubCategory(Long id, String subCategoryName, Category category, List<Product> products,
			String subcategoryImage, String subcategoryDesc) {
		super();
		this.id = id;
		this.subCategoryName = subCategoryName;
		this.category = category;
		this.products = products;
		this.subcategoryImage = subcategoryImage;
		this.subcategoryDesc = subcategoryDesc;
	}

	public String getSubcategoryDesc() {
		return subcategoryDesc;
	}

	public void setSubcategoryDesc(String subcategoryDesc) {
		this.subcategoryDesc = subcategoryDesc;
	}

	public SubCategory(Long id, String subCategoryName, List<Product> products) {
		super();
		this.id = id;
		this.subCategoryName = subCategoryName;
		this.products = products;
	}
	
	public SubCategory(Long id, String subCategoryName, Category category,List<Product> products) {
		super();
		this.id = id;
		this.subCategoryName = subCategoryName;
		this.category = category;
		this.products = products;
	}
	public SubCategory( String subCategoryName, Category category, String subcategoryImage) {
		super();
		
		this.subCategoryName = subCategoryName;
		this.category = category;
		this.subcategoryImage=subcategoryImage;
		this.products = null;
	}


	public String getSubcategoryImage() {
		return subcategoryImage;
	}

	public void setSubcategoryImage(String subcategoryImage) {
		this.subcategoryImage = subcategoryImage;
	}

	public SubCategory(Long id) {
		super();
		this.id = id;
	}

	public SubCategory() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getSubCategoryName() {
		return subCategoryName;
	}
	public void setSubCategoryName(String subCategoryName) {
		this.subCategoryName = subCategoryName;
	}
	public List<Product> getProducts() {
		return products;
	}
	public void setProducts(List<Product> products) {
		this.products = products;
	}
	
	
	
}