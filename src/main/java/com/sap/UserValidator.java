package com.sap;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class UserValidator implements Validator {

	public boolean supports(Class<?> clazz) {
		
		return User.class.isAssignableFrom(clazz);
	}

	public void validate(Object target, Errors errors) {

		User user = (User) target;
		if (!user.getPassword().equals(user.getConfirmPassword())){
			errors.rejectValue("confirmPassword", "passwordNotMatch", "Passwords do not match.");
		}
	}

}
