package az.babayev.springrest.controller;

import az.babayev.springrest.dto.ProductDTO;
import az.babayev.springrest.dto.StudentDTO;
import az.babayev.springrest.entity.ProductEntity;
import az.babayev.springrest.service.ProductService;
import az.babayev.springrest.service.StudentService;
import az.babayev.springrest.util.DynamicFiltering;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class DynamicFilteringController {


    private final DynamicFiltering dynamicFiltering;
    private final StudentService studentService;
    private final ProductService productService;


    @GetMapping("/filter-students")
    @ResponseStatus(HttpStatus.OK)
    public MappingJacksonValue getStudentsFilterData(
            @RequestParam String... fields) {
        List<StudentDTO> studentDTOList = studentService.getAllStudentsAndSearch("");
        return dynamicFiltering.filterThis(studentDTOList, "studentFilter", fields);
    }


    @GetMapping("/filter-products")
    @ResponseStatus(HttpStatus.OK)
    public MappingJacksonValue getProductsFilterData(
            @RequestParam String... fields) {
        List<ProductDTO> productDTOList = productService.findAll("");
        return dynamicFiltering.filterThis(productDTOList, "productFilter", fields);
    }



}
