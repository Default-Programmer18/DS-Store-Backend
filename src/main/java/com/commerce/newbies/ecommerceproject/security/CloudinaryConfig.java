package com.commerce.newbies.ecommerceproject.security;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.cloudinary.Cloudinary;


@Configuration
public class CloudinaryConfig {

	@Bean
	public Cloudinary getCloudinary() {
		// TODO Auto-generated method stub
		Map config=new HashMap();
		config.put("cloud_name", "dtqxcg7nd"); // insert here you cloud name
		 config.put("api_key", "442776545871744");// insert here your api code
		 config.put("api_secret", "GI8JLVHcI65rl4HSJmYfyUksYtg");
		 config.put("secure", true);
	        
	       return new Cloudinary(config);

	} 
}
