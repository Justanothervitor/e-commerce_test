package com.UEG.Justanothervitor.config.security.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.UEG.Justanothervitor.domain.UserDomain;
import com.UEG.Justanothervitor.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{
	
	@Autowired
	UserRepository userRepo;
	
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
			UserDomain user = this.userRepo.FindByUsername(username);
			if(user == null)
			{
				throw(new UsernameNotFoundException("User not found with username:"+username));
			}
		return UserDetailsImpl.build(user);
	}

}
