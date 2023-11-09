package com.UEG.Justanothervitor.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.UEG.Justanothervitor.domain.UserDomain;
import com.UEG.Justanothervitor.templates.UserInterface;

@Repository
public class UserRepository implements UserInterface{

	@Autowired
	private MongoTemplate template;
	
	@Override
	public List<UserDomain> findAll() {
		List<UserDomain> users = template.findAll(UserDomain.class);
		return users;
	}

	@Override
	public UserDomain findById(String id) {
		Query query = new Query();
		query.addCriteria(Criteria.where("id").is(id));
		return template.findOne(query, UserDomain.class);
	}

	@Override
	public UserDomain createANewUser(UserDomain user) {
		template.save(user);
		return user;
	}

	@Override
	public void deleteAUser(String id) {
		Query query = new Query();
		query.addCriteria(Criteria.where("id").is(id));
		template.remove(query, UserDomain.class);
		
	}

	@Override
	public UserDomain updateAUser(String id, UserDomain user) {
		Query query = new Query();
		query.addCriteria(Criteria.where("id").is(id));
		Update update = new Update();
		UserDomain change = this.template.findOne(query,UserDomain.class);
		change.setNome(user.getNome());
		change.setEmail(user.getEmail());
		change.setPassword(user.getPassword());
		update.set("nome:",change.getNome()).set("email:", change.getEmail()).set("password:", change.getPassword());
		template.updateFirst(query, update, UserDomain.class);
		return change;
	}
	

}
