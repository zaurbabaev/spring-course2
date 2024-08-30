package az.babayev.service;

import az.babayev.model.Student;
import az.babayev.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private final StudentRepository repository;

    @Autowired
    public StudentService(StudentRepository repository) {
        this.repository = repository;
    }

    public List<Student> filter(String search) {
        return repository.findAll(search);
    }

    public void add(Student student) {
        repository.add(student);
    }
}
