package com.sap.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
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

    @Autowired
	protected AuthenticationManager authenticationManager;
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String formLogin() {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			return "redirect:";
		}
		
		return "login";
	}

	@RequestMapping(value = "/signUp", method = RequestMethod.GET)
	public String signUp(Model model) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			return "redirect:";
		}
		
		model.addAttribute("user", new User());
		return "signUp";
	}

	@RequestMapping(value = "/signUp", method = RequestMethod.POST)
	public String signUp(@ModelAttribute("user") @Valid User user, BindingResult bindingResult, 
			HttpSession session, HttpServletRequest request) throws UserAlreadyExistsException {

		new UserValidator().validate(user, bindingResult);
		if (bindingResult.hasErrors()) {
			return "signUp";
		}
		
		this.userDAO.addUser(user);
		authenticateUserAndSetSession(user, request);
		return "redirect:";
	}
	
    private void authenticateUserAndSetSession(User user, HttpServletRequest request) {
        String username = user.getUsername();
        String password = user.getPassword();
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);
        
        request.getSession();

        token.setDetails(new WebAuthenticationDetails(request));
        Authentication authenticatedUser = authenticationManager.authenticate(token);

        SecurityContextHolder.getContext().setAuthentication(authenticatedUser);
    }

	@ExceptionHandler(UserAlreadyExistsException.class)
	public String handleDuplicateUser() {
		return "errors/userAlreadyExists";
	}
}
