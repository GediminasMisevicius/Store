package lt.bit.validation;

import java.math.BigDecimal;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ProductsSumPositiveValidator implements ConstraintValidator<NotNegative, BigDecimal> {

	@Override
	public boolean isValid(BigDecimal value, ConstraintValidatorContext context) {
		if (value != null) {
			return value.doubleValue() >= 0;
		}
		return false;
//		return value != null ? value.doubleValue() >= 0 : false;
//		return (value != null && value.doubleValue() >= 0) ? true : false;
	}

}
