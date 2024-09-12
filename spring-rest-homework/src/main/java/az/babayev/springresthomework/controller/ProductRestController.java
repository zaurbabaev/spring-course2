package az.babayev.springresthomework.controller;

import az.babayev.springresthomework.entity.Product;
import az.babayev.springresthomework.exception.MyValidationException;
import az.babayev.springresthomework.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductRestController {

    private final ProductService service;

    @GetMapping
    public List<Product> findAll(@RequestParam(name = "search", required = false, defaultValue = "")
                                 String search) {
        return service.findAll(search);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Long add(@Valid
                    @RequestBody
                    Product request,
                    BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new MyValidationException(bindingResult);
        }
        request.setId(null);
        return service.add(request);
    }
}
