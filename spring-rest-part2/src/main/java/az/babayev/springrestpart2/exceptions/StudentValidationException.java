package az.babayev.springrestpart2.exceptions;

import lombok.Getter;
import org.springframework.validation.BindingResult;

@Getter
public class StudentValidationException extends RuntimeException {

    private BindingResult bindingResult;

    public StudentValidationException(BindingResult bindingResult) {
        this.bindingResult = bindingResult;
    }
}
