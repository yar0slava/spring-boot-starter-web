package kma.topic3.webstarter.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "book")
public class Book {

    @Id
    @Column(name = "isbn")
    String isbn;

    @Column(name = "name")
    String name;

    @Column(name = "author")
    String author;
}
