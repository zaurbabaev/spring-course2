package az.babayev.springrestpart2.repository;

import az.babayev.springrestpart2.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<BookEntity, Long> {
}
