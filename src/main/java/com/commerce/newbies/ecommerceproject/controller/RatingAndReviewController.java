package com.commerce.newbies.ecommerceproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.commerce.newbies.ecommerceproject.entities.Product;
import com.commerce.newbies.ecommerceproject.entities.RatingAndReview;
import com.commerce.newbies.ecommerceproject.repository.RatingAndReviewRepository;
import com.commerce.newbies.ecommerceproject.services.ProductServices;
import com.commerce.newbies.ecommerceproject.services.RatingAndReviewService;

@RestController
@RequestMapping("/RatingAndReview/v1")
public class RatingAndReviewController {
	@Autowired
	private RatingAndReviewService ratingService;
	
	@PostMapping("/RatingAndReviewRepository")
	public ResponseEntity<String> createRatingAndReviewRepository(@RequestBody RatingAndReview rating,@RequestParam long product_id,@RequestParam long user_id)
	{System.out.println("pro:  "+product_id+"user:  "+user_id);
		if(ratingService.createRatingAndReview(rating,product_id,user_id)==true)
		{	 
		return new ResponseEntity("RATING REVIEW CREATED.",HttpStatusCode.valueOf(201));}
		return new ResponseEntity("review exists.",HttpStatusCode.valueOf(400));
	}
	

	@PutMapping("/UpdateRatingAndReview")
	public ResponseEntity<String> UpdateRatingAndReviewService(@RequestBody RatingAndReview rating,@RequestParam long product_id,@RequestParam long user_id)
	{System.out.println("pro:  "+product_id+"user:  "+user_id);
		if(ratingService.updateRatingAndReviewService(rating,product_id,user_id)==true)
		{	 
			return new ResponseEntity("RATING REVIEW UPDATED.",HttpStatusCode.valueOf(201));
			}
		return new ResponseEntity("review exists.",HttpStatusCode.valueOf(400));
	}
	
	@DeleteMapping("/DeleteRatingAndReview")
	public ResponseEntity<String> DeleteRatingAndReviewService(@RequestParam String product_id,@RequestParam String user_id)
	{
		long a=Long.parseLong(product_id);
		long b=Long.parseLong(user_id);
		
		if(ratingService.deleteRatingAndReviewService(a,b)==true)
		{	 
			return new ResponseEntity("RATING REVIEW CREATED.",HttpStatusCode.valueOf(201));}
			return new ResponseEntity("review exists.",HttpStatusCode.valueOf(400));
	}

}
