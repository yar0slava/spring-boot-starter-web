package kma.topic3.webstarter.service;

import kma.topic3.webstarter.model.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BooksService {

    private final EntityManager entityManager;

    @Transactional
    public Book add(Book book) {
        return entityManager.merge(book);
    }

    public List<Book> getAll() {
        return entityManager.createQuery("SELECT b FROM Book b",Book.class)
                .getResultList();
    }

    public List<Book> findByIsbnNameAuthor(String isbn, String name, String author) {
        return entityManager.createQuery(
                "SELECT b FROM Book b WHERE " +
                        "b.isbn = :isbn OR " +
                        "b.name = :name OR " +
                        "b.author = :author",
                Book.class)
                .setParameter("isbn", isbn)
                .setParameter("name", name)
                .setParameter("author", author)
                .getResultList();
    }

    public Book findByIsbn(String isbn) {
        return entityManager.createQuery(
                "SELECT b FROM Book b WHERE b.isbn = :isbn", Book.class)
                .setParameter("isbn", isbn)
                .getSingleResult();
    }
}
