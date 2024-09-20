package az.babayev.springrest.repository;

import az.babayev.springrest.entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StudentRepository extends JpaRepository<StudentEntity, Long> {

    @Query(value = "SELECT * FROM students WHERE name LIKE %?1% OR surname LIKE %?1%", nativeQuery = true)
    List<StudentEntity> findAllAndSearch(String search);
}
