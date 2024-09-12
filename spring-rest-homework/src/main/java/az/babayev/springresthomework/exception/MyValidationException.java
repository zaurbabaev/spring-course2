package az.babayev.springresthomework.exception;

import lombok.Getter;
import org.springframework.validation.BindingResult;

@Getter
public class MyValidationException extends RuntimeException {

    private final BindingResult bindingResult;

    public MyValidationException(BindingResult bindingResult) {
        this.bindingResult = bindingResult;
    }
}
