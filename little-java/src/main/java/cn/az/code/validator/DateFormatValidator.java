package cn.az.code.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.format.DateTimeFormatter;

/**
 * @author az
 * @since 2021-04-19 22:43
 */
public class DateFormatValidator implements ConstraintValidator<DateFormatValidation, String> {

    private DateTimeFormatter dateTimeFormatter;

    @Override
    public void initialize(DateFormatValidation constraintAnnotation) {
        dateTimeFormatter = DateTimeFormatter.ofPattern(constraintAnnotation.format());
        System.out.println("initialize called");
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        try {
            this.dateTimeFormatter.parse(s);
        } catch (Exception e) {
            return false;
        } finally {
            this.dateTimeFormatter = null;
        }
        return true;
    }
}
