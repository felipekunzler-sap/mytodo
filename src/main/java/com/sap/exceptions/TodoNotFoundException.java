package com.sap.exceptions;

public class TodoNotFoundException extends NotFoundException {

	private static final long serialVersionUID = 1L;
	private int todoId;
	
	public TodoNotFoundException(int todoId){
		this.todoId = todoId;
	}
	
	public int getTodoId(){
		return this.todoId;
	}
	
	@Override
	public String getMessage() {
		return "Todo [" + todoId + "] not found.";
	}
}
