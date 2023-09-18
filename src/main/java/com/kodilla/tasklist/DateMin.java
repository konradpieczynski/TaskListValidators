package com.kodilla.tasklist;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;

@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = DateMinValidator.class)
public @interface DateMin {
    String message() default "Earlier date then expected";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    String value() default "2020-01-01";

}