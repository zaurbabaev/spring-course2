package az.babayev.springrestpart2.repository;

import az.babayev.springrestpart2.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {


    @Query(value = "SELECT * FROM products WHERE name LIKE %?1%", nativeQuery = true)
    List<ProductEntity> findAllAndSearchByName(String search);
}
