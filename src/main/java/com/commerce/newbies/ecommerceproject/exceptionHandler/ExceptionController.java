

package com.commerce.newbies.ecommerceproject.exceptionHandler;

import java.time.LocalDateTime;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ExceptionController {
	
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllException(Exception ex, WebRequest request) throws Exception {
		ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now() , ex.getMessage() , request.getDescription(false));
		
		return new ResponseEntity(errorDetails , HttpStatus.INTERNAL_SERVER_ERROR);
	}
	@ExceptionHandler(BadCredentialsException.class)
	public final ResponseEntity<Object> handleBadCredentialException(Exception ex, WebRequest request) throws Exception {
		ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now() , ex.getMessage() , request.getDescription(false));
		
		return new ResponseEntity(errorDetails , HttpStatus.UNAUTHORIZED);
	}
	@ExceptionHandler(AuthenticationException.class)
	public final ResponseEntity<Object> handleAuthenticationException(Exception ex, WebRequest request) throws Exception {
		ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now() , ex.getMessage() , request.getDescription(false));
	
		return new ResponseEntity(errorDetails , HttpStatus.UNAUTHORIZED);
	}
	@ExceptionHandler(UsernameNotFoundException.class)
	public final ResponseEntity<Object> handleUserNotFoundException(Exception ex, WebRequest request) throws Exception {
		ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now() , ex.getMessage() , request.getDescription(false));
		
		return new ResponseEntity(errorDetails , HttpStatus.NOT_FOUND);
	}
	
}