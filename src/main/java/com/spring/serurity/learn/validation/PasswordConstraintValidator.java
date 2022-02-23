package com.spring.serurity.learn.validation;

import com.spring.serurity.learn.validation.annotation.ValidPassword;
import lombok.RequiredArgsConstructor;
import org.passay.*;
import org.passay.spring.SpringMessageResolver;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;

/**
 * @author lihu
 */
@RequiredArgsConstructor
public class PasswordConstraintValidator implements ConstraintValidator<ValidPassword, String> {

    @Override
    public void initialize(final ValidPassword constraintAnnotation) {}

    @Override
    public boolean isValid(final String password, final ConstraintValidatorContext context) {

        PasswordValidator validator = new PasswordValidator(Arrays.asList(
                // 长度规则：8 - 30 位
                new LengthRule(8, 30),
                // 至少有一个大写字母
                new CharacterRule(EnglishCharacterData.UpperCase, 1),
                // 至少有一个小写字母
                new CharacterRule(EnglishCharacterData.LowerCase, 1),
                // 至少有一个数字
                new CharacterRule(EnglishCharacterData.Digit, 1),
                // 至少有一个特殊字符
                new CharacterRule(EnglishCharacterData.Special, 1),
                // 不允许连续 3 个字母，按字母表顺序
                // alphabetical is of the form 'abcde', numerical is '34567', qwery is 'asdfg'
                // the false parameter indicates that wrapped sequences are allowed; e.g. 'xyzabc'
                new IllegalSequenceRule(EnglishSequenceData.Alphabetical, 5, false),
                // 不允许 3 个连续数字
                new IllegalSequenceRule(EnglishSequenceData.Numerical, 5, false),
                // 不允许 QWERTY 键盘上的三个连续相邻的按键所代表的字符
                new IllegalSequenceRule(EnglishSequenceData.USQwerty, 5, false),
                // 不允许包含空格
                new WhitespaceRule()));
        RuleResult result = validator.validate(new PasswordData(password));
        return result.isValid();
    }
}
