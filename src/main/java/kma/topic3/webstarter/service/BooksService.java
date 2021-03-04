package kma.topic3.webstarter.service;

import kma.topic3.webstarter.model.Book;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BooksService {

    List<Book> books = new ArrayList<>();

    public void add(Book book) {
        books.add(book);
    }

    public List<Book> getAll() {
        return books;
    }

    public List<Book> findByNameIsbn(String name, String isbn) {
        return books.stream()
                .filter(b -> (b.getName().contains(name)) || (b.getIsbn().contains(isbn)))
                .collect(Collectors.toList());
    }
}
