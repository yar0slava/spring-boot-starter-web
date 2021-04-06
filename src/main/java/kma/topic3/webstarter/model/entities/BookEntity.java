package kma.topic3.webstarter.model.entities;

import kma.topic3.webstarter.model.entities.UserEntity;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "book")
@EqualsAndHashCode(exclude = "users")
@ToString(exclude = "users")
public class BookEntity {

    @Id
    @Column(name = "isbn")
    String isbn;

    @Column(name = "name")
    String name;

    @Column(name = "author")
    String author;

    @ManyToMany(mappedBy = "books", fetch = FetchType.LAZY)
    private List<UserEntity> users;
}
