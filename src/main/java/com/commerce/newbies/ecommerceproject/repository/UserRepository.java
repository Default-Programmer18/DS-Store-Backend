package com.commerce.newbies.ecommerceproject.repository;

import java.util.List;
import java.util.function.Predicate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.commerce.newbies.ecommerceproject.entities.Users;


public interface UserRepository extends JpaRepository<Users, Long> {
	
	
 	 List<Users>findByEmail(String email);
 	

}
