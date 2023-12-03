package com.UEG.Justanothervitor.domain;

import java.io.Serializable;
import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import com.UEG.Justanothervitor.domain.roles.RolesDomain;

@Document(collection = "user")
public class UserDomain implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	private String id;
	@Indexed(unique = true, direction = IndexDirection.DESCENDING)
	private String username;
	private String email;
	private String password;

	@DocumentReference
	private Set<RolesDomain> roles;

	public UserDomain(String username, String email, String password) {
		super();
		this.username = username;
		this.email = email;
		this.password = password;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<RolesDomain> getRoles() {
		return roles;
	}

	public void setRoles(Set<RolesDomain> roles) {
		this.roles = roles;
	}

}
