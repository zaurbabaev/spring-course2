package az.babayev.springrest.service;

import az.babayev.springrest.entity.LanguageEntity;
import az.babayev.springrest.entity.StudentEntity;
import az.babayev.springrest.entity.StudentLanguageEntity;
import az.babayev.springrest.repository.StudentLanguageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentLanguageService {

    private final StudentLanguageRepository studentLanguageRepository;
    private final StudentLanguageRepository repository;
    private final StudentService studentService;
    private final LanguageService languageService;


    public void saveAll(List<StudentLanguageEntity> entities) {
        for (StudentLanguageEntity entity : entities) {
            StudentEntity byId = studentService.findById(entity.getStudentId());
            LanguageEntity languageById = languageService.findById(entity.getLanguageId());
        }
        studentLanguageRepository.saveAll(entities);
    }


}
