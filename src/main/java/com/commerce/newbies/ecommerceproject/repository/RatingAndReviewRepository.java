package com.commerce.newbies.ecommerceproject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.commerce.newbies.ecommerceproject.entities.RatingAndReview;
import com.commerce.newbies.ecommerceproject.entities.Users;

public interface RatingAndReviewRepository extends JpaRepository<RatingAndReview,Long> {
	 
	 @Query("SELECT r FROM RatingAndReview r WHERE r.user.id=:uid AND r.product.id=:pid")
	 RatingAndReview getRatingAndReviewByUser(@Param("pid")Long product_id,@Param("uid")Long user_id);
	 
	 @Query("Select avg(r.rating)FROM RatingAndReview r where r.product.id=:pid " )
		Double getSumofRating(@Param("pid")Long pid);
	 
}
