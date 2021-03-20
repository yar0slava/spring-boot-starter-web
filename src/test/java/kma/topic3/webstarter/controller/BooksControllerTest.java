package kma.topic3.webstarter.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import kma.topic3.webstarter.model.Book;
import kma.topic3.webstarter.service.BooksService;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BooksController.class)
class BooksControllerTest {

    @MockBean
    private BooksService booksService;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @SneakyThrows
    void shouldHandleSaveRequest() {

        byte[] book = BooksControllerTest.class.getResourceAsStream("/addRequest.json").readAllBytes();
//        Book bookObj = objectMapper.convertValue(String.valueOf(book),Book.class);
        Book bookObj = new Book("123456qwe","Harry Potter", "J. K. Rowling");

        when(booksService.getAll()).thenReturn(List.of(bookObj));

        mockMvc.perform(
                post("/save")
                        .content(book)
                        .contentType(MediaType.APPLICATION_JSON))

                .andExpect(status().isOk())
                .andExpect(content().json(new String(BooksControllerTest.class.getResourceAsStream("/addResponse.json").readAllBytes())));

        verify(booksService).add(any());
        verify(booksService).getAll();

    }

    @Test
    @SneakyThrows
    void shouldHandleFindRequest() {

        byte[] book = BooksControllerTest.class.getResourceAsStream("/addRequest.json").readAllBytes();
//        Book bookObj = objectMapper.convertValue(String.valueOf(book),Book.class);
        Book bookObj = new Book("123456qwe","Harry Potter", "J. K. Rowling");

        when(booksService.findByIsbnNameAuthor(any(),any(),any())).thenReturn(List.of(bookObj));

        mockMvc.perform(
                post("/find")
                        .content(book)
                        .contentType(MediaType.APPLICATION_JSON))

                .andExpect(status().isOk())
                .andExpect(content().json(new String(BooksControllerTest.class.getResourceAsStream("/addResponse.json").readAllBytes())));

        verify(booksService).findByIsbnNameAuthor("123456qwe","Harry Potter", "J. K. Rowling");
    }

    @Test
    @SneakyThrows
    void shouldHandleGetAllRequest() {

        byte[] book = BooksControllerTest.class.getResourceAsStream("/addRequest.json").readAllBytes();
//        Book bookObj = objectMapper.convertValue(String.valueOf(book),Book.class);
        Book bookObj = new Book("123456qwe","Harry Potter", "J. K. Rowling");

        when(booksService.getAll()).thenReturn(List.of(bookObj));

        mockMvc.perform(
                get("/all")
                        .contentType(MediaType.APPLICATION_JSON))

                .andExpect(status().isOk())
                .andExpect(content().json(new String(BooksControllerTest.class.getResourceAsStream("/addResponse.json").readAllBytes())));

        verify(booksService).getAll();
    }
}