package com.commerce.newbies.ecommerceproject.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.commerce.newbies.ecommerceproject.entities.Category;
import com.commerce.newbies.ecommerceproject.entities.OrdersEcom;
import com.commerce.newbies.ecommerceproject.entities.SubCategory;
import com.commerce.newbies.ecommerceproject.entities.Users;

public interface OrderRepository extends JpaRepository<OrdersEcom,Long>  {

	Optional<OrdersEcom> findByOrderId(String orderId);
	
	@Query("SELECT r FROM OrdersEcom r WHERE r.user.id=:userId and r.status='paid' or r.status='Refunded'")
	List<OrdersEcom> getOrderByUserId(@Param("userId")Long userId  );

}
