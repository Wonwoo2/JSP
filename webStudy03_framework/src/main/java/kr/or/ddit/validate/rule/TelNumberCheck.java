package kr.or.ddit.validate.rule;

import static java.lang.annotation.ElementType.*;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.ReportAsSingleViolation;
import javax.validation.constraints.Pattern;

@Retention(RetentionPolicy.RUNTIME)
@Target({FIELD, METHOD})
@Pattern(regexp = "\\d{2,3}-\\d{3,4}-\\d{4}")
@Constraint(validatedBy = {})
@ReportAsSingleViolation
public @interface TelNumberCheck {
	String message() default "{kr.or.ddit.validate.rule.TelNumberCheck.message}"; // {프로퍼티 파일 안의 키값}

	Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}