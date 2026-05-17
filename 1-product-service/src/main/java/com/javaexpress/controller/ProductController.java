package com.javaexpress.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.javaexpress.models.Product;
import com.javaexpress.service.ProductService;


@RestController
@RequestMapping("/products")
public class ProductController {

	@Autowired
	private ProductService productService;
	
	@PostMapping
	public ResponseEntity<Product>save(@RequestBody Product product) { 
	 var result = productService.save(product);
	 return new	 ResponseEntity<>(result, HttpStatus.CREATED); 
	} 
	
	@GetMapping("/{productId}")
	public Product findById(@PathVariable Long productId)
	{
		return productService.findById(productId);
	}
	
	@DeleteMapping("/{productId}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void deleteById(@PathVariable Long productId)
	{
		productService.deleteById(productId);
	}
	
	@PutMapping("/{productId}")
	public Product update(@RequestBody Product product, @PathVariable Long productId) {
      return  productService.update(product, productId);		
	}
}
