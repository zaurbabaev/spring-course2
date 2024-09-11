package az.babayev.repository;

import az.babayev.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

    @Query(value = "SELECT * FROM " +
            "students " +
            "WHERE name LIKE %:search% OR surname LIKE %:search%",
            nativeQuery = true)
    List<Student> findAllAndSearch(@Param("search") String search);


}
