package com.UEG.Justanothervitor.templates;

import java.util.List;

import com.UEG.Justanothervitor.domain.UserDomain;

public interface UserInterface{

	List<UserDomain> findAll();
	
	UserDomain findById(String id);
	
	UserDomain createANewUser(UserDomain user);
	
	void deleteAUser(String id);
	
	UserDomain updateAUser(String id, UserDomain user);
	
	
	
}
