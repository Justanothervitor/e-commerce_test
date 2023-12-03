package com.UEG.Justanothervitor.repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.UEG.Justanothervitor.domain.UserDomain;
//import com.UEG.Justanothervitor.services.exceptions.DatabaseException;
import com.UEG.Justanothervitor.templates.UserInterface;

@Repository
public class UserRepository implements UserInterface{

	@Autowired
	private MongoTemplate template;
	
	@Override
	public UserDomain FindByEmail(String email) {
		Query query = new Query();
		query.addCriteria(Criteria.where("email").is(email));
		UserDomain user = template.findOne(query, UserDomain.class);
		return user;
	}
	
	@Override
	public UserDomain FindByUsername(String username)
	{
		Query query = new Query();
		query.addCriteria(Criteria.where("username").is(username));
		return this.template.findOne(query, UserDomain.class);
	}
	
	@Override
	public UserDomain SaveUser (UserDomain obj)
	{
		return this.template.save(obj);
	}
	
	@Override
	public Boolean verifyUser (String username)
	{
		Query query = new Query();
		query.addCriteria(Criteria.where("username").is(username));
		if(this.template.exists(query,UserDomain.class)) {
			return true;
		}else {
			return false;
		}
	}
	
	@Override
	public Boolean verifyEmail (String email)
	{
		Query query = new Query();
		query.addCriteria(Criteria.where("email").is(email));
		if(this.template.exists(query,UserDomain.class)) {
			return true;
		}else {
			return false;
		}
	}
	
}
