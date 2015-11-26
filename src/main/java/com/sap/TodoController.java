package com.sap;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TodoController {
	
	@Autowired
	private TodoDAO todoDAO;
	
	@RequestMapping("/")
	public ModelAndView showHome() {
		
		ModelAndView mv = new ModelAndView("home");
		mv.addObject("todoList", this.todoDAO.getTodoList());
		
		return mv;
	}
	
	@RequestMapping("/addTodo")
	public String showAddTodo(HttpSession session) {
		
		return "addTodo";
	}
	
	@RequestMapping(value = "/checkTodo", method=RequestMethod.POST )
	public String checkTodo(@Valid Todo todo, BindingResult bindingResult) {
				
		if (bindingResult.hasErrors()){
			return "addTodo";
		}

		this.todoDAO.addTodo(todo);
		
		return "redirect:";
	}
}
