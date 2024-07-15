package pet.finder.petfinder.domain;

import pet.finder.petfinder.domain.audit.DateAudit;
import org.hibernate.annotations.NaturalId;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

/**
 * Represents a user entity in the application.
 */
@Entity
@Getter
@Setter
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(columnNames = {
                "username"
        }),
        @UniqueConstraint(columnNames = {
                "email"
        })
})
public class User extends DateAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 40)
    private String name;

    @NotBlank
    @Size(max = 15)
    private String username;

    @NaturalId
    @NotBlank
    @Size(max = 40)
    @Email
    private String email;

    @NotBlank
    @Size(max = 100)
    private String password;

    /**
     * Default constructor for JPA compliance.
     */
    public User() {
    }

    /**
     * Constructor to initialize User with specific attributes.
     * 
     * @param name     Name of the user.
     * @param username Unique username of the user.
     * @param email    Unique email address of the user.
     * @param password Password of the user.
     */
    public User(String name, String username, String email, String password) {
        this.name = name;
        this.username = username;
        this.email = email;
        this.password = password;
    }
}
