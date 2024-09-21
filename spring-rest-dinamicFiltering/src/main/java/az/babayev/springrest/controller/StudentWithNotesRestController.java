package az.babayev.springrest.controller;

import az.babayev.springrest.dto.StudentWithNotesDTO;
import az.babayev.springrest.entity.StudentWithNotesEntity;
import az.babayev.springrest.service.StudentWithNotesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("students/notes")
@RequiredArgsConstructor
public class StudentWithNotesRestController {


    private final StudentWithNotesService service;


    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    private List<StudentWithNotesDTO> findAll(){
        return service.findAll();

    }
}
