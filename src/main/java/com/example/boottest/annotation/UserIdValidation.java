package com.example.boottest.annotation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UserIdConstraintValidator.class)
@Target(value = {ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER})
public @interface UserIdValidation {
    String message();
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
