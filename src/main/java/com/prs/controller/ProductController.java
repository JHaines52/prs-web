package com.prs.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import com.prs.db.ProductRepo;
import com.prs.model.LineItem;
import com.prs.model.Product;




@CrossOrigin
@RestController
@RequestMapping("/api/products")

public class ProductController {
	@Autowired  //Initializes create variable 
	private ProductRepo productRepo;
	
	@GetMapping("/")
	public List<Product> getAllProducts() {
		return productRepo.findAll();
	}
	
	@GetMapping("/{id}")
	public Product getProductById(@PathVariable int id) {
		Optional<Product> p = productRepo.findById(id);
		if (p.isPresent()) {
			return p.get();
		}
		else {
			throw new ResponseStatusException(
					HttpStatus.NOT_FOUND, "Product Not Found");
		}
		
	}
	
	@PostMapping("")
	public Product addProduct(@RequestBody Product product) {
		
		//TODO Check for existence by product.getId()before save
		return productRepo.save(product);
		
	}
	@PutMapping("/{id}")
	public Product updateProduct(@PathVariable int id, @RequestBody Product product) {
		
		Product p = null;
		
		if(id != product.getId()) {
			System.err.println("Product id ["+product.getId()+"] not match path id.");
			throw new ResponseStatusException(
					HttpStatus.BAD_REQUEST, "Product Not Found");
		}
		else if(!productRepo.existsById(id)){
			System.err.println("Product does not exist for id: "+id);
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product Not Found");
		}
		else {
			try {
				p = productRepo.save(product);
			}
			catch (Exception e) {
				System.err.println(e);
				throw e;
			}
			
		}
		return p;
	}
	@DeleteMapping("/{id}")
	public boolean deleteProduct(@PathVariable int id) {
		boolean success = false;
		if (productRepo.existsById(id)) {
			productRepo.deleteById(id);
			success = true;
		}
		else {
			System.err.println("Delete Error: No product exists for id: "+id);
			success = false;
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product Not Found");
		}
		return success;
	}

}
