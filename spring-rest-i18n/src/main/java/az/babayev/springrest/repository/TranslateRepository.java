package az.babayev.springrest.repository;

import az.babayev.springrest.entity.TranslateEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TranslateRepository extends JpaRepository<TranslateEntity, Long> {

    List<TranslateEntity> findAllByLanguage(String language);

}
