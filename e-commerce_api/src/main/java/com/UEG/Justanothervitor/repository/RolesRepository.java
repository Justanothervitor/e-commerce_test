package com.UEG.Justanothervitor.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.UEG.Justanothervitor.domain.RolesDomain;
import com.UEG.Justanothervitor.templates.RolesInterface;

@Repository
public class RolesRepository implements RolesInterface {
	
	@Autowired
	private MongoTemplate template;
	
	@Override
	public RolesDomain findByRole(String role) {
		Query query = new Query();
		query.addCriteria(Criteria.where("role").is(role));
		return this.template.findOne(query,RolesDomain.class);
	}
	
	@Override
	public RolesDomain save (RolesDomain role)
	{
		return this.template.save(role);
	}
	

}
