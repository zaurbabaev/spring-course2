package az.babayev.springrest.service;

import az.babayev.springrest.dto.StudentDTO;
import az.babayev.springrest.entity.StudentEntity;
import az.babayev.springrest.exceptions.StudentNotFoundException;
import az.babayev.springrest.exceptions.StudentOperationNotSupportedException;
import az.babayev.springrest.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository repository;
    private final ModelMapper mapper;

    public List<StudentDTO> getAllStudentsAndSearch(String search) {

        List<StudentEntity> studentEntityList = repository.findAllAndSearch(search);

        return studentEntityList.stream().map(entity ->
                        mapper.map(entity, StudentDTO.class))
                .toList();
    }

    public Long add(StudentDTO request) {
        StudentEntity studentEntity = mapper.map(request, StudentEntity.class);
        return repository.save(studentEntity).getId();
    }

    public void update(StudentDTO student) {
        if (student.getId() == null || student.getId() == 0) {
            throw new StudentOperationNotSupportedException("Id should not be null");
        }
        StudentEntity existingStudent = findById(student.getId());
        StudentEntity studentEntity = mapper.map(student, StudentEntity.class);
        repository.save(studentEntity);
    }

    public void deleteById(Long id) {
        findById(id);
        repository.deleteById(id);
    }

    public StudentEntity findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException("Student with id : " + id + " not found"));
    }


    public List<StudentDTO> findAllByLanguageId(Long languageId) {

        List<StudentEntity> allByLanguageId = repository.findAllByLanguageId(languageId);

        return allByLanguageId.stream()
                .map(studentEntity -> mapper
                        .map(studentEntity, StudentDTO.class))
                .toList();
    }


    public List<StudentDTO> findAllByPagination(Integer begin, Integer length) {

        List<StudentEntity> allPagination = repository.findAllPagination(begin, length);
        return allPagination.stream()
                .map(studentEntity -> mapper
                        .map(studentEntity, StudentDTO.class))
                .toList();
    }
}
