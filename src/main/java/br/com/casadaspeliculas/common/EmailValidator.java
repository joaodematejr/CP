package br.com.casadaspeliculas.common;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EmailValidator implements ConstraintValidator<Email, String>{
	
	private Pattern patter = Pattern.compile(".+@.+\\.[a-z]+");

	@Override
	public void initialize(Email email) {
		
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		Matcher m = patter.matcher(value);
		return m.matches();
	}

}
