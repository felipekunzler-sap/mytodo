package com.sap.controllers;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sap.User;
import com.sap.UserDAO;
import com.sap.UserValidator;
import com.sap.exceptions.UserAlreadyExistsException;

@Controller
public class LoginController {
	
	@Autowired
	private UserDAO userDAO;
	
	@RequestMapping(value = "/login", method=RequestMethod.GET )
	public String formLogin(Model model, HttpSession session) {
				
		User currentUser = (User) session.getAttribute("currentUser");
		if (currentUser != null){
			return "redirect:";
		}
		
		model.addAttribute("user", new User());
		
		return "login";
	}
	
	@RequestMapping(value = "/logout", method=RequestMethod.GET )
	public String formLogin(HttpSession session, HttpServletResponse response) {
        		
		
		session.removeAttribute("currentUser");
		session.invalidate();
		
		return "redirect:";
	}
	
	@RequestMapping(value = "/login", method=RequestMethod.POST )
	public String login(@ModelAttribute("user") @Valid User user, BindingResult bindingResult, HttpSession session, Model model) {
						
		if (bindingResult.hasErrors()){
			return "login";
		}
		
		if (this.userDAO.checkCredentials(user)){
			
			int userId = this.userDAO.getUserByName(user.getUsername()).getId();
			user.setId(userId);
			session.setAttribute("currentUser", user);
			return "redirect:";
		}
		
		model.addAttribute("notFound", "Credentials were not found in the server.");
		return "login";		
	}
	
	@RequestMapping(value = "/signUp", method=RequestMethod.GET)
	public String signUp(Model model){
		
		model.addAttribute("user", new User());
		
		return "signUp";
	}
	
	@RequestMapping(value = "/signUp", method=RequestMethod.POST )
	public String signUp(@ModelAttribute("user") @Valid User user, BindingResult bindingResult, HttpSession session) throws UserAlreadyExistsException {
						
		new UserValidator().validate(user, bindingResult);
		if (bindingResult.hasErrors()){
			return "signUp";
		}
		
		this.userDAO.addUser(user);
		int userId = this.userDAO.getUserByName(user.getUsername()).getId();
		user.setId(userId);
		session.setAttribute("currentUser", user);
		return "redirect:";	
	}
	
	@ExceptionHandler(UserAlreadyExistsException.class)
	public String handleDuplicateUser() {
		return "errors/userAlreadyExists";
	}
}
