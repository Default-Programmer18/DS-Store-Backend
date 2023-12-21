package com.commerce.newbies.ecommerceproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.commerce.newbies.ecommerceproject.entities.ResetPassword;
import com.commerce.newbies.ecommerceproject.entities.Users;
import com.commerce.newbies.ecommerceproject.repository.UserRepository;
import com.commerce.newbies.ecommerceproject.services.ResetPasswordService;
@RestController
public class ResetPasswordController {
	@Autowired
	private ResetPasswordService resetPasswordServices;
	
	
	
	@PostMapping("/ResetPassword")
	public ResponseEntity<String> resetNewPassword(@RequestBody ResetPassword rp,@RequestParam("id") long id)
	{
		System.out.println("heyyyyyyyy:"+rp.getCurrentPassword());
		System.out.println("heyyyyyyyy22222222222222222:"+rp.getNewPassword());
	if(resetPasswordServices.resetPassword(rp,id))
	{
		return new ResponseEntity("Password Reset Successful.",HttpStatusCode.valueOf(201));	
	}
		return new ResponseEntity("Password Reset Failed.",HttpStatusCode.valueOf(400));
		
	}
	
	}