package az.babayev.service;

import az.babayev.entity.Student;
import az.babayev.exceptions.StudentNotFoundException;
import az.babayev.repository.AuthorityRepository;
import az.babayev.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class StudentService {

    private final StudentRepository repository;
    private final UserService userService;
    private final AuthorityRepository authorityRepository;

    @Autowired
    public StudentService(StudentRepository repository, UserService userService, AuthorityRepository authorityRepository) {
        this.repository = repository;
        this.userService = userService;
        this.authorityRepository = authorityRepository;
    }

    public List<Student> filter(String search) {
        List<Student> studentList = repository.findAllAndSearch(search);
        AtomicInteger counter = new AtomicInteger();
        studentList.forEach(student -> student.setCounter(counter.incrementAndGet()));
        return studentList;
    }

    public void add(Student student) {
        userService.saveUser(student.getUsername(),
                student.getPassword());
        repository.save(student);
    }

    public void deleteById(Integer id) {
        findById(id);
        repository.deleteById(id);
    }

    public Student findById(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException("Tələbə tapılmadı id=" + id));

    }

}
