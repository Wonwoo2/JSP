package kr.or.ddit.validate.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import kr.or.ddit.validate.rule.PasswordCheck;

public class PasswordChecker implements ConstraintValidator<PasswordCheck, String> {
	private PasswordCheck constraint;
	
	@Override
	public void initialize(PasswordCheck constraintAnnotation) {
		this.constraint = constraintAnnotation;
	}
	
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		int minLength = constraint.min();
		int maxLength = constraint.max();
		String regex = constraint.regex();
		
		int length = value != null ? value.length() : -1;
		boolean valid = length >= minLength && length <= maxLength && value.matches(regex);
		return valid;
	}
}