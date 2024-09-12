package az.babayev.controller;

import az.babayev.entity.Student;
import az.babayev.exceptions.OurValidationException;
import az.babayev.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudentRestController {

    private final StudentService service;

    @GetMapping
    public List<Student> findAll(
            @RequestParam(name = "search", required = false, defaultValue = "")
            String search) {
        return service.filter(search);
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Integer add(@Valid
                       @RequestBody Student request,
                       BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new OurValidationException(bindingResult);
        }
        request.setId(null);
        return service.add(request);
    }

}
