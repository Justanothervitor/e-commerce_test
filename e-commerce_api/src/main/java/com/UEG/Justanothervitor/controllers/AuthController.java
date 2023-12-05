package com.UEG.Justanothervitor.controllers;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.UEG.Justanothervitor.config.jwt.JwtUtils;
import com.UEG.Justanothervitor.config.security.services.UserDetailsImpl;
import com.UEG.Justanothervitor.domain.UserDomain;
import com.UEG.Justanothervitor.domain.roles.ERoles;
import com.UEG.Justanothervitor.domain.roles.RolesDomain;
import com.UEG.Justanothervitor.payload.request.LoginRequest;
import com.UEG.Justanothervitor.payload.request.SignupRequest;
import com.UEG.Justanothervitor.payload.response.JwtResponse;
import com.UEG.Justanothervitor.payload.response.MessageResponse;
import com.UEG.Justanothervitor.repository.RolesRepository;
import com.UEG.Justanothervitor.repository.UserRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/auth")
public class AuthController {

	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	RolesRepository roleRepo;
	
	@Autowired
	PasswordEncoder encoder;
	
	@Autowired
	JwtUtils jwtUtils;
	
	@PostMapping(value="/login")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest)
	{
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),loginRequest.getPassword()));
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		String jwt = jwtUtils.generateJwtToken(authentication);
		
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
		List<String> roles = userDetails.getAuthorities().stream()
				.map(item-> item.getAuthority())
				.collect(Collectors.toList());
		
		return ResponseEntity.ok(new JwtResponse(jwt,
				userDetails.getId(),
				userDetails.getUsername(),
				userDetails.getEmail(),
				userDetails.getPassword(),
				roles));
	}
	
	@PostMapping(value="/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signupRequest)
	{
		if(userRepo.verifyUser(signupRequest.getUsername())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error:Username is already taken!"));
		}
		if(userRepo.verifyEmail(signupRequest.getEmail()))
		{
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error:Email is already taken!"));
		}
		
		UserDomain user = new UserDomain(signupRequest.getUsername(),
				signupRequest.getEmail(),
				encoder.encode(signupRequest.getPassword()));
		
		Set<String> strRoles = signupRequest.getRoles();
		Set<RolesDomain> roles = new HashSet<>();
		
		if(strRoles == null)
		{
			RolesDomain userRole = roleRepo.findByRole(ERoles.USER);
			roles.add(userRole);
		}else {
			strRoles.forEach(role ->{
				switch(role) {
				
				case "admin":
					RolesDomain adminRole = roleRepo.findByRole(ERoles.ADMIN);
					roles.add(adminRole);
					
					break;
					
				default:
					RolesDomain userRole = roleRepo.findByRole(ERoles.USER);
					roles.add(userRole);
				}
			});
		}
		user.setRoles(roles);
		userRepo.SaveUser(user);
		
		return ResponseEntity.ok(new MessageResponse("User registered sucessfully"));
	}
	
}
