package com.UEG.Justanothervitor.templates;

import com.UEG.Justanothervitor.domain.UserDomain;

public interface UserInterface{

	UserDomain FindByEmail(String email);
	UserDomain FindByUsername(String username);
	UserDomain SaveUser(UserDomain obj);
	Boolean verifyUser(String username);
	Boolean verifyEmail(String email);
}
