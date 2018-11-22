package lt.bit.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy=ProductsSumPositiveValidator.class)
public @interface NotNegative {
	
	String message() default "{validation.constraint.error.NOT_NEGATIVE}";

	   Class<?>[] groups() default {};

	   Class<? extends Payload>[] payload() default {};
}
