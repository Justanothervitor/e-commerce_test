package com.UEG.Justanothervitor.domain.dto;

import org.springframework.data.annotation.Id;

import com.UEG.Justanothervitor.domain.UserDomain;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class LoginDto {
	
	@JsonIgnore
	@Id
	private String id;
	
	private String email;
	
	private String password;
	
	public LoginDto(UserDomain obj)
	{
		this.id = obj.getId();
		this.email = obj.getEmail();
		this.password = obj.getPassword();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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
	
	
	
}
