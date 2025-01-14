package com.nextbuy.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nextbuy.dto.ProductDTO;
import com.nextbuy.entity.ProductEntity;
import com.nextbuy.exception.ProductNotFoundException;
import com.nextbuy.repo.ProductRepository;
import com.nextbuy.service.IProductService;

@Service
public class ProductServiceImpl implements IProductService {

	@Autowired
	private ProductRepository productRepo;

	@Override
	public void saveProduct(ProductDTO productDTO) {
		ProductEntity entity = new ProductEntity();
		BeanUtils.copyProperties(productDTO, entity);
		productRepo.save(entity);
	}

	@Override
	public ProductDTO getOneProduct(Integer id) {
		ProductEntity entity = productRepo.findById(id)
				.orElseThrow(() -> new ProductNotFoundException("Product Not Found!!!"));
		ProductDTO productDTO = new ProductDTO();
		BeanUtils.copyProperties(entity, productDTO);
		return productDTO;
	}

	@Override
	public List<ProductDTO> getAllProduct() {
		List<ProductDTO> products = productRepo.findAll().stream().map(entity -> {
			ProductDTO productDTO = new ProductDTO();
			BeanUtils.copyProperties(entity, productDTO);
			return productDTO;
		}).collect(Collectors.toList());
		return products;
	}

	@Override
	public List<ProductDTO> searchProducts(String keyword) {
		return productRepo.searchByNameOrTitle(keyword).stream().map(entity -> {
			ProductDTO productDTO = new ProductDTO();
			BeanUtils.copyProperties(entity, productDTO);
			return productDTO;
		}).collect(Collectors.toList());
	}

	@Override
	public ProductEntity getProductEntity(Integer prodId) {
		return productRepo.findById(prodId).orElseThrow(() -> new ProductNotFoundException("Product Not Found!!!"));

	}

}
