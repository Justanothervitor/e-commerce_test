package com.UEG.Justanothervitor.domain.roles;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection ="roles")
public class RolesDomain {
	
	@Id
	private String id;
	
	@Indexed(unique = true,direction = IndexDirection.DESCENDING)
	private String role;
	
	private ERoles name;
	
	public RolesDomain()
	{
		
	}
	
	public ERoles getName() {
		return name;
	}

	public void setName(ERoles name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	
}
