package az.babayev.springrest.service;

import az.babayev.springrest.entity.ProductEntity;
import az.babayev.springrest.exceptions.ProductNotFoundException;
import az.babayev.springrest.exceptions.ProductOperationNotSupportedException;
import az.babayev.springrest.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository repository;

    public List<ProductEntity> findAll(String search) {
        return repository.findAllAndSearchByName(search);
    }

    public Long add(ProductEntity request) {
        return repository.save(request).getId();
    }

    public void update(ProductEntity product) {
        if (product.getId() == null || product.getId() == 0) {
            throw new ProductOperationNotSupportedException("Id should not be null");
        }
        ProductEntity existingProduct = findById(product.getId());

        repository.save(product);

    }

    public ProductEntity findById(Long id) {
        return repository.findById(id).orElseThrow(() ->
                new ProductNotFoundException("Product with id = " + id + " not found"));
    }


    public void deleteById(Long id) {
        findById(id);
        repository.deleteById(id);
    }
}
