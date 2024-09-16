package az.babayev.springrestpart2.repository;

import az.babayev.springrestpart2.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, String> {


}
