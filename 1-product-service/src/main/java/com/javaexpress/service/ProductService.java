package com.javaexpress.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaexpress.dto.ProductRequestDto;
import com.javaexpress.dto.ProductResponseDto;
import com.javaexpress.exception.ProductNotFoundException;
import com.javaexpress.models.Product;
import com.javaexpress.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;
	
	public Product save(Product product) {
		return productRepository.save(product);
	}
	
	public ProductResponseDto save(ProductRequestDto request) {
		//convert request to entity object
		Product product = new Product();
		product.setName(request.getName());
		product.setDescription(request.getDescription());
		product.setPrice(request.getPrice());
		product.setStock(request.getStock());
		
		var dbproduct = productRepository.save(product); 
		
		// convert entity to response object
		ProductResponseDto productResponseDto = new ProductResponseDto();
		BeanUtils.copyProperties(dbproduct, productResponseDto);
		return productResponseDto;		
	}
	
	public ProductResponseDto getProduct(Long productid)
	{
		Product dbproduct = productRepository.findById(productid).
				orElseThrow(()-> new ProductNotFoundException("Product not found with id " + productid));
		
		ProductResponseDto responseDto = new ProductResponseDto();
		BeanUtils.copyProperties(dbproduct, responseDto);
		return responseDto;
	}
	
	public Product findById(Long productid) {
		return productRepository.findById(productid).
				orElseThrow(()-> new ProductNotFoundException("Product not found with id " + productid));
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
