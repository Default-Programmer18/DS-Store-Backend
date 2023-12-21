package com.commerce.newbies.ecommerceproject.filters;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.crypto.SecretKey;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.commerce.newbies.ecommerceproject.constants.SecurityConstants;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.IOException;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtGeneratorFilter extends OncePerRequestFilter {

	@Override
	public void doFilterInternal(HttpServletRequest Request,HttpServletResponse Response,FilterChain filterchain)throws IOException,ServletException, java.io.IOException
	{
		try {
			SecretKey secret_key=Keys.hmacShaKeyFor(SecurityConstants.JWT_SECRET.getBytes(StandardCharsets.UTF_8));
			Authentication auth =SecurityContextHolder.getContext().getAuthentication();
			Date d=new Date();
			Date expiry=new Date(d.getTime()+(1000*60*60*24));
			var jwtToken=Jwts.builder().setIssuer("Ecommerce").setIssuedAt(d).claim("username", auth.getName()).claim("authorities", getStudentRoles((List<GrantedAuthority>) auth.getAuthorities())).setExpiration(expiry).signWith(secret_key).compact();
			Response.setHeader(SecurityConstants.JWT_Header,jwtToken);
			System.out.println(jwtToken);
			
			
			
		}
		catch(Exception e)
		{
			
		}
		filterchain.doFilter(Request, Response);
		
		
	}
	@Override
	protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException
	{
		return (!request.getServletPath().equals("/auth/v1/user"));
	}
	private String getStudentRoles(List<GrantedAuthority> collection) {
        Set<String> authoritiesSet = new HashSet<>();
        for (GrantedAuthority authority : collection) {
            authoritiesSet.add(authority.getAuthority());
        }
        
        return String.join(",", authoritiesSet);
    }
	 
}
