package az.babayev.springrest.controller;

import az.babayev.springrest.dto.ProductDTO;
import az.babayev.springrest.entity.ProductEntity;
import az.babayev.springrest.exceptions.MyValidationException;
import az.babayev.springrest.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/products")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class ProductRestController {

    private final ProductService service;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Find all products", description = "Method: findAll")

    public List<ProductDTO> findAll(@RequestParam(name = "search", required = false, defaultValue = "")
                                       String search) {
        return service.findAll(search);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Add product", description = "Method: add")
    public Long add(@Valid
                    @RequestBody
                    ProductDTO request,
                    BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new MyValidationException(bindingResult);
        }
        return service.add(request);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Update product", description = "Method: update")
    public void update(@Valid
                       @RequestBody
                       ProductDTO request,
                       BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new MyValidationException(bindingResult);
        }
        service.update(request);
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Delete product by id", description = "Method: deleteById")
    public void deleteById(@PathVariable Long id) {
        service.deleteById(id);
    }
}
