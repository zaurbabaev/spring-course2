package az.babayev.springmvcjdbctemplate.annotation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class MyAnnotationValidator implements ConstraintValidator<MyAnnotation, String> {

    private String prefix;

    @Override
    public void initialize(MyAnnotation myAnnotation) {
        this.prefix = myAnnotation.value();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value.startsWith(prefix);
    }
}
