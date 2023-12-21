package com.commerce.newbies.ecommerceproject.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.commerce.newbies.ecommerceproject.entities.Category;
import com.commerce.newbies.ecommerceproject.entities.SubCategory;
import com.commerce.newbies.ecommerceproject.repository.CategoriesRepository;
import com.commerce.newbies.ecommerceproject.repository.SubCategoryRepository;

@Service
public class SubCategoryServices {

	@Autowired
	private SubCategoryRepository subcatRepo;
	@Autowired
	private CategoriesRepository catRepo;
	 public SubCategory createSubcategory(SubCategory subcategory)
	{ 
		return subcatRepo.save(subcategory);}
	 
	 public boolean deleteSubCategoryById(long id)
		{
		SubCategory subcategories=subcatRepo.findById(id).orElse(null);
		 subcatRepo.delete(subcategories);
		 if(subcategories==null)
		 return false;
		 else
			 return true;
		}
			
	 public boolean updateSubCategoryService(SubCategory subcategory)
	 {    SubCategory existedSubCategories=subcatRepo.findById(subcategory.getId()).orElse(null); 
	 		
	 		if(existedSubCategories==null)
	 		{
	 			return false;
	 		}
	 	
	 		existedSubCategories.setSubCategoryName(subcategory.getSubCategoryName());
	 		subcatRepo.save(existedSubCategories);
			 return true;
				 
	 }
	 public List<SubCategory> getAllSubCategoryService()
	 {  
		 	List<SubCategory> allSubCategories=subcatRepo.findAll();
		 	return allSubCategories;
		 	
	 }
	 public List<SubCategory> getAllSubCategoryService(String categoryName)
	 {  long catid=catRepo.findBycategoryName(categoryName);
		 	List<SubCategory> allSubCategories=subcatRepo.getSubcategoryByCategory(catid);
		 	return allSubCategories;
		 	
	 }
	 public List<SubCategory> getAllSubCategoryidService(long id)
	 { 
		 	List<SubCategory> allSubCategories=subcatRepo.getSubcategoryByCategory(id);
		 	return allSubCategories;
		 	
	 }
}