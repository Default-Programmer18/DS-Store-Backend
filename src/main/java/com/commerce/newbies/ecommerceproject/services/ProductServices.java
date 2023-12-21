package com.commerce.newbies.ecommerceproject.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


import com.commerce.newbies.ecommerceproject.entities.Product;
import com.commerce.newbies.ecommerceproject.repository.CategoriesRepository;
import com.commerce.newbies.ecommerceproject.repository.ProductRepository;

@Service
public class ProductServices {
	@Autowired
	private ProductRepository productRepo;
	
	 public Product createProduct(Product product)
	{ 
		return productRepo.save(product);
		
	}
	 
	 public boolean deleteProductById(long id)
		{
		 Product products=productRepo.findById(id).orElse(null);
		 productRepo.delete(products);
		 if(products==null)
		 return false;
		 else
			 return true;
		}
			
	 public boolean updateProductService( Product  product)
	 {    Product existedProduct=productRepo.findById( product.getId()).orElse(null); 
	 		
	 		if(existedProduct==null)
	 		{
	 			return false;
	 		}
	 		existedProduct.setProductname(product.getProductname());
	 		existedProduct.setProductDesc(product.getProductDesc());
	 		existedProduct.setProductPrice(product.getProductPrice());
	 		existedProduct.setSubcategory(product.getSubcategory());
	 		existedProduct.setCategory(product.getCategory());
	 		existedProduct.setCompanyName(product.getCompanyName());
	 		productRepo.save(existedProduct);
			 return true;
				 
	 }
	 public List<Product> getAllProductService()
	 {  
		 	List<Product> allProduct=productRepo.findAll();
		 	return allProduct;
		 	
	 }
	 public List<Product> getAllProductFilteredByPriceDescendingService()
	 { 
		 List<Product> allProductFilteredByPrice=productRepo.findAll(Sort.by("productPrice").descending());
		 return allProductFilteredByPrice;
	 }
	 
	 public List<Product> getAllProductFilteredByPriceAscendingService()
	 { 
		 List<Product> allProductFilteredByPrice=productRepo.findAll(Sort.by("productPrice").ascending());
		 return allProductFilteredByPrice;
	 }
	 
	 

	 
	 
}

