package az.babayev.springresthomework.service;

import az.babayev.springresthomework.entity.Product;
import az.babayev.springresthomework.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository repository;

    public List<Product> findAll(String search) {
        return repository.searchAllByName(search);
    }

    public Long add(Product product) {
        return repository.save(product).getId();

    }
}
