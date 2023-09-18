package com.kodilla.tasklist;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;


import java.lang.annotation.*;


@Target({ElementType.PARAMETER, ElementType.FIELD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = RangeValidator.class)
public @interface Range {
	String message() default "Priority can be between 1 and 5";

	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };

	int min() default 1;
	int max() default 5;
}