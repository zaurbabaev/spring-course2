package az.babayev.springmvcjdbctemplate.service;

import az.babayev.springmvcjdbctemplate.exception.StudentNotFoundException;
import az.babayev.springmvcjdbctemplate.model.Student;
import az.babayev.springmvcjdbctemplate.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private final StudentRepository repository;

    public StudentService(StudentRepository repository) {
        this.repository = repository;
    }

    public List<Student> findAll(String search) {
        return repository.findAll(search);
    }

    public void add(Student student) {
        repository.add(student);
    }

    public Student findById(Integer id) {
        Student student = repository.findById(id);
        if (student == null) {
            throw new StudentNotFoundException("Student by id=" + id + " not found");
        }
        return student;
    }

    public void update(Student student) {
        repository.update(student);
    }

    public void delete(Integer id) {
        repository.delete(id);
    }

}
