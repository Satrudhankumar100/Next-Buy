package com.nextbuy.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
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
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.map(productDTO, entity);
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
	    List<ProductEntity> entities = productRepo.findAll();
	    List<ProductDTO> productDTOList = new ArrayList<>();
	    for (ProductEntity entity : entities) {
	        ProductDTO productDTO = new ProductDTO();
	        BeanUtils.copyProperties(entity, productDTO);
	        productDTOList.add(productDTO);
	    }
	    return productDTOList;
	}
	
	
	  public List<ProductDTO> searchProducts(String keyword) {
	        List<ProductEntity> entities = productRepo.searchByNameOrTitle(keyword);
	        List<ProductDTO> productDTOList = new ArrayList<>();

	        for (ProductEntity entity : entities) {
	            ProductDTO productDTO = new ProductDTO();
	            BeanUtils.copyProperties(entity, productDTO);
	            productDTOList.add(productDTO);
	        }

	        return productDTOList;
	    }
	


}
