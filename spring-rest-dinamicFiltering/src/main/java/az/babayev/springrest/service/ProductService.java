package az.babayev.springrest.service;

import az.babayev.springrest.dto.ProductDTO;
import az.babayev.springrest.entity.ProductEntity;
import az.babayev.springrest.exceptions.ProductNotFoundException;
import az.babayev.springrest.exceptions.ProductOperationNotSupportedException;
import az.babayev.springrest.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository repository;
    private final ModelMapper mapper;

    public List<ProductDTO> findAll(String search) {
        List<ProductEntity> productEntities = repository.findAllAndSearchByName(search);
        return productEntities.stream()
                .map(product -> mapper
                        .map(product, ProductDTO.class))
                .toList();

    }

    public Long add(ProductDTO request) {
        ProductEntity productEntity = mapper.map(request, ProductEntity.class);
        return repository.save(productEntity).getId();
    }

    public void update(ProductDTO product) {
        if (product.getId() == null || product.getId() == 0) {
            throw new ProductOperationNotSupportedException("Id should not be null");
        }
        ProductEntity existingProduct = findById(product.getId());
        ProductEntity productEntity = mapper.map(product, ProductEntity.class);
        repository.save(productEntity);
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
