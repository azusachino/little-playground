package cn.az.code.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

/**
 * @author az
 * @since 08/31/20
 */
public class PhoneValidator implements ConstraintValidator<Phone, String> {

    public static final Pattern PHONE = Pattern.compile("158\\d{8}");

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return PHONE.matcher(value).matches();
    }
}
