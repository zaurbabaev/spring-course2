package az.babayev.springrestpart2.repository;

import az.babayev.springrestpart2.entity.AuthorityEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepository extends JpaRepository<AuthorityEntity, Long> {
}
