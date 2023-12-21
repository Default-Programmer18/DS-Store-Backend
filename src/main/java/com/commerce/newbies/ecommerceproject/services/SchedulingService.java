package com.commerce.newbies.ecommerceproject.services;

import org.apache.catalina.valves.rewrite.Substitution.StaticElement;
import org.hibernate.annotations.Comment;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.config.ScheduledTask;
import org.springframework.scheduling.config.ScheduledTasksBeanDefinitionParser;
import org.springframework.stereotype.Component;

import com.commerce.newbies.ecommerceproject.entities.Product;
import com.commerce.newbies.ecommerceproject.repository.ProductRepository;
import com.commerce.newbies.ecommerceproject.repository.RatingAndReviewRepository;

import ch.qos.logback.classic.Logger;

@Component
public class SchedulingService {
	@Autowired
	private ProductRepository prodRepo;
	@Autowired
	private RatingAndReviewRepository rateRepo;
	
@Scheduled(cron ="0 0 0 * * *")
public void UpdateAverageRating()
 	{
	prodRepo.findAll().forEach((product)->{
		Long pid=product.getId();
		
		double avg=rateRepo.getSumofRating(pid)==null?0.0:rateRepo.getSumofRating(pid);
	
		product.setAvg_rating_count(avg);
		System.out.println(avg);
	
	});
 	}
}
