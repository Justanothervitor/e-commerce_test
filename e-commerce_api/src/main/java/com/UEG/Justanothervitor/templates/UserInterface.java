package com.UEG.Justanothervitor.templates;

import com.UEG.Justanothervitor.domain.UserDomain;

public interface UserInterface{

	UserDomain FindByEmail(String email);
	UserDomain SaveUser(UserDomain obj);
	
}
