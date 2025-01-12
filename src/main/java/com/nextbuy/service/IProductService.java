package com.nextbuy.service;

import java.util.List;

import com.nextbuy.dto.ProductDTO;

public interface IProductService {

	void saveProduct(ProductDTO productDTO);

	ProductDTO getOneProduct(Integer id);
	
	List<ProductDTO> getAllProduct();
	
	List<ProductDTO> searchProducts(String search);

}
