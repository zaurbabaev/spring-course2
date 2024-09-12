package az.babayev.exceptions.handler;

import az.babayev.exceptions.StudentNotFoundException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public String handleAccessDeniedException(AccessDeniedException exc, Model model) {
        model.addAttribute("message", exc.getMessage());
        return "my-error";
    }

    @ExceptionHandler
    public String handleStudentNotFoundException(StudentNotFoundException ex, Model model) {
        model.addAttribute("message", ex.getMessage());
        return "my-error";
    }
}
