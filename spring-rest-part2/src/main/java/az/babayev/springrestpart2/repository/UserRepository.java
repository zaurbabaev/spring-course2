package az.babayev.springrestpart2.repository;

import az.babayev.springrestpart2.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.validation.constraints.NotEmpty;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, String> {

}
