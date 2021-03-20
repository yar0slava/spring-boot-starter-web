package kma.topic3.webstarter.controller;

import kma.topic3.webstarter.model.Book;
import kma.topic3.webstarter.service.BooksService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
public class MyController {

    private final BooksService booksService;

    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/book/{isbn}")
    public String getBookByIsbn(@PathVariable String isbn, @ModelAttribute Book book, Model model) {

        model.addAttribute("book", booksService.findByIsbn(isbn));
        return "book";
    }
}
