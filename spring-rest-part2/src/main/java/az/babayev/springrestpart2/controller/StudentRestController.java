package az.babayev.springrestpart2.controller;

import az.babayev.springrestpart2.entity.StudentEntity;
import az.babayev.springrestpart2.exceptions.MyValidationException;
import az.babayev.springrestpart2.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/students")
@CrossOrigin(origins = "*")
public class StudentRestController {

    private final StudentService service;

    @GetMapping
    public List<StudentEntity> findAll(
            @RequestParam(name = "search", required = false, defaultValue = "")
            String search) {
        return service.filter(search);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Long add(@Valid
                    @RequestBody StudentEntity request,
                    BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new MyValidationException(bindingResult);
        }
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
//        request.setCreator(username);
        request.setId(null); // bunu yazmaqla biz post metodu vasitəsilə updatenin qarşısını alırıq
        return service.add(request);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public void update(@Valid
                       @RequestBody StudentEntity request,
                       BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new MyValidationException(bindingResult);
        }
        service.update(request);
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteById(@PathVariable Long id) {
        service.deleteById(id);
    }





}
