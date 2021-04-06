package kma.topic3.webstarter.controller;

import kma.topic3.webstarter.database.UserRepository;
import kma.topic3.webstarter.model.entities.BookEntity;
import kma.topic3.webstarter.database.BookRepository;
import kma.topic3.webstarter.model.entities.UserEntity;
import kma.topic3.webstarter.model.security.AuthenticatedUser;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BooksController {

    private final BookRepository bookRepository;
    private final UserRepository userRepository;

    @PreAuthorize("isFullyAuthenticated()")
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseEntity<List<BookEntity>> saveBook(
            @RequestBody final BookEntity book
    ) {

        bookRepository.save(book);
        System.out.println("Saved book: " + book);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(bookRepository.findAll());
    }

    @PreAuthorize("isFullyAuthenticated()")
    @RequestMapping(value = "/find", method = RequestMethod.POST)
    public ResponseEntity<List<BookEntity>> findBooks(
            @RequestBody final BookEntity book
    ) {

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(bookRepository.findByIsbnContainsAndAndNameContains(book.getIsbn(),book.getName()));
    }

    @PreAuthorize("isFullyAuthenticated()")
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ResponseEntity<List<BookEntity>> getAll() {

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(bookRepository.findAll());
    }

    @PreAuthorize("isFullyAuthenticated()")
    @RequestMapping(value = "/favorite", method = RequestMethod.GET)
    public ResponseEntity<List<BookEntity>> getFavorite() {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        final AuthenticatedUser authenticatedUser = (AuthenticatedUser) auth.getPrincipal();

        UserEntity userEntity = userRepository.findByLogin(authenticatedUser.getUsername()).get();
        for (BookEntity b :userEntity.getBooks()) {
            System.out.println(b);
        }

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userEntity.getBooks());
    }

    @PreAuthorize("isFullyAuthenticated()")
    @RequestMapping(value = "/addtofavorite", method = RequestMethod.POST)
    public void addToFavorite(@RequestBody final BookEntity book) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        final AuthenticatedUser authenticatedUser = (AuthenticatedUser) auth.getPrincipal();

        BookEntity bookEntity = bookRepository.findById(book.getIsbn()).get();

        UserEntity userEntity = userRepository.findByLogin(authenticatedUser.getUsername()).get();
        userEntity.getBooks().add(bookEntity);

        userRepository.save(userEntity);
    }

    @PreAuthorize("isFullyAuthenticated()")
    @RequestMapping(value = "/deletefromfavorite", method = RequestMethod.POST)
    public void deleteFromFavorite(@RequestBody final BookEntity book) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        final AuthenticatedUser authenticatedUser = (AuthenticatedUser) auth.getPrincipal();

        BookEntity bookEntity = bookRepository.findById(book.getIsbn()).get();

        UserEntity userEntity = userRepository.findByLogin(authenticatedUser.getUsername()).get();
        userEntity.getBooks().remove(bookEntity);

        userRepository.save(userEntity);
    }
}
