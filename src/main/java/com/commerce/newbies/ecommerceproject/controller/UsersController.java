package com.commerce.newbies.ecommerceproject.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.aspectj.weaver.patterns.HasThisTypePatternTriedToSneakInSomeGenericOrParameterizedTypePatternMatchingStuffAnywhereVisitor;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.commerce.newbies.ecommerceproject.entities.EmailDetails;
import com.commerce.newbies.ecommerceproject.entities.Product;
import com.commerce.newbies.ecommerceproject.entities.Users;
import com.commerce.newbies.ecommerceproject.repository.UserRepository;
import com.commerce.newbies.ecommerceproject.services.CloudinaryService;
import com.commerce.newbies.ecommerceproject.services.EmailServices;
import com.commerce.newbies.ecommerceproject.services.OtpGenerator;
import com.commerce.newbies.ecommerceproject.services.UserServices;
import com.commerce.newbies.ecommerceproject.security.SecurityConfiguration;

@RestController
@RequestMapping("/auth/v1")
public class UsersController {
	
	@Autowired 
	private UserServices services;
	@Autowired 
	private EmailDetails mailDetails;
	@Autowired
	 private EmailServices emailService;
	@Autowired
	private OtpGenerator otpGenerator;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private CloudinaryService cloudService;
	
	@GetMapping("/")
	public ResponseEntity<String> checkTokenIsValid()
	{return new ResponseEntity<>("All Fields are Required.",HttpStatusCode.valueOf(200));
	}

	@PostMapping("/register")
	public ResponseEntity<String> saveUser(@RequestBody Users user)
	{
		if(user.getAccountType().equals("") && user.getPassword().equals("")  &&user.getPhnNo().equals("") && user.getEmail().equals("") && user.getLandmark().equals("") && user.getAccountType().equals("")&&user.getOtp().equals(""))
		{
			return new ResponseEntity<>("All Fields are Required.",HttpStatusCode.valueOf(400));
		}
		String otpFromCache=String.valueOf(otpGenerator.getOPTByKey(user.getEmail()));
		String otpFromUser=user.getOtp();
		System.out.println(otpFromUser +"    "+otpFromCache);
		if(otpFromCache.equals(otpFromUser))
		{  List<Users> L=userRepository.findByEmail(user.getEmail());
			if(!L.isEmpty())
			{
				
				return new ResponseEntity("User Email Already Exists.",HttpStatusCode.valueOf(400));}
		  else {
			  user.setPassword(passwordEncoder.encode(user.getPassword()));
				userRepository.save(user);
			return new ResponseEntity("Registration Successful.",HttpStatusCode.valueOf(201));
			}
		}
		else
		return new ResponseEntity("OTP Expired or Otp Did Not Match.",HttpStatusCode.valueOf(400));
	}
	@PostMapping("/sendOtp")
    public String
    sendMail(@RequestBody Users user)
    {
		
		String email = user.getEmail();
		mailDetails.setRecipient(email);
		otpGenerator.generateOTP(email);
		int otp=otpGenerator.getOPTByKey(email);
		System.out.println("from send otp:  "+otp);
		mailDetails.setSubject("One Time OTP");
		mailDetails.setMsgBody("Your otp: "+otp);
		
		System.out.println(mailDetails);
        String status
            = emailService.sendSimpleMail(mailDetails);
 
        return status;
    }
	@GetMapping("/user")
	public Users showUser()
	{
		Authentication auth=SecurityContextHolder.getContext().getAuthentication();
		String email= auth.getName() ;
		return userRepository.findByEmail(email).get(0);
	}
	@PostMapping("/sendForgetPasswordToken")
    public ResponseEntity<String> sendMailForForgetPassword(@RequestBody Users user)
    {
		String email = user.getEmail();
		mailDetails.setRecipient(email);
		otpGenerator.generateOTP(email);
		int otp=otpGenerator.getOPTByKey(email);
		System.out.println("from send otp:  "+otp);
		
		
		
		mailDetails.setSubject("Reset password");
		mailDetails.setMsgBody("Otp to reset password: "+otp);
		if(userRepository.findByEmail(email).isEmpty())
		{
			return new ResponseEntity("No such email id exist",HttpStatusCode.valueOf(400));
		}
		
		  String status
          = emailService.sendSimpleMail(mailDetails);
		  

		  return new ResponseEntity("otp Send.",HttpStatusCode.valueOf(200));
    }
	
	
	@PostMapping("/checkOtpForPasswordReset")
    public ResponseEntity<String>  checkOtpForPasswordReset(@RequestBody Users user)
    {
		
		String otpFromCache=String.valueOf(otpGenerator.getOPTByKey(user.getEmail()));
		String otpFromUser=user.getOtp();
		System.out.println(otpFromUser +"    "+otpFromCache +"   "+user.getEmail());
		if(otpFromCache.equals(otpFromUser))
		{  List<Users> L=userRepository.findByEmail(user.getEmail());
			if(L.isEmpty())
			{
				
				return new ResponseEntity("user doesn't exist.",HttpStatusCode.valueOf(401));}
		  else {
			 
				return new ResponseEntity(" Successful.",HttpStatusCode.valueOf(201));
			
		  }}
		return new ResponseEntity("otp wrong.",HttpStatusCode.valueOf(400));
		}
	
	
	@PutMapping("/ForgetPassword")
	public  ResponseEntity<String> forgetPassword(@RequestBody Users user)
	{
	
		
		  Users u=userRepository.findByEmail( user.getEmail()).get(0);
		u.setPassword(passwordEncoder.encode(user.getPassword()));
		try {
			userRepository.save(u);
			return new ResponseEntity("Password has been reset.",HttpStatusCode.valueOf(200));
			}
		catch(Exception e)
		{
			e.printStackTrace();
			return new ResponseEntity("Password cannot be reset.",HttpStatusCode.valueOf(400));
		}
		
		
	}
	
	
	@PutMapping(path="/updateProfilePicture/{id}" )
	public ResponseEntity<Users> createProduct(@RequestParam("image") MultipartFile img,@PathVariable long id) throws IOException, Error
	{
		Map data=cloudService.upload(img);
		String urlLoc=data.get("secure_url").toString();
		Users u=services.userfindById(id,urlLoc);
	
		if(u==null)
		{
		
			return new ResponseEntity("Wrong input.",HttpStatusCode.valueOf(400));	
		}
			return new ResponseEntity(u,HttpStatusCode.valueOf(201));
	}
	
	@PutMapping(path="/updateProfile/{id}" )
	public ResponseEntity<Users> createProduct(@RequestBody Users  u,@PathVariable long id) 
	{
		Users USER=services.userUpdate(id,u);
	
			return new ResponseEntity( USER,HttpStatusCode.valueOf(201));
	}
	
	
	}
	


