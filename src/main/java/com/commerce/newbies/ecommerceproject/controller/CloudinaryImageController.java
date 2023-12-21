package com.commerce.newbies.ecommerceproject.controller;

import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.commerce.newbies.ecommerceproject.services.CloudinaryService;

@RestController
public class CloudinaryImageController {
	@Autowired
	private CloudinaryService cloudinaryservice ;
	
	@PostMapping("/upload")
	public ResponseEntity<Map> uploadImage(@RequestParam("image") MultipartFile file) throws IOException, Error
	{
		Map data=this.cloudinaryservice.upload(file);
		if(data!=null)
		return new ResponseEntity<>(data,HttpStatusCode.valueOf(201));
		else
			return new ResponseEntity<>(data,HttpStatusCode.valueOf(400));
	}

}
