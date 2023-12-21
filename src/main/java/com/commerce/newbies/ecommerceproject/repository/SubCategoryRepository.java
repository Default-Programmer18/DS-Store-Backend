package com.commerce.newbies.ecommerceproject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.commerce.newbies.ecommerceproject.entities.Category;
import com.commerce.newbies.ecommerceproject.entities.RatingAndReview;
import com.commerce.newbies.ecommerceproject.entities.SubCategory;
import com.commerce.newbies.ecommerceproject.entities.Users;

public interface SubCategoryRepository  extends JpaRepository<SubCategory,Long>{
	@Query("SELECT r FROM SubCategory r WHERE r.category.id=:catId ")
	List<SubCategory> getSubcategoryByCategory(@Param("catId")Long catId  );

}
