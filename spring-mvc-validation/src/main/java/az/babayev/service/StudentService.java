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
        List<Student> studentList = repository.findAll(search);
        int counter = 0;
        for (Student student : studentList) {
            student.setCounter(++counter);
        }
        return studentList;
    }

    public void add(Student student) {
        repository.add(student);
    }

    public void deleteById(Integer id) {
        repository.deleteById(id);
    }

    public Student findById(Integer id) {
        Student studentId = repository.findById(id);
        if (studentId == null) {
            throw new RuntimeException("Tələbə tapılmadı id=" + id);
        }
        return studentId;
    }

    public void update(Student student) {
        repository.update(student);
    }
}
