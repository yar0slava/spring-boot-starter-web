package kma.topic3.webstarter.model.entities;

import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Pattern(regexp = "^[a-zA-Z0-9]*$", message = "login should contain only letters and digits")
    @Column(name = "login", unique = true)
    private String login;

    @Size(min = 8, max = 20, message = "length of password should be between 8-20")
    @Column(name = "password")
    private String password;

    @JsonIgnoreProperties("users")
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_to_book",
    joinColumns = @JoinColumn(name = "user_id"),
    inverseJoinColumns = @JoinColumn(name = "book_id"))
    private List<BookEntity> books;

    @ManyToMany
    @JoinTable(
            name = "user_to_permissions",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "permission_id")
    )
    private List<PermissionEntity> permissions;
}
