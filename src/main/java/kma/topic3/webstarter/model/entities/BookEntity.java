package kma.topic3.webstarter.model.entities;

import kma.topic3.webstarter.model.entities.UserEntity;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
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

    @NotEmpty(message = "ISBN can not be empty")
    @Pattern(regexp = "^(\\d{13})?$", message = "ISBN must contain 13 digits")
    @Id
    @Column(name = "isbn")
    String isbn;

    @NotEmpty(message = "Name can not be empty")
    @Column(name = "name")
    String name;

    @NotEmpty(message = "Author can not be empty")
    @Column(name = "author")
    String author;

    @ManyToMany(mappedBy = "books", fetch = FetchType.LAZY)
    private List<UserEntity> users;
}
