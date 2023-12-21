package com.commerce.newbies.ecommerceproject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.commerce.newbies.ecommerceproject.entities.ResetPassword;
import com.commerce.newbies.ecommerceproject.entities.Users;
import com.commerce.newbies.ecommerceproject.repository.UserRepository;
@Service
public class ResetPasswordService {
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private PasswordEncoder pdEncoder;
	
	

	public boolean resetPassword( ResetPassword rp, long id)
	{
		
		Users u=userRepo.findById(id).orElse(null);
		String passfromDb=u.getPassword();
		String passfromUser=rp.getCurrentPassword();
		
		
		if(pdEncoder.matches(passfromUser,passfromDb))
		{
			u.setPassword(pdEncoder.encode(rp.getNewPassword()));
			userRepo.save(u);
			return true;
			
		}
		
		return false;
		
	}
	
	}