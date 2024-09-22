package az.babayev.springrest.controller;

import az.babayev.springrest.dto.StudentDTO;
import az.babayev.springrest.entity.StudentLanguageEntity;
import az.babayev.springrest.exceptions.MyValidationException;
import az.babayev.springrest.request.AddLanguageStudentModel;
import az.babayev.springrest.service.StudentLanguageService;
import az.babayev.springrest.service.StudentService;
import az.babayev.springrest.util.DynamicFiltering;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/students")
@CrossOrigin(origins = "*")
public class StudentRestController {

    private final StudentService service;
    private final StudentLanguageService studentLanguageService;
    private final ModelMapper mapper;
    private DynamicFiltering dynamicFiltering;

    @GetMapping
    @Operation(summary = "Get all students", description = "Method: findAll")
    public List<StudentDTO> findAll(@RequestParam(name = "search", required = false, defaultValue = "") String search) {
        return service.getAllStudentsAndSearch(search);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Save student", description = "Method: add")
    public Long add(@Valid @RequestBody StudentDTO request, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new MyValidationException(bindingResult);
        }
        request.setId(null); // bunu yazmaqla biz post metodu vasitəsilə updatenin qarşısını alırıq
        return service.add(request);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Update student", description = "Method: update")
    public void update(@Valid @RequestBody StudentDTO request, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new MyValidationException(bindingResult);
        }
        service.update(request);
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Delete student", description = "Method: deleteById")
    public void deleteById(@PathVariable Long id) {
        service.deleteById(id);
    }


    @PutMapping("/add-languages-to-student")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Add language to student", description = "Method: addLanguageToStudent")
    public void addLanguagesToStudent(@RequestBody AddLanguageStudentModel request) {
        List<StudentLanguageEntity> entities = new ArrayList<>();

        for (Long languageId : request.getLanguageIds()) {
            StudentLanguageEntity studentLanguageEntity = StudentLanguageEntity.builder().studentId(request.getStudentId()).languageId(languageId).build();
            entities.add(studentLanguageEntity);
        }

        studentLanguageService.saveAll(entities);
    }

    @GetMapping("/find-by-language-id/{languageId}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Find student by language id", description = "Method: findByLanguageId")
    public List<StudentDTO> findByLanguageId(@PathVariable Long languageId) {
        return service.findAllByLanguageId(languageId);
    }

    @GetMapping("/find-partial/{begin}/{length}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Pagination", description = "Method: findAllByPagination")
    public List<StudentDTO> findAllByPagination(@PathVariable Integer begin, @PathVariable Integer length) {
        return service.findAllByPagination(begin, length);
    }


}
