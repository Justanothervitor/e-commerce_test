package com.UEG.Justanothervitor.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.UEG.Justanothervitor.domain.ProductDomain;
import com.UEG.Justanothervitor.domain.UserDomain;
import com.UEG.Justanothervitor.repository.ProductRepository;
import com.UEG.Justanothervitor.repository.UserRepository;

@Configuration
public class DatabaseInjection implements CommandLineRunner{
		
	@Autowired
	private MongoTemplate template;
		
	@Autowired 
	private UserRepository repoForUsers;
	
	@Autowired
	private ProductRepository repoForProducts;
	
	@Override
	public void run(String... args) throws Exception {
		
		template.dropCollection(UserDomain.class);
		template.dropCollection(ProductDomain.class);
		
		UserDomain u0 = new UserDomain(null,"Felicio Nathan Dias","firstemail@gtreco.com","passwordtoyou");
		repoForUsers.createANewUser(u0);
		
		ProductDomain p0 = new ProductDomain(null,"Pasta de dente","Serve para escovar os dente","null",10.0);
		repoForProducts.createANewProduct(p0);
		
	}

}
