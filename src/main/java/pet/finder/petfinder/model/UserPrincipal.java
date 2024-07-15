package pet.finder.petfinder.model;

import pet.finder.petfinder.domain.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Objects;

/**
 * Represents a custom implementation of Spring Security's UserDetails interface
 * that wraps a User entity for authentication and authorization purposes.
 */
@Getter
public class UserPrincipal implements UserDetails {
    private Long id;

    private String name;

    private String username;

    @JsonIgnore
    private String email;

    @JsonIgnore
    private String password;

    private Collection<? extends GrantedAuthority> authorities;

    /**
     * Constructs a UserPrincipal instance with the given details.
     */
    public UserPrincipal(Long id, String name, String username, String email, String password) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.email = email;
        this.password = password;
    }

    /**
     * Creates a UserPrincipal instance from a User entity.
     */
    public static UserPrincipal create(User user) {
        return new UserPrincipal(
                user.getId(),
                user.getName(),
                user.getUsername(),
                user.getEmail(),
                user.getPassword());
    }

    /**
     * Retrieves the username of the principal (inherited from UserDetails).
     */
    @Override
    public String getUsername() {
        return username;
    }

    /**
     * Retrieves the password of the principal (inherited from UserDetails).
     */
    @Override
    public String getPassword() {
        return password;
    }

    /**
     * Retrieves the authorities granted to the principal (inherited from
     * UserDetails).
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    /**
     * Checks if the account is not expired (inherited from UserDetails).
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * Checks if the account is not locked (inherited from UserDetails).
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * Checks if the credentials are not expired (inherited from UserDetails).
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * Checks if the account is enabled (inherited from UserDetails).
     */
    @Override
    public boolean isEnabled() {
        return true;
    }

    /**
     * Checks equality between two UserPrincipal instances based on their IDs.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        UserPrincipal that = (UserPrincipal) o;
        return Objects.equals(id, that.id);
    }

    /**
     * Generates a hash code for the UserPrincipal instance based on its ID.
     */
    @Override
    public int hashCode() {

        return Objects.hash(id);
    }
}
