package com.commerce.newbies.ecommerceproject.exceptionHandler;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class MyAuthenciationEntryPoint implements AuthenticationEntryPoint{

	
	private final HandlerExceptionResolver resolver;
	
	
	public MyAuthenciationEntryPoint(@Qualifier("handlerExceptionResolver")HandlerExceptionResolver resolver) {
		super();
		this.resolver= resolver;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		// TODO Auto-generated method stub
		resolver.resolveException(request, response, null, authException);
		
	}
	

}
