package com.commerce.newbies.ecommerceproject.security;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.commerce.newbies.ecommerceproject.entities.Users;
import com.commerce.newbies.ecommerceproject.repository.UserRepository;
import com.commerce.newbies.ecommerceproject.services.UserServices;

@Component
public class EmailPassWordAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	private UserServices userService;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private UserRepository userRepo;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		// TODO Auto-generated method stub
		String email = authentication.getName();
		String pass = authentication.getCredentials().toString();
		
		if(!userService.userAlreadyPresent(email))
			throw new BadCredentialsException("User does not exist");
		Users user = userRepo.findByEmail(email).get(0); 
		String userPass = user.getPassword();
		
		if(passwordEncoder.matches( pass,userPass)) {
			
			return new UsernamePasswordAuthenticationToken(email, null ,getGrantedAuthorities(user.getAccountType()));
		}
		throw new BadCredentialsException(" Wrong credentials ");
	}
    private List<GrantedAuthority> getGrantedAuthorities(String roles )
    {
    	List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
    	
    		grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_"+roles));
    	
    	return grantedAuthorities;
    }

	@Override
	public boolean supports(Class<?> authentication) {
		// TODO Auto-generated method stub
		return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
	}

}
