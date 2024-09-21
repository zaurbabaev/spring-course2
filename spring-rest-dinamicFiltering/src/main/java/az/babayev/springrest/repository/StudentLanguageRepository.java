package az.babayev.springrest.repository;

import az.babayev.springrest.entity.StudentLanguageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentLanguageRepository extends JpaRepository<StudentLanguageEntity, Long> {

}
