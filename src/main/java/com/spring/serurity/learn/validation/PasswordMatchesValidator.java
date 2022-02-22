package com.spring.serurity.learn.validation;

import com.spring.serurity.learn.domain.dto.UserDto;
import com.spring.serurity.learn.validation.annotation.PasswordMatches;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author lihu
 */
public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, UserDto> {

    @Override
    public void initialize(final PasswordMatches constraintAnnotation) { }

    @Override
    public boolean isValid(final UserDto obj, final ConstraintValidatorContext context) {

        return ((UserDto) obj).getPassword().equals(((UserDto) obj).getMatchingPassword());
    }
}