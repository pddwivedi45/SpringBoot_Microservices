package com.javaexpress.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaexpress.models.Product;
import com.javaexpress.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;
	
	public Product save(Product product) {
		return productRepository.save(product);
	}
	
	public Product findById(Long productid)
	{
		return productRepository.findById(productid).
				orElseThrow(()-> new RuntimeException("Product not found with id" + productid));
	}
	
	public void deleteById(Long productId) 
	{
		productRepository.deleteById(productId);
	}
	
	public Product update(Product product, Long productId) {
		Product dbProduct = findById(productId);
		dbProduct.setName(product.getName());
		dbProduct.setDescription(product.getDescription());
		dbProduct.setPrice(product.getPrice());
		dbProduct.setStock(product.getStock());
		
		return productRepository.save(dbProduct);
	} 
}
