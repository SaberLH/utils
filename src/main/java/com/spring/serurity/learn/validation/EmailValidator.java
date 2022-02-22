package com.spring.serurity.learn.validation;

import com.spring.serurity.learn.validation.annotation.ValidEmail;
import lombok.val;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

/**
 * @author lihu
 */
public class EmailValidator implements ConstraintValidator<ValidEmail, String> {

    private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    @Override
    public void initialize(final ValidEmail constraintAnnotation) {}

    @Override
    public boolean isValid(final String str, final ConstraintValidatorContext context) {
        return (validateEmail(str));
    }

    private boolean validateEmail(final String email) {
        val pattern = Pattern.compile(EMAIL_PATTERN);
        val matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
