package az.babayev.my_annotation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class MyAnnotationValidator implements ConstraintValidator<MyAnnotation, String> {

    private String prefix;

    @Override
    public void initialize(MyAnnotation myAnnotation) {
        prefix = myAnnotation.value();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return prefix.startsWith(value);
    }
}
