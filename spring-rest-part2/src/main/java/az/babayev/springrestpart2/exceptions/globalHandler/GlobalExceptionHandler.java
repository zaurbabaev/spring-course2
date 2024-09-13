package az.babayev.springrestpart2.exceptions.globalHandler;

import az.babayev.springrestpart2.exceptions.StudentValidationException;
import az.babayev.springrestpart2.exceptions.StudentNotFoundException;
import az.babayev.springrestpart2.exceptions.StudentOperationNotSupportedException;
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
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public List<String> handleStudentValidationException(StudentValidationException myException) {
        BindingResult bindingResult = myException.getBindingResult();
        List<String> errors = new ArrayList<>();
        bindingResult.getFieldErrors().forEach(error ->
                errors.add(error.getField() + " ==> " + error.getDefaultMessage())
        );
        return errors;
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleStudentNotFoundException(StudentNotFoundException notFoundException) {
        return notFoundException.getMessage();
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleNotSupportedException(StudentOperationNotSupportedException exception) {
        return exception.getMessage();
    }


}
