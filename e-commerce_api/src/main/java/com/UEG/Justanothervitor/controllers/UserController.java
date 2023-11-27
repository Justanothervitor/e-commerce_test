package com.UEG.Justanothervitor.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.UEG.Justanothervitor.domain.UserDomain;
import com.UEG.Justanothervitor.services.CustomUserDetailsServices;

import jakarta.validation.Valid;

@Controller
public class UserController {

	@Autowired
	private CustomUserDetailsServices loginService;
	
	@GetMapping(value ="/login")
	public ModelAndView login()
	{
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("login");
		return modelAndView;
	}
	
	@GetMapping(value = "/signup")
	public ModelAndView signup()
	{
		ModelAndView modelAndView = new ModelAndView();
		UserDomain user = new UserDomain();
		modelAndView.addObject("users", user);
		modelAndView.setViewName("signup");
		return modelAndView;
		
	}
	
	@PostMapping(value= "/signup")
	public ModelAndView createANewUser(@Valid UserDomain user,BindingResult bidingResult)
	{
		ModelAndView modelAndView = new ModelAndView();
		UserDomain obj = loginService.returnByEmail(user.getEmail());
		if(obj != null)
		{
			bidingResult
			.rejectValue("email","error.user","There is already a user with this email");
		}
		if(bidingResult.hasErrors())
		{
			modelAndView.setViewName("signup");
		}else {
			loginService.CreateAUser(obj);
			modelAndView.addObject("sucessMessage","User was been registered");
			modelAndView.addObject("users", new UserDomain());
			modelAndView.setViewName("login");
		}
		return modelAndView;
	}
	
	@GetMapping(value = "/dashboard")
	public ModelAndView dashboard() {
	    ModelAndView modelAndView = new ModelAndView();
	    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    UserDomain user = loginService.returnByEmail(auth.getName());
	    modelAndView.addObject("currentUser", user);
	    modelAndView.addObject("fullName", "Welcome " + user.getNome());
	    modelAndView.addObject("want to see your product history?");
	    modelAndView.setViewName("dashboard");
	    return modelAndView;
	}
	
	@GetMapping(value = {"/","/home"})
	public ModelAndView home() {
	    ModelAndView modelAndView = new ModelAndView();
	    modelAndView.setViewName("home");
	    return modelAndView;
	}
}
