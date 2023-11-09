package com.UEG.Justanothervitor.templates;

import java.util.List;

import com.UEG.Justanothervitor.domain.ProductDomain;

public interface ProductsInterface{

	List<ProductDomain> findAll();
	
	ProductDomain findById(String id);
	
	ProductDomain createANewProduct(ProductDomain product);
	
	void deleteAProduct(String id);
	
	ProductDomain updateAProduct(String id, ProductDomain product);
	
}
