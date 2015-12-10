package com.sap.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sap.dao.TodoDAO;
import com.sap.dao.UserDAO;
import com.sap.models.Todo;

@Controller
public class TodoController {

	@Autowired
	private TodoDAO todoDAO;

	@Autowired
	private UserDAO userDAO;

	private int getLoggedUserId() {

		String username = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
		return this.userDAO.getUserIdByName(username);
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String showHome(Model model, HttpSession session) {

		model.addAttribute("todoList", this.todoDAO.getTodoListByUser(getLoggedUserId()));
		return "home";
	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public String removeTodo(int todoId, Model model) {

		this.todoDAO.removeTodo(todoId);
		return "redirect:";
	}

	@RequestMapping(value = "/addTodo", method = RequestMethod.GET)
	public String formAddTodo(Model model) {

		model.addAttribute("todo", new Todo());
		return "addTodo";
	}

	@RequestMapping(value = "/addTodo", method = RequestMethod.POST)
	public String addTodo(@ModelAttribute("todo") @Valid Todo todo, BindingResult bindingResult, HttpSession session) {

		if (bindingResult.hasErrors()) {
			return "addTodo";
		}

		todo.setUserId(getLoggedUserId());
		this.todoDAO.addTodo(todo);
		return "redirect:";
	}

	@RequestMapping(value = "/editTodo", method = RequestMethod.GET)
	public String handleEditTodoGet() {

		return "redirect:";
	}

	@RequestMapping(value = "/editTodo", method = RequestMethod.POST)
	public String editTodo(boolean createForm, @Valid @ModelAttribute("todo") Todo todo, BindingResult bindingResult,
			HttpSession session) {

		if (createForm || bindingResult.hasErrors()) {
			return "editTodo";
		}

		todo.setUserId(getLoggedUserId());
		this.todoDAO.updateTodo(todo);
		return "redirect:";
	}
}
