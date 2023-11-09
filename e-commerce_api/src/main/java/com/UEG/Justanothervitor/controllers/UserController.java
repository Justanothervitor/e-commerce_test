package com.UEG.Justanothervitor.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.UEG.Justanothervitor.domain.UserDomain;
import com.UEG.Justanothervitor.domain.dto.UserForDto;
import com.UEG.Justanothervitor.repository.UserRepository;

@RestController(value="users")
@RequestMapping
public class UserController {

	@Autowired
	private UserRepository repo;
	
	@GetMapping(value="users")
	public ResponseEntity<List<UserForDto>> getAll()
	{
		List<UserDomain> users = this.repo.findAll();
		List<UserForDto> list = users.stream().map(x -> new UserForDto(x)).collect(Collectors.toList());
		return ResponseEntity.ok().body(list);	
	}
	
	@GetMapping(value="/users/{id}")
	public ResponseEntity<UserForDto> getById(@PathVariable String id)
	{
		UserDomain user = this.repo.findById(id);
		return ResponseEntity.ok().body(new UserForDto(user));
	}
	
	@PostMapping(value="users/")
	public ResponseEntity<UserDomain> insert (@RequestBody UserDomain obj)
	{
		return new ResponseEntity<UserDomain>(this.repo.createANewUser(obj),HttpStatus.CREATED);
	}
	
	@DeleteMapping(value="users/{id}")
	public ResponseEntity<Void> delete(@PathVariable String id)
	{
		this.repo.deleteAUser(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping(value ="users/{id}")
	public ResponseEntity<UserDomain> update (@PathVariable String id, @RequestBody UserDomain obj)
	{
		UserDomain updated = this.repo.updateAUser(id, obj);
		return ResponseEntity.ok().body(updated);
	}
}
