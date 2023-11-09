package com.UEG.Justanothervitor.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.UEG.Justanothervitor.domain.ProductDomain;
import com.UEG.Justanothervitor.templates.ProductsInterface;

@Repository
public class ProductRepository implements ProductsInterface{
	
	@Autowired
	private MongoTemplate template;

	@Override
	public List<ProductDomain> findAll() {
		List<ProductDomain> products = this.template.findAll(ProductDomain.class) ;
		return products;
	}

	@Override
	public ProductDomain findById(String id) {
		Query query = new Query();
		query.addCriteria(Criteria.where("productId").is(id));
		ProductDomain product = this.template.findOne(query,ProductDomain.class);
		return product;
	}

	@Override
	public ProductDomain createANewProduct(ProductDomain product) {
		return this.template.save(product);
	}

	@Override
	public void deleteAProduct(String id) {
		Query query = new Query();
		query.addCriteria(Criteria.where("productId").is(id));
		this.template.remove(query,ProductDomain.class);
		
	}

	@Override
	public ProductDomain updateAProduct(String id, ProductDomain product) {
		Query query = new Query();
		query.addCriteria(Criteria.where("productId").is(id));
		Update update = new Update();
		ProductDomain change = this.template.findOne(query,ProductDomain.class);
		change.setProductName(product.getProductName());
		change.setDescription(product.getDescription());
		change.setLinkToImage(product.getLinkToImage());
		change.setPrice(product.getPrice());
		update.set("productName:",change.getProductName()).set("description:", change.getDescription()).set("linkToImage:", change.getLinkToImage()).set("price", change.getPrice());
		template.updateFirst(query, update, ProductDomain.class);
		return change;
	}
	
}
