package az.babayev.exceptions;

import lombok.Getter;
import org.springframework.validation.BindingResult;

@Getter
public class OurValidationException extends RuntimeException {

    private final BindingResult bindingResult;

    public OurValidationException(BindingResult bindingResult) {
        this.bindingResult = bindingResult;
    }

}
