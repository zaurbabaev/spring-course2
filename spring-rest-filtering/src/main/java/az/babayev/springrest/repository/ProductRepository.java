package az.babayev.springrest.repository;

import az.babayev.springrest.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {


    @Query(value = "SELECT * FROM products WHERE name LIKE %?1%", nativeQuery = true)
    List<ProductEntity> findAllAndSearchByName(String search);
}
