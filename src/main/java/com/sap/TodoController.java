package com.sap;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TodoController {
	
	@Autowired
	private TodoDAO todoDAO;
	
	@RequestMapping(value = "/", method=RequestMethod.GET)
	public ModelAndView showHome() {
		
		ModelAndView mv = new ModelAndView("home");
		mv.addObject("todoList", this.todoDAO.getTodoList());
		
		return mv;
	}
	
	@RequestMapping(value = "/", method=RequestMethod.POST)
	public String removeTodo(int todoId, Model model) {
		
		this.todoDAO.removeTodo(todoId);
		model.addAttribute("todoList", this.todoDAO.getTodoList());
		
		return "home";
	}
	
	@RequestMapping(value = "/addTodo", method=RequestMethod.GET)
	public String formAddTodo(Model model) {
		
		model.addAttribute("todo", new Todo());				
		
		return "addTodo";
	}
	
	@RequestMapping(value = "/addTodo", method=RequestMethod.POST )
	public String addTodo(@ModelAttribute("todo") @Valid Todo todo, BindingResult bindingResult) {
				
		if (bindingResult.hasErrors()){
			return "addTodo";
		}

		this.todoDAO.addTodo(todo);
		
		return "redirect:";
	}
	
	@RequestMapping(value = "/editTodo", method=RequestMethod.POST )
	public String editTodo(boolean createForm, @Valid @ModelAttribute("todo") Todo todo, BindingResult bindingResult) {
		
		if (createForm || bindingResult.hasErrors()){
			return "editTodo";
		}
			
		this.todoDAO.updateTodo(todo);
		
		return "redirect:";
	}	
}
