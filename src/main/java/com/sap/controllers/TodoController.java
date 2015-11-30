package com.sap.controllers;


import com.sap.*;

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
public class TodoController {
	
	@Autowired
	private TodoDAO todoDAO;
	
	@Autowired 
	UserDAO userDAO;
	
	@RequestMapping(value = "/", method=RequestMethod.GET)
	public String showHome(Model model, HttpSession session) {
		
		User currentUser = (User) session.getAttribute("currentUser");		
		model.addAttribute("todoList", this.todoDAO.getTodoListByUser(currentUser.getId()));
		
		return "home";
	}
	
	@RequestMapping(value = "/", method=RequestMethod.POST)
	public String removeTodo(int todoId, Model model) {
		
		this.todoDAO.removeTodo(todoId);
		
		return "redirect:";
	}
	
	@RequestMapping(value = "/addTodo", method=RequestMethod.GET)
	public String formAddTodo(Model model) {
		
		model.addAttribute("todo", new Todo());				
		
		return "addTodo";
	}
	
	@RequestMapping(value = "/addTodo", method=RequestMethod.POST )
	public String addTodo(@ModelAttribute("todo") @Valid Todo todo, BindingResult bindingResult, HttpSession session) {
				
		if (bindingResult.hasErrors()){
			return "addTodo";
		}

		User currentUser = (User) session.getAttribute("currentUser");
		todo.setUserId(currentUser.getId());
		this.todoDAO.addTodo(todo);
		
		return "redirect:";
	}
	
	@RequestMapping(value = "/editTodo", method=RequestMethod.GET)
	public String handleEditTodoGet() {
		
		return "redirect:";
	}
	
	@RequestMapping(value = "/editTodo", method=RequestMethod.POST )
	public String editTodo(boolean createForm, @Valid @ModelAttribute("todo") Todo todo, 
			BindingResult bindingResult, HttpSession session) {
		
		System.out.println(todo);
		
		if (createForm || bindingResult.hasErrors()){
			return "editTodo";
		}
		
		this.todoDAO.updateTodo(todo);
		
		return "redirect:";
	}	
}
