package kma.topic3.webstarter.model;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Book {

    String name;
    String isbn;
    String author;
}
