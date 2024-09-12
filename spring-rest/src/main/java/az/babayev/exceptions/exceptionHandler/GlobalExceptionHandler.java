package az.babayev.exceptions.exceptionHandler;

import az.babayev.exceptions.OurValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public List<String> handleValidationException(OurValidationException exception) {
        BindingResult bindingResult = exception.getBindingResult();
        List<String> errors = new ArrayList<>();
        bindingResult.getFieldErrors().forEach(error ->
                errors.add(error.getField() + " -> " + error.getDefaultMessage())
        );

        return errors;
    }
}
