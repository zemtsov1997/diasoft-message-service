package ru.diasoft.service.api.annotation;

import ru.diasoft.service.api.utils.TimeUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;

public class ValidatorActualDateBirth implements ConstraintValidator<ActualDateBirth, LocalDate> {

    public static final Integer MIN_AGE = 14;

    @Override
    public void initialize(ActualDateBirth annotation) { }

    @Override
    public boolean isValid(LocalDate date, ConstraintValidatorContext ctx) {
        if (date == null) {
            ctx.disableDefaultConstraintViolation();
            ctx.buildConstraintViolationWithTemplate("error.user.date.birthday").addConstraintViolation();
            return false;
        } else return TimeUtils.calAge(date, null) >= MIN_AGE;
    }
}
