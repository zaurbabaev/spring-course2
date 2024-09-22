package az.babayev.springrest.controller;

import az.babayev.springrest.dto.StudentWithNotesDTO;
import az.babayev.springrest.entity.StudentWithNotesEntity;
import az.babayev.springrest.service.StudentWithNotesService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("students/notes")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class StudentWithNotesRestController {


    private final StudentWithNotesService service;


    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Find all students with notes", description = "Method: findAll")
    private List<StudentWithNotesDTO> findAll(){
        return service.findAll();

    }
}
