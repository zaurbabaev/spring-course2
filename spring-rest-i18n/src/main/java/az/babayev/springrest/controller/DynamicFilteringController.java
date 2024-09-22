package az.babayev.springrest.controller;

import az.babayev.springrest.dto.ProductDTO;
import az.babayev.springrest.dto.ProductDtoDynamic;
import az.babayev.springrest.dto.StudentDTO;
import az.babayev.springrest.dto.StudentDtoDynamic;
import az.babayev.springrest.service.ProductService;
import az.babayev.springrest.service.StudentService;
import az.babayev.springrest.util.DynamicFiltering;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class DynamicFilteringController {


    private final DynamicFiltering dynamicFiltering;
    private final StudentService studentService;
    private final ProductService productService;
    private final ModelMapper mapper;


    @GetMapping("/filter-students")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get student filtered data", description = "Method: getStudentsFilterData")
    public MappingJacksonValue getStudentsFilterData(
            @RequestParam String... fields) {
        List<StudentDTO> studentDTOList = studentService.getAllStudentsAndSearch("");
        List<StudentDtoDynamic> studentDynamicFilteringDTOS = studentDTOList.stream()
                .map(dynamicFiltering ->
                        mapper.map(dynamicFiltering, StudentDtoDynamic.class))
                .toList();
        return dynamicFiltering.filterThis(studentDynamicFilteringDTOS, "studentFilter", fields);
    }


    @GetMapping("/filter-products")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get products filtered data", description = "Method: getProductsFilterData")
    public MappingJacksonValue getProductsFilterData(
            @RequestParam String... fields) {
        List<ProductDTO> productDTOList = productService.findAll("");
        List<ProductDtoDynamic> dynamicFilteringDTOS = productDTOList
                .stream()
                .map(dynamicFiltering -> mapper
                        .map(dynamicFiltering, ProductDtoDynamic.class))
                .toList();
        return dynamicFiltering.filterThis(dynamicFilteringDTOS, "productFilter", fields);
    }


}
