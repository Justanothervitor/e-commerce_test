package com.UEG.Justanothervitor.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.UEG.Justanothervitor.domain.RolesDomain;
import com.UEG.Justanothervitor.domain.UserDomain;
import com.UEG.Justanothervitor.repository.RolesRepository;
import com.UEG.Justanothervitor.repository.UserRepository;
import com.UEG.Justanothervitor.services.exceptions.EmailNotFoundException;

@Service
public class CustomUserDetailsServices implements UserDetailsService{
	
	
	@Autowired
	private RolesRepository roleRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private BCryptPasswordEncoder CryptAgent;
	
	private List<GrantedAuthority> getUserAuthority(Set<RolesDomain>UserRoles)
	{
		Set<GrantedAuthority> roles = new HashSet<>();
		UserRoles.forEach((role)->{
			roles.add(new SimpleGrantedAuthority(role.getRole()));
		});
		List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
		return grantedAuthorities;
	}
	
	public UserDomain returnByEmail (String email)
	{
		return userRepo.FindByEmail(email);
	}
	
	@Override
	public UserDetails loadUserByUsername(String email) throws EmailNotFoundException{
		UserDomain user = userRepo.FindByEmail(email);
		if(user != null)
		{
			List<GrantedAuthority> authority = getUserAuthority(user.getRoles());
			return buildUserForAuthetication(user,authority);
		}else {
			throw new EmailNotFoundException("User with this email not found!");
		}
	}
	
	public void CreateAUser(UserDomain obj)
	{
		UserDomain newUser = new UserDomain();
		newUser.setNome(obj.getNome());
		newUser.setEmail(obj.getEmail());
		newUser.setPassword(CryptAgent.encode(obj.getPassword()));
		newUser.setEnabled(true);
		RolesDomain userRole = roleRepo.findByRole("CLIENT");
		newUser.setRoles(new HashSet<>(Arrays.asList(userRole)));
		userRepo.SaveUser(newUser);
	}
	
	private UserDetails buildUserForAuthetication(UserDomain user, List<GrantedAuthority> authority)
	{
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),authority);
	}


}
