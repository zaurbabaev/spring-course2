package az.babayev.service;

import az.babayev.model.Employee;
import az.babayev.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeRepository repository;


    public EmployeeService(EmployeeRepository repository) {
        this.repository = repository;
    }

    public List<Employee> filter(String search) {
        return repository.findAll(search);
    }

    public void add(Employee employee) {
        repository.add(employee);
    }
}
