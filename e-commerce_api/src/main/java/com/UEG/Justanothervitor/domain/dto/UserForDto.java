package com.UEG.Justanothervitor.domain.dto;

import java.io.Serializable;
import java.util.Optional;

import com.UEG.Justanothervitor.domain.UserDomain;
import com.UEG.Justanothervitor.services.exceptions.ObjectNotFoundException;

public class UserForDto implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String id;
	private String nome;
	
	public UserForDto (Optional<UserDomain> obj)
	{
		this(obj.orElseThrow(()-> new ObjectNotFoundException("Objeto não encontrado")));
	}

	public UserForDto(UserDomain obj) {
		super();
		this.id = obj.getId();
		this.nome = obj.getNome();
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
	
	
}
