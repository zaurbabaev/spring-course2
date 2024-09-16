package az.babayev.springrestpart2.controller;

import az.babayev.springrestpart2.entity.ProductEntity;
import az.babayev.springrestpart2.exceptions.MyValidationException;
import az.babayev.springrestpart2.service.ProductService;
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
    public List<ProductEntity> findAll(@RequestParam(name = "search", required = false, defaultValue = "")
                                       String search) {
        return service.findAll(search);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Long add(@Valid
                    @RequestBody
                    ProductEntity request,
                    BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            throw new MyValidationException(bindingResult);
        }
        return service.add(request);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public void update(@Valid
                       @RequestBody
                       ProductEntity request,
                       BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new MyValidationException(bindingResult);
        }
        service.update(request);
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteById(@PathVariable Long id){
        service.deleteById(id);
    }
}
