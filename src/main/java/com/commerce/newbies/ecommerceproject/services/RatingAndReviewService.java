package com.commerce.newbies.ecommerceproject.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.commerce.newbies.ecommerceproject.entities.Category;
import com.commerce.newbies.ecommerceproject.entities.RatingAndReview;
import com.commerce.newbies.ecommerceproject.repository.RatingAndReviewRepository;
import com.commerce.newbies.ecommerceproject.repository.UserRepository;

@Service
public class RatingAndReviewService {

	@Autowired
	private RatingAndReviewRepository ratingRepo;
	@Autowired
	private UserRepository userRepo;

	 public boolean createRatingAndReview(RatingAndReview rating, Long product_id, Long user_id)
	{
		 RatingAndReview existedRatingAndReview=ratingRepo.getRatingAndReviewByUser(product_id,user_id); 
		if(existedRatingAndReview==null)
			{rating.setUser(userRepo.findById(user_id).orElse(null) );
			ratingRepo.save(rating);
			return true; }
		return false;}
	 
	 public boolean updateRatingAndReviewService(RatingAndReview rating, Long product_id, Long user_id)
		{
			 RatingAndReview existedRatingAndReview=ratingRepo.getRatingAndReviewByUser(product_id,user_id); 
			 System.out.println(existedRatingAndReview);
			 
			 
			if(existedRatingAndReview!=null)
				{existedRatingAndReview.setUser(userRepo.findById(user_id).orElse(null) );
				 existedRatingAndReview.setRating( rating.getRating());
				 existedRatingAndReview.setReviewText(rating.getReviewText());
				 System.out.println("from if:  "+existedRatingAndReview);
				ratingRepo.save(existedRatingAndReview);
				return true; }
			return false;
			
		}
	 
	
	 
	 public boolean deleteRatingAndReviewService(long pid,long uid)
		{
		 	RatingAndReview rating = ratingRepo.getRatingAndReviewByUser(pid,uid);
		 	
		 	if(rating==null)
		 		return false;
		 	else
		 	{
		 		ratingRepo.delete(rating);
		 		return true;}
		}
			

	 
	 public List<RatingAndReview> getAllRatingAndReviewService()
	 {  
		 	List<RatingAndReview> allRatingAndReview=ratingRepo.findAll();
		 	return allRatingAndReview;
		 	
	 }
	 
	
	 
}