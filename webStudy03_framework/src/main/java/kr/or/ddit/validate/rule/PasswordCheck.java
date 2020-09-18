package kr.or.ddit.validate.rule;

import static java.lang.annotation.ElementType.*;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import kr.or.ddit.validate.validator.PasswordChecker;

@Retention(RetentionPolicy.RUNTIME)
@Target({FIELD, METHOD})
@Constraint(validatedBy = PasswordChecker.class)
public @interface PasswordCheck {
	int min() default 8;
	int max() default 15;
	String regex() default "(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[!@#$%^&*]).*";
	
	String message() default "{kr.or.ddit.validate.rule.PasswordCheck.message}";
	
	Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}