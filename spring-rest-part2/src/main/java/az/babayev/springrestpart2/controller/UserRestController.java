package az.babayev.springrestpart2.controller;


import az.babayev.springrestpart2.entity.UserEntity;
import az.babayev.springrestpart2.exceptions.MyValidationException;
import az.babayev.springrestpart2.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
@CrossOrigin(origins = "*")
public class UserRestController {

    private final UserService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createLibrarian(@Valid
                                @RequestBody UserEntity request,
                                BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new MyValidationException(bindingResult);
        }

        service.createLibrarian(request);
    }
}
