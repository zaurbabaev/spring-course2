package az.babayev.springrest.repository;

import az.babayev.springrest.entity.LanguageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LanguageRepository extends JpaRepository<LanguageEntity, Long> {

    @Query(value = "SELECT * FROM languages WHERE language LIKE %?1%", nativeQuery = true)
    List<LanguageEntity> findAllAndSearch(String search);


    @Query(value = "SELECT * FROM languages WHERE language=?1", nativeQuery = true)
    Optional<LanguageEntity> findByLanguage(String language);
}
