package com.fclub.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.fclub.annotation.PasswordMatches;
import com.fclub.persistence.model.User;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, Object>{

	@Override
	public void initialize(PasswordMatches arg0) { }

	@Override
	public boolean isValid(Object obj, ConstraintValidatorContext context) {
		User user = (User) obj;
		return user.getPassword().equals(user.getMatchingPassword());
	}

}
