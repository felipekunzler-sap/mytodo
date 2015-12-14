package com.sap.exceptions;

public class UserNotFoundException extends NotFoundException {

	private static final long serialVersionUID = 1L;
	private int userId;
	
	public UserNotFoundException(int userId){
		this.userId = userId;
	}
	
	public int getUserId(){
		return this.userId;
	}
	
	@Override
	public String getMessage() {
		return "User [" + userId + "] not found.";
	}
}
