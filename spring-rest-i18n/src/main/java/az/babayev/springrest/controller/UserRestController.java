package az.babayev.springrest.controller;


import az.babayev.springrest.entity.UserEntity;
import az.babayev.springrest.exceptions.MyValidationException;
import az.babayev.springrest.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
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
    @Operation(summary = "Create librarian", description = "createLibrarian")
    public void createLibrarian(@Valid
                                @RequestBody UserEntity request,
                                BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new MyValidationException(bindingResult);
        }


        service.createLibrarian(request);
    }

    @GetMapping("/login")
    @Operation(summary = "Login", description = "login")
    public void login() {

    }

}
