package az.babayev.springrestpart2.service;

import az.babayev.springrestpart2.entity.StudentEntity;
import az.babayev.springrestpart2.exceptions.StudentNotFoundException;
import az.babayev.springrestpart2.exceptions.StudentOperationNotSupportedException;
import az.babayev.springrestpart2.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository repository;

    public List<StudentEntity> filter(String search) {

        return repository.findAllAndSearch(search);
    }

    public Long add(StudentEntity request) {
        return repository.save(request).getId();
    }

    public void update(StudentEntity student) {
        if (student.getId() == null || student.getId() == 0) {
            throw new StudentOperationNotSupportedException("Id should not be null");
        }
        StudentEntity existingStudent = findById(student.getId());
        repository.save(student);
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
