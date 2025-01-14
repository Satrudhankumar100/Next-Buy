package com.nextbuy.service;

import java.util.List;

import com.nextbuy.dto.ProductDTO;
import com.nextbuy.entity.ProductEntity;

public interface IProductService {

	void saveProduct(ProductDTO productDTO);

	ProductDTO getOneProduct(Integer id);
	
	List<ProductDTO> getAllProduct();
	
	List<ProductDTO> searchProducts(String search);
	
	
	ProductEntity getProductEntity(Integer prodId);

}
