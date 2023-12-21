package com.commerce.newbies.ecommerceproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.commerce.newbies.ecommerceproject.entities.Product;


public interface ProductRepository extends JpaRepository<Product,Long>{
	
//	@Query("Select Count(p.ratingAndReview) from Product p where p.id=:pid ")
//	Long getCountofRating(@Param("pid")Long pid);
//	
	

}
