package com.commerce.newbies.ecommerceproject.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

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
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.commerce.newbies.ecommerceproject.entities.Category;
import com.commerce.newbies.ecommerceproject.entities.Product;
import com.commerce.newbies.ecommerceproject.entities.SubCategory;
import com.commerce.newbies.ecommerceproject.entities.SubCategory;
import com.commerce.newbies.ecommerceproject.repository.CategoriesRepository;
import com.commerce.newbies.ecommerceproject.services.CategoriesService;
import com.commerce.newbies.ecommerceproject.services.CloudinaryService;
import com.commerce.newbies.ecommerceproject.services.SubCategoryServices;

@RestController
@RequestMapping("/SubCategory/v1")
public class SubCategoryController {
	
	@Autowired
	private SubCategoryServices subcatService;
	@Autowired
	private CategoriesService catService;
	@Autowired
	private CloudinaryService cloudService;

		@PostMapping("/createSubCategory")
		public ResponseEntity<String> createSubCategory(@RequestPart("data") SubCategory subcategory,@RequestParam("image") MultipartFile img) throws IOException, Error
		{
			Map data=cloudService.upload(img);
		String urlLoc=data.get("secure_url").toString();
		subcategory.setSubcategoryImage(urlLoc);
	
			
			if(subcatService.createSubcategory(subcategory)==null)
			{
			return new ResponseEntity("Wrong input.",HttpStatusCode.valueOf(400));	}
			return new ResponseEntity("Sub-Category Created.",HttpStatusCode.valueOf(201));
		}
		
		@DeleteMapping("/deleteSubCategory")
		public ResponseEntity<String> deletesubCategory(@RequestParam Long id)
		{
			if(!subcatService.deleteSubCategoryById(id))
			{
			return new ResponseEntity("No Such Sub-Category Exists.",HttpStatusCode.valueOf(400));	}
			
			return new ResponseEntity("Sub-Category deleted.",HttpStatusCode.valueOf(201));
		}
		
		@PutMapping("/UpdateSubCategory")
		public ResponseEntity<String> updateSubCategory(@RequestBody SubCategory category)
		{
			if(!subcatService.updateSubCategoryService(category))
			{
				return new ResponseEntity("Wrong info.",HttpStatusCode.valueOf(400));	}
			
			return new ResponseEntity("Sub-Category updated.",HttpStatusCode.valueOf(201));
		}
			
		@GetMapping("/GetAllSubCategory")
		public List<SubCategory> getAllSubCategory()
		{List<SubCategory> c=subcatService.getAllSubCategoryService();
			return c;
			
		}
		@GetMapping("/GetAllSubCategory/category" )
		public List<SubCategory> getAllSubCategory(@RequestParam String categoryName)
		{
			List<SubCategory> c=subcatService.getAllSubCategoryService(categoryName);
			return c;
			
		}	
		@GetMapping("/GetAllSubCategory/categoryID" )
		public List<SubCategory> getAllSubCategoryID(@RequestParam long categoryid)
		{
			List<SubCategory> c=subcatService.getAllSubCategoryidService(categoryid);
			return c;
			
		}	
			
	}
