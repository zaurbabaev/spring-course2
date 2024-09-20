package az.babayev.springrest.controller;

import az.babayev.springrest.dto.StudentDTO;
import az.babayev.springrest.entity.StudentEntity;
import az.babayev.springrest.entity.StudentLanguageEntity;
import az.babayev.springrest.exceptions.MyValidationException;
import az.babayev.springrest.request.AddLanguageStudentModel;
import az.babayev.springrest.service.StudentLanguageService;
import az.babayev.springrest.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
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

    @GetMapping
    public List<StudentDTO> findAll(
            @RequestParam(name = "search", required = false, defaultValue = "")
            String search) {
        List<StudentEntity> studentEntityList = service.filter(search);
        return studentEntityList.stream()
                .map(student ->
                        mapper.map(student, StudentDTO.class))
                .toList();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Long add(@Valid
                    @RequestBody StudentDTO request,
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
                       @RequestBody StudentDTO request,
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


    @PutMapping("/add-languages-to-student")
    @ResponseStatus(HttpStatus.OK)
    public void addLanguagesToStudent(@RequestBody
                                      AddLanguageStudentModel request) {
        List<StudentLanguageEntity> entities = new ArrayList<>();

        for (Long languageId : request.getLanguageIds()) {
            StudentLanguageEntity studentLanguageEntity = StudentLanguageEntity.builder()
                    .studentId(request.getStudentId())
                    .languageId(languageId)
                    .build();
            entities.add(studentLanguageEntity);
        }

        studentLanguageService.saveAll(entities);
    }


}
