package com.UEG.Justanothervitor.domain;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

@Document(collection ="user")
public class UserDomain implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	private String id;
	@Indexed(unique = true,direction = IndexDirection.DESCENDING)
	private String nome;
	private String email;
	private String password;
	private Boolean enabled;
	@DocumentReference
	private Set<RolesDomain> roles;
	
	public UserDomain()
	{
		
	}
	
	public UserDomain(String id, String nome, String email, String password,Boolean enabled,Set<RolesDomain> roles) {
		super();
		this.id = id;
		this.enabled = enabled;
		this.nome = nome;
		this.roles = roles;
		this.email = email;
		this.password = password;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
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
	

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public void setRoles(Set<RolesDomain> roles) {
		this.roles = roles;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserDomain other = (UserDomain) obj;
		return Objects.equals(id, other.id);
	}
	
	

}
