package az.babayev.springrest.exceptions;

import lombok.Getter;
import org.springframework.validation.BindingResult;

@Getter
public class MyValidationException extends RuntimeException {

    private BindingResult bindingResult;

    public MyValidationException(BindingResult bindingResult) {
        this.bindingResult = bindingResult;
    }
}
