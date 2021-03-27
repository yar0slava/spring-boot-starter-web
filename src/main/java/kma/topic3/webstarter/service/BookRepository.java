package kma.topic3.webstarter.service;

import kma.topic3.webstarter.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, String> {

    List<Book> findByIsbnContainsAndAndNameContains(String isbn, String name);
}
