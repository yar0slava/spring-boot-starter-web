package kma.topic3.webstarter.database;

import kma.topic3.webstarter.model.entities.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<BookEntity, String> {

    List<BookEntity> findByIsbnContainsAndAndNameContains(String isbn, String name);
}
