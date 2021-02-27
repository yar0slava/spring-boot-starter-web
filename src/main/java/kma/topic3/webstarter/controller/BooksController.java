package kma.topic3.webstarter.controller;

import kma.topic3.webstarter.model.Book;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class BooksController {

    List<Book> books = new ArrayList<>();

    @GetMapping("/book")
    public String saveBook(){
        return "books-get";
    }

    @PostMapping("/books")
    public String listBooks(@ModelAttribute Book book){
        books.add(book);
        return "redirect:/books-list";
    }

    @GetMapping("/books-list")
    public String booksList(Model model) {
        model.addAttribute("books", books);
        return "books-list";
    }
}
