package com.pon.engine.service.impl;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pon.engine.domain.Product;
import com.pon.engine.domain.repository.ProductRepository;
import com.pon.engine.service.OrderService;


@Service
public class OrderServiceImpl implements OrderService{

	@Autowired
	private ProductRepository productRepository;
	

	public void processOrder(String productId, int count) {
Product productById = productRepository.getProductById(productId);
		
		if(productById.getUnitsInStock() < count){
			throw new IllegalArgumentException("Out of Stock. Available Units in stock"+ productById.getUnitsInStock());
		}
		
		productById.setUnitsInStock(productById.getUnitsInStock() - count);
		
	}
}
