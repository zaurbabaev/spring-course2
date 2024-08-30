package az.babayev.service;

import az.babayev.model.Computer;
import az.babayev.repository.ComputerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComputerService {

    private final ComputerRepository repository;

    @Autowired
    public ComputerService(ComputerRepository repository) {
        this.repository = repository;
    }

    public List<Computer> filter(String search) {
        return repository.findAll(search);
    }

    public void add(Computer computer) {
        repository.add(computer);
    }

    public void deleteById(Integer id) {
        repository.deleteById(id);
    }

    public Computer findById(Integer id) {
        Computer computerId = repository.findById(id);
        if (computerId == null) {
            throw new RuntimeException("Computer tapılmadı id=" + id);
        }
        return computerId;
    }

    public void update(Computer computer) {
        repository.update(computer);
    }
}
