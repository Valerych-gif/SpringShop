package com.geekbrains.springshop.utils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordValidator implements ConstraintValidator<ValidPassword, String> {
	private Pattern pattern;
	private Matcher matcher;
	private static final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,20}$";

	@Override
	public boolean isValid(final String email, final ConstraintValidatorContext context) {
		pattern = Pattern.compile(PASSWORD_PATTERN);
		if (email == null) {
			return false;
		}
		matcher = pattern.matcher(email);
		return matcher.matches();
	}
}