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
		return template.findOne(query, UserDomain.class);
	}
	
	@Override
	public UserDomain SaveUser (UserDomain obj)
	{
		return this.template.save(obj);
	}
}
