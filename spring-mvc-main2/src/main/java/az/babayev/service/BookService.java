package az.babayev.service;

import az.babayev.model.Book;
import az.babayev.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private final BookRepository repository;

    @Autowired
    public BookService(BookRepository repository) {
        this.repository = repository;
    }

    public List<Book> filter(String search) {
        return repository.findAll(search);
    }

    public void add(Book book) {
        repository.add(book);
    }
}
