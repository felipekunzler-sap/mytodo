package com.sap.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.sap.dao.TodoDAO;
import com.sap.dao.UserDAO;
import com.sap.exceptions.Error;
import com.sap.exceptions.NotFoundException;
import com.sap.exceptions.TodoNotFoundException;
import com.sap.exceptions.UserNotFoundException;
import com.sap.models.Todo;
import com.sap.models.User;

@RequestMapping(value = "/api")
@RestController
public class MyTodoRestController {

	@Autowired
	private UserDAO userDAO;

	@Autowired
	private TodoDAO todoDAO;

	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public ResponseEntity<List<User>> listAllUsers() {

		List<User> users = userDAO.getUserList();
		if (users.isEmpty()) {
			return new ResponseEntity<List<User>>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<User>>(users, HttpStatus.OK);
	}

	@RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
	public User listUser(@PathVariable("id") int id) {

		User user = this.userDAO.getUserById(id);
		if (user == null)
			throw new UserNotFoundException(id);
		return user;
	}

	@RequestMapping(value = "/todos", method = RequestMethod.GET)
	public ResponseEntity<List<Todo>> listAllTodos() {

		List<Todo> todos = this.todoDAO.getTodoList();
		if (todos.isEmpty()) {
			return new ResponseEntity<List<Todo>>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<Todo>>(todos, HttpStatus.OK);
	}

	@RequestMapping(value = "/todo/{id}", method = RequestMethod.GET)
	public Todo listTodo(@PathVariable("id") int id) {

		Todo todo = this.todoDAO.getTodoById(id);
		if (todo == null)
			throw new TodoNotFoundException(id);
		return todo;
	}

	@ExceptionHandler(NotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public Error handlesNotFound(NotFoundException exception) {
		return new Error(exception.getMessage());
	}
}
