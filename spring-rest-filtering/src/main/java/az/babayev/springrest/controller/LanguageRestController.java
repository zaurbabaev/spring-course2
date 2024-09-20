package az.babayev.springrest.controller;

import az.babayev.springrest.entity.LanguageEntity;
import az.babayev.springrest.exceptions.MyValidationException;
import az.babayev.springrest.service.LanguageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/languages")
public class LanguageRestController {

    private final LanguageService service;

    @GetMapping
    public List<LanguageEntity> findAll(@RequestParam(name = "search", defaultValue = "", required = false)
                                        String search) {
        return service.filter(search);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Long add(@Valid
                    @RequestBody LanguageEntity request,
                    BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new MyValidationException(bindingResult);

        }
        request.setId(null);
        return service.add(request);
    }
}
