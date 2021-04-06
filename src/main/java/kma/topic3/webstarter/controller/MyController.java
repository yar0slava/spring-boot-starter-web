package kma.topic3.webstarter.controller;

import kma.topic3.webstarter.model.entities.BookEntity;
import kma.topic3.webstarter.database.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
public class MyController {

    private final BookRepository bookRepository;

    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/signup")
    public String signUp() {
        return "signup";
    }

    @GetMapping("/login")
    public String logIn() {
        return "login";
    }

    @PreAuthorize("isFullyAuthenticated()")
    @GetMapping("/book/{isbn}")
    public String getBookByIsbn(@PathVariable String isbn, @ModelAttribute BookEntity book, Model model) {

        model.addAttribute("book", bookRepository.findById(isbn));
        return "book";
    }
}
