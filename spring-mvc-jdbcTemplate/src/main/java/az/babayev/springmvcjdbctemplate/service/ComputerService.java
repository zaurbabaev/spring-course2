package az.babayev.springmvcjdbctemplate.service;

import az.babayev.springmvcjdbctemplate.exception.ComputerNotFoundException;
import az.babayev.springmvcjdbctemplate.model.Computer;
import az.babayev.springmvcjdbctemplate.repository.ComputerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComputerService {

    private final ComputerRepository repository;

    public ComputerService(ComputerRepository repository) {
        this.repository = repository;
    }

    public List<Computer> findAll(String search) {
        return repository.findAll(search);
    }

    public void add(Computer computer) {
        repository.add(computer);
    }

    public Computer findById(Integer id) {
        Computer computer = repository.findById(id);
        if (computer == null) {
            throw new ComputerNotFoundException("Computer with id=" + id + " not found");
        }
        return computer;
    }

    public void update(Computer computer) {
        repository.update(computer);
    }

    public void delete(Integer id) {
        repository.delete(id);
    }
}
