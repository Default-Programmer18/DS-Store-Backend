package com.commerce.newbies.ecommerceproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.commerce.newbies.ecommerceproject.entities.Category;
import com.commerce.newbies.ecommerceproject.repository.CategoriesRepository;
import com.commerce.newbies.ecommerceproject.services.CategoriesService;

@RestController
@RequestMapping("/Category/v1")
public class CategoryController {
	@Autowired
	private CategoriesService catService;
	@PostMapping("/createCategory")
	public ResponseEntity<String> createCategory(@RequestBody Category category)
	{
		if(catService.createCategory(category)==null)
		{
		return new ResponseEntity("Wrong input.",HttpStatusCode.valueOf(400));	}
		return new ResponseEntity("Category Created.",HttpStatusCode.valueOf(201));
	}
	@DeleteMapping("/deleteCategory")
	public ResponseEntity<String> deleteCategory(@RequestParam Long id)
	{
		if(!catService.deleteCategoryById(id))
		{
		return new ResponseEntity("No Such Categoy Exists.",HttpStatusCode.valueOf(400));	}
		
		return new ResponseEntity("Category deleted.",HttpStatusCode.valueOf(201));
	}
	@PutMapping("/UpdateCategory")
	public ResponseEntity<String> updateCategory(@RequestBody Category category)
	{
		if(!catService.updateCategoryService(category))
		{
			return new ResponseEntity("Wrong info.",HttpStatusCode.valueOf(400));	}
		
		return new ResponseEntity("Category updated.",HttpStatusCode.valueOf(201));
	}
		
	@GetMapping("/GetAllCategory")
	public List<Category> getAllCategory()
	{List<Category> c=catService.getAllCategoryService();
		return c;
	}
		

}
