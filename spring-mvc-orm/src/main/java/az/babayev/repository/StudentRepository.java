package az.babayev.repository;

import az.babayev.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

    @Query(value = "SELECT * FROM " +
            "students " +
            "WHERE name LIKE %?1% OR surname LIKE %?1%",
            nativeQuery = true)
    List<Student> findAllAndSearch(String search);


    List<Student> findAllByName(String name);

    Optional<Student> findByName(String name);

}
