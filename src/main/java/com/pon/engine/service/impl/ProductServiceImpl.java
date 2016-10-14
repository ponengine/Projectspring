package com.pon.engine.service.impl;



import java.util.List;
import java.util.Map;
import java.util.Set;

import com.pon.engine.domain.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pon.engine.domain.repository.ProductRepository;
import com.pon.engine.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService{
	@Autowired
	private ProductRepository productrepository;
	
	public List<Product> getAllProducts() {
		return productrepository.getAllProducts();
	}
	public Product getProductById(String productID) {
		
	return productrepository.getProductById(productID);
	}
	public List<Product> getProductsByCategory(String category) {
		return productrepository.getProductsByCategory(category);
		}
	public Set<Product> getProductsByFilter(Map<String, List<String>> filterParams) {
	
	return productrepository.getProductsByFilter(filterParams);
	}
	public void addProduct(Product product) {
		productrepository.addProduct(product);
		}
}
