package com.nextbuy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nextbuy.dto.ProductDTO;
import com.nextbuy.service.IProductService;

@RestController
@CrossOrigin
@RequestMapping("next-buy/api/product")
public class ProductController {

	@Autowired
	IProductService productService;

	@PostMapping("/save-prod")
	public ResponseEntity<String> saveProduct(@RequestBody ProductDTO productDTO) {
		productService.saveProduct(productDTO);
		return new ResponseEntity<String>("saved successfully", HttpStatus.CREATED);
	}
	
	
	@GetMapping("/find-prod/{id}")
	public ResponseEntity<ProductDTO> getOneProduct(@PathVariable("id")String id){
		ProductDTO product = productService.getOneProduct(Integer.parseInt(id));
		return new ResponseEntity<ProductDTO>(product,HttpStatus.OK);
	}
	
	
	@GetMapping("/find-all-prod")
		public ResponseEntity<List<ProductDTO>> getAllProduct(){
			List<ProductDTO> allProduct = productService.getAllProduct();
			return new ResponseEntity<List<ProductDTO>>(allProduct,HttpStatus.OK);
		}
	
	@GetMapping("/search-prod")
    public ResponseEntity<List<ProductDTO>> searchProducts(@RequestParam("keyword") String keyword) {
        List<ProductDTO> products = productService.searchProducts(keyword);
        return new ResponseEntity<List<ProductDTO>>(products,HttpStatus.OK);
    }

}
