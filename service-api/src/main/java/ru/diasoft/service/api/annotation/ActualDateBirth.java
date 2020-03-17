package ru.diasoft.service.api.annotation;

import javax.validation.Constraint;
import java.lang.annotation.*;

@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {ValidatorActualDateBirth.class})
public @interface ActualDateBirth {

    String message() default "error.date.birthday.actual";

    Class[] groups() default {};

    Class[] payload() default {};

}
