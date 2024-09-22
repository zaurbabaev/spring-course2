package az.babayev.springrest.repository;


import az.babayev.springrest.entity.StudentWithNotesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentWithNotesRepository extends JpaRepository<StudentWithNotesEntity,Long> {

}
