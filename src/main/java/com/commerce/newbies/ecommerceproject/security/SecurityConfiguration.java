package com.commerce.newbies.ecommerceproject.security;


	import java.util.Arrays;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
	import org.springframework.context.annotation.Configuration;
	import org.springframework.http.HttpMethod;
	import org.springframework.security.config.annotation.web.builders.HttpSecurity;
	import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
	import org.springframework.security.crypto.password.PasswordEncoder;
	import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationConverter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import com.commerce.newbies.ecommerceproject.exceptionHandler.MyAuthenciationEntryPoint;
import com.commerce.newbies.ecommerceproject.filters.JwtGeneratorFilter;
import com.commerce.newbies.ecommerceproject.filters.JwtValidatorFilter;
import com.fasterxml.jackson.databind.introspect.TypeResolutionContext.Basic;


import jakarta.servlet.http.HttpServletRequest;

	@Configuration
	public class SecurityConfiguration {
		@Autowired
		private MyAuthenciationEntryPoint authenciationEntryPoint;
		@Bean
		SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception
		{
			http.authorizeHttpRequests().requestMatchers("/auth/v1/register","/auth/v1/sendForgetPasswordToken","/auth/v1/ForgetPassword","/auth/v1/sendOtp","/SubCategory/v1/GetAllSubCategory/categoryID","/Category/v1/GetAllCategory",
					"/SubCategory/v1/GetAllSubCategory/category", "/products/**","/auth/v1/checkOtpForPasswordReset").permitAll().anyRequest().authenticated().and()
				.cors().configurationSource(new CorsConfigurationSource() {
					
					@Override
					public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
						// TODO Auto-generated method stub
						 CorsConfiguration config=new CorsConfiguration();
						 config.setAllowedMethods(Collections.singletonList("*"));
						 config.setAllowedOrigins(Collections.singletonList("http://localhost:3000"));
						 config.setAllowCredentials(true);
						 config.setAllowedHeaders(Collections.singletonList("*"));
						 config.setExposedHeaders(Arrays.asList("Authorization"));
						 config.setMaxAge(3600L);
						 return config;
					}
				}).and().csrf((csrf)->csrf.disable())
				.formLogin().and().httpBasic().authenticationEntryPoint(authenciationEntryPoint)
				.and().addFilterAfter(new JwtGeneratorFilter(),BasicAuthenticationFilter.class).addFilterBefore(new JwtValidatorFilter(),BasicAuthenticationFilter.class);
			return http.build();
		}
		
		@Bean
		PasswordEncoder passwordEncoder() {
			return new BCryptPasswordEncoder(11);
		}
	}

	