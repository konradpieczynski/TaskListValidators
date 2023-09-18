package com.kodilla.tasklist;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class RangeValidator implements ConstraintValidator<Range, Integer> {
    private int annotationValueMin;
    private int annotationValueMax;

    public void initialize(Range constraintAnnotation) {
        annotationValueMin = constraintAnnotation.min();
        annotationValueMax = constraintAnnotation.max();
    }

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        if (annotationValueMax <= value)
            return annotationValueMin >= value;
        return false;
    }
}

