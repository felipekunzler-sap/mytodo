package com.sap.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.multiaction.NoSuchRequestHandlingMethodException;

import com.sap.dao.TodoDAO;
import com.sap.dao.UserDAO;
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
        if(users.isEmpty()){
            return new ResponseEntity<List<User>>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<User>>(users, HttpStatus.OK);
    }
    
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @RequestMapping(value = "/todo/{id}", method = RequestMethod.GET)
    public ResponseEntity<Todo> listTodo(@PathVariable("id") int id) throws NoSuchRequestHandlingMethodException {
    	
    	Todo todo = this.todoDAO.getTodoById(id);
        if(todo == null){
    		return new ResponseEntity<Todo>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Todo>(todo, HttpStatus.OK);
    }
}
