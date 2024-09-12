package az.babayev.service;

import az.babayev.entity.Student;
import az.babayev.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository repository;


    public List<Student> filter(String search) {
        List<Student> studentList = repository.findAllAndSearch(search);
        AtomicInteger counter = new AtomicInteger();
        studentList.forEach(student -> student.setCounter(counter.incrementAndGet()));
        return studentList;
    }

    public Integer add(Student student) {
        return repository.save(student).getId();
    }

}
