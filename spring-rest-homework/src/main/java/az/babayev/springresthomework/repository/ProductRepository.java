package az.babayev.springresthomework.repository;

import az.babayev.springresthomework.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query(value = "SELECT * FROM products WHERE name LIKE %:search%", nativeQuery = true)
    List<Product> searchAllByName(String search);
}
