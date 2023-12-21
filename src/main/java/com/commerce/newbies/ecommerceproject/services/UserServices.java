package com.commerce.newbies.ecommerceproject.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.commerce.newbies.ecommerceproject.entities.Users;
import com.commerce.newbies.ecommerceproject.repository.UserRepository;



@Service
public class UserServices {
	
	@Autowired
	private UserRepository userRepo;
	
	public Boolean userAlreadyPresent(String email)
	{
		 Users l=userRepo.findByEmail(email).get(0);
		 if(l==null)
		return false ;
		 return true;
	}
	
	public Users userfindById(long id,String urlLoc)
	{
		 Users l=userRepo.findById(id).orElse(null);
		 if(l==null)
		return null ;
		 
		 l.setImage(urlLoc);
		 userRepo.save(l);
		 return l;
	}
	public Users userUpdate(long id,Users u)
	{
		 Users l=userRepo.findById(id).orElse(null);
		 if(l==null)
		return null ;
		 if(u.getUsername()!=null)
			 l.setUsername(u.getUsername());
		 if(u.getPhnNo()!=null)
			 l.setPhnNo(u.getPhnNo());
		
		 if(u.getAddress()!=null)
			 l.setAddress(u.getAddress());
		 if(u.getLandmark()!=null)
			 l.setLandmark(u.getLandmark());
		 if(u.getPincode()!=null)
			 l.setPincode(u.getPincode());
		 if(u.getCountry()!=null)
			 l.setCountry(u.getCountry());
		 if(u.getState()!=null)
			 l.setState(u.getState());
		 
		 
		 
		 
		 userRepo.save(l);
		 return l;
	}

	
}
