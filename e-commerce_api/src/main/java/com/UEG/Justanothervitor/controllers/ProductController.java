package com.UEG.Justanothervitor.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.UEG.Justanothervitor.domain.ProductDomain;
import com.UEG.Justanothervitor.repository.ProductRepository;

@RestController
@RequestMapping("api/products")
public class ProductController {

	@Autowired
	private ProductRepository repo;
	
	@GetMapping(value="/getproducts")
	public ResponseEntity<List<ProductDomain>> getAll()
	{
		List<ProductDomain> products = this.repo.findAll();
		return ResponseEntity.ok().body(products);	
	}
	
	@GetMapping(value="/{id}")
	public ResponseEntity<ProductDomain> getById(@PathVariable String id)
	{
		ProductDomain product = this.repo.findById(id);
		return ResponseEntity.ok(product);
	}
	
	@PostMapping(value="addproduct")
	public ResponseEntity<ProductDomain> insert (@RequestBody ProductDomain obj)
	{
		return new ResponseEntity<ProductDomain>(this.repo.createANewProduct(obj),HttpStatus.CREATED);
	}
	
	@DeleteMapping(value="delete/{id}")
	public ResponseEntity<Void> delete(@PathVariable String id)
	{
		this.repo.deleteAProduct(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping(value ="update/{id}")
	public ResponseEntity<ProductDomain> update (@PathVariable String id, @RequestBody ProductDomain obj)
	{
		ProductDomain updated = this.repo.updateAProduct(id, obj);
		return ResponseEntity.ok().body(updated);
	}
}