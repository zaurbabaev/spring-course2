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

    public List<StudentEntity> filter(String search) {

        return repository.findAllAndSearch(search);
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


}
