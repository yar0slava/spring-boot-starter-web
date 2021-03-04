package kma.topic3.webstarter.controller;

import io.restassured.RestAssured;
import kma.topic3.webstarter.model.Book;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BooksControllerIt {

    @LocalServerPort
    void savePort(final int port) {
        // save port of locally starter server during test
        RestAssured.port = port;
    }

    @Test
    void shouldSendSaveRequest() {
        Book[] response = given()
                .body(BooksControllerIt.class.getResourceAsStream("/addRequest.json"))
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .post("/save")
                .then()

                .extract()
                .as(Book[].class);

        assertThat(response[0]).isEqualTo(new Book("Harry Potter", "123456qwe", "J. K. Rowling"));
    }

    @Test
    void shouldSendFindRequest() {

        given().body(BooksControllerIt.class.getResourceAsStream("/addRequest.json"))
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .post("/save");

        Book[] response = given()
                .body(BooksControllerIt.class.getResourceAsStream("/findRequest.json"))
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .post("/find")
                .then()

                .extract()
                .as(Book[].class);

        assertThat(response[0]).isEqualTo(new Book("Harry Potter", "123456qwe", "J. K. Rowling"));
    }

    @Test
    void shouldSendGetAllRequest() {

        given().body(BooksControllerIt.class.getResourceAsStream("/addRequest.json"))
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .post("/save");

        Book[] response = given()
                .when()
                .get("/all")
                .then()

                .extract()
                .as(Book[].class);

        assertThat(response[0]).isEqualTo(new Book("Harry Potter", "123456qwe", "J. K. Rowling"));
    }
}