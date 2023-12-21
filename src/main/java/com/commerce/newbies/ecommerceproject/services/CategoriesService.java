package com.commerce.newbies.ecommerceproject.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.commerce.newbies.ecommerceproject.entities.Category;
import com.commerce.newbies.ecommerceproject.repository.CategoriesRepository;
import com.google.common.base.Optional;

@Service
public class CategoriesService {

	@Autowired
	private CategoriesRepository catRepo;
	
	 public Category createCategory(Category category)
	{ 
		return catRepo.save(category);}
	 
	 public boolean deleteCategoryById(long id)
		{
		 Category categories=catRepo.findById(id).orElse(null);
		 catRepo.delete(categories);
		 if(categories==null)
		 return false;
		 else
			 return true;
		}
			
	 public boolean updateCategoryService(Category category)
	 {     Category existedCategories=catRepo.findById(category.getId()).orElse(null); 
	 		
	 		if(existedCategories==null)
	 		{
	 			return false;
	 		}
	 		existedCategories.setCategoryDesc(category.getCategoryDesc());
	 		existedCategories.setCategoryName(category.getCategoryName());
	 		catRepo.save(existedCategories);
			 return true;
				 
	 }
	 public List<Category> getAllCategoryService()
	 {  
		 	List<Category> allCategories=catRepo.findAll();
		 	return allCategories;
		 	
	 }
}