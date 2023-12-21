package com.commerce.newbies.ecommerceproject.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;

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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.commerce.newbies.ecommerceproject.entities.Category;
import com.commerce.newbies.ecommerceproject.entities.Product;
import com.commerce.newbies.ecommerceproject.services.CategoriesService;
import com.commerce.newbies.ecommerceproject.services.CloudinaryService;
import com.commerce.newbies.ecommerceproject.services.ProductServices;


@RestController
@RequestMapping("/Product/v1")
public class ProductController {
	
		@Autowired
		private ProductServices productService;
		
		@Autowired
		private CloudinaryService cloudService;
		
		@PostMapping(path="/createProduct" )
		public ResponseEntity<String> createProduct(@RequestPart("data") Product product,@RequestParam("image") MultipartFile img) throws IOException, Error
		{
			Map data=cloudService.upload(img);
			String urlLoc=data.get("secure_url").toString();
			product.setProductImage(urlLoc);
		
			if(productService.createProduct(product)==null)
			{
			
				return new ResponseEntity("Wrong input.",HttpStatusCode.valueOf(400));	
			}
				return new ResponseEntity("Product Created.",HttpStatusCode.valueOf(201));
		}
		@DeleteMapping("/deleteProduct")
		public ResponseEntity<String> deleteCategory(@RequestParam Long id)
		{
			if(!productService.deleteProductById(id))
			{
			return new ResponseEntity("No Such Product Exists.",HttpStatusCode.valueOf(400));	}
			
			return new ResponseEntity("Product deleted.",HttpStatusCode.valueOf(201));
		}
		@PutMapping("/UpdateProduct")
		public ResponseEntity<String> updateProduct(@RequestBody Product product)
		{
			if(!productService.updateProductService(product))
			{
				return new ResponseEntity("Wrong info.",HttpStatusCode.valueOf(400));	}
			
			return new ResponseEntity("Product updated.",HttpStatusCode.valueOf(201));
		}
			
		@GetMapping("/GetAllProduct")
		public List<Product> getAllProduct()
		{List<Product> c=productService.getAllProductService();
			return c;
		}
		
		@GetMapping("/GetAllProductByPriceDescending")
		public List<Product> getAllProductFilteredByPriceDescending()
		{List<Product> c=productService.getAllProductFilteredByPriceDescendingService();
			return c;
		}	
		
		@GetMapping("/GetAllProductByPriceAscending")
		public List<Product> getAllProductFilteredByPriceAscending()
		{List<Product> c=productService.getAllProductFilteredByPriceAscendingService();
			return c;
		}	
	}



