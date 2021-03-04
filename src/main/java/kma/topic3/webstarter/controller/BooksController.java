package kma.topic3.webstarter.controller;

import kma.topic3.webstarter.model.Book;
import kma.topic3.webstarter.service.BooksService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BooksController {

    private final BooksService booksService;

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseEntity<List<Book>> saveBook(
            @RequestBody final Book book
    ) {
        booksService.add(book);
        System.out.println("Saved book: " + book);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(booksService.getAll());
    }

    @RequestMapping(value = "/find", method = RequestMethod.POST)
    public ResponseEntity<List<Book>> findBooks(
            @RequestBody final Book book
    ) {

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(booksService.findByNameIsbn(book.getName(),book.getIsbn()));
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ResponseEntity<List<Book>> getAll() {

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(booksService.getAll());
    }
}
