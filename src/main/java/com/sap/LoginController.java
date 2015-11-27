package com.sap;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {
	
	@Autowired
	private UserDAO userDAO;
	
	@RequestMapping(value = "/login", method=RequestMethod.GET )
	public String formLogin(Model model, HttpSession session) {
				
		String currentUser = (String) session.getAttribute("currentUser");
		if (currentUser != null){
			return "redirect:";
		}
		
		model.addAttribute("user", new User());
		
		return "login";
	}
	
	@RequestMapping(value = "/logout", method=RequestMethod.GET )
	public String formLogin(HttpSession session) {
		
		session.removeAttribute("currentUser");
				
		return "redirect:";
	}
	
	@RequestMapping(value = "/login", method=RequestMethod.POST )
	public String login(@ModelAttribute("user") @Valid User user, BindingResult bindingResult, HttpSession session, Model model) {
						
		if (bindingResult.hasErrors()){
			System.out.println("has erros");
			return "login";
		}
		
		if (this.userDAO.checkCredentials(user)){
			
			session.setAttribute("currentUser", user.getUsername());
			return "redirect:";
		}
		
		System.out.println("not found");
		model.addAttribute("notFound", "Credentials were not found in the server.");
		return "login";		
	}
	
	@RequestMapping(value = "/signUp", method=RequestMethod.GET)
	public String signUp(Model model){
		
		model.addAttribute("user", new User());
		
		return "signUp";
	}
	
	@RequestMapping(value = "/signUp", method=RequestMethod.POST )
	public String signUp(@ModelAttribute("user") @Valid User user, BindingResult bindingResult, HttpSession session) {
						
		new UserValidator().validate(user, bindingResult);
		if (bindingResult.hasErrors()){
			return "signUp";
		}
		
		this.userDAO.addUser(user);
			
		session.setAttribute("currentUser", user.getUsername());
		return "redirect:";	
	}
}
