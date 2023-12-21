package com.commerce.newbies.ecommerceproject.filters;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import javax.crypto.SecretKey;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.commerce.newbies.ecommerceproject.constants.SecurityConstants;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtValidatorFilter extends OncePerRequestFilter{

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String jwt = request.getHeader(SecurityConstants.JWT_Header);
		System.out.println(jwt);
		if(jwt != null)
		{
			try
			{
				SecretKey key = Keys.hmacShaKeyFor(SecurityConstants.JWT_SECRET.getBytes(StandardCharsets.UTF_8));
				
				Claims claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jwt).getBody();
				String username = String.valueOf(claims.get("username"));
	            String authorities = (String) claims.get("authorities");
	
	            Authentication auth = new UsernamePasswordAuthenticationToken(username, null,
	                    getStudentRoles(authorities));
	
	            SecurityContextHolder.getContext().setAuthentication(auth);
			} catch (ExpiredJwtException ex) {
				logger.info("Token expired!");
			}
		}
		else 
		{
			throw new BadCredentialsException(" Token is Missing ");
		}
	
		filterChain.doFilter(request, response);
	}
	
	private List<GrantedAuthority> getStudentRoles(String authorities) {
        List<GrantedAuthority> grantedAuthorityList = new ArrayList<>();
        String[] roles = authorities.split(",");
        for (String role : roles) {
            grantedAuthorityList.add(new SimpleGrantedAuthority(role.replaceAll("\\s+", "")));
        }

        return grantedAuthorityList;
    }
	
	@Override
	protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
		// TODO Auto-generated method stub
		return (request.getServletPath().equals("/auth/v1/user") || request.getServletPath().equals("/auth/v1/sendpasswordToken") || request.getServletPath().equals("/auth/v1/forgot-pass")||request.getServletPath().equals("/auth/v1/register")||request.getServletPath().equals("/auth/v1/sendOtp")
				||request.getServletPath().equals("/Category/v1/GetAllCategory")||request.getServletPath().equals("/SubCategory/v1/GetAllSubCategory/category")||request.getServletPath().equals("/SubCategory/v1/GetAllSubCategory/categoryID")
						||request.getServletPath().equals("/auth/v1/sendForgetPasswordToken")||request.getServletPath().equals("/auth/v1/checkOtpForPasswordReset")
						||request.getServletPath().equals("/auth/v1/ForgetPassword")	);
	}

}