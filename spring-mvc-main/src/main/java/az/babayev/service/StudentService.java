package az.babayev.service;

import az.babayev.model.Student;
import az.babayev.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepository repository;

    public List<Student> filter(String s) {
        return repository.findAll(s);
    }

    public void add(Student student) {
        repository.add(student);
    }
}
