package com.UEG.Justanothervitor.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.UEG.Justanothervitor.domain.roles.ERoles;
import com.UEG.Justanothervitor.domain.roles.RolesDomain;
import com.UEG.Justanothervitor.templates.RolesInterface;

@Repository
public class RolesRepository implements RolesInterface {
	
	@Autowired
	private MongoTemplate template;
	
	@Override
	public RolesDomain findByRole(ERoles role) {
		Query query = new Query();
		query.addCriteria(Criteria.where("name").is(role));
		RolesDomain foundRole = this.template.findOne(query,RolesDomain.class);
		return foundRole;
	}
	
	@Override
	public RolesDomain save (RolesDomain role)
	{
		return this.template.save(role);
	}
	

}
