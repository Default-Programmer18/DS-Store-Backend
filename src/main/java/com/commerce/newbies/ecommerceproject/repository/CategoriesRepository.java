package com.commerce.newbies.ecommerceproject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.commerce.newbies.ecommerceproject.entities.Category;
import com.commerce.newbies.ecommerceproject.entities.SubCategory;
import com.commerce.newbies.ecommerceproject.entities.Users;
import com.google.common.base.Optional;

public interface CategoriesRepository extends JpaRepository<Category,Long> {
	@Query("SELECT r.id FROM Category r WHERE r.categoryName=:categoryName ")
	Long findBycategoryName(@Param("categoryName")String categoryName);

	
}
