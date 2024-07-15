package pet.finder.petfinder.repos;

import pet.finder.petfinder.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository interface for managing User entities.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    /**
     * Retrieves an Optional User by email.
     *
     * @param email The email of the user.
     * @return Optional containing User if found, empty otherwise.
     */
    Optional<User> findByEmail(String email);

    /**
     * Retrieves a list of Users by their IDs. (for scalability)
     *
     * @param userIds List of user IDs.
     * @return List of Users matching the provided IDs.
     */
    List<User> findByIdIn(List<Long> userIds);

    /**
     * Retrieves an Optional User by username. (for scalability)
     *
     * @param username The username of the user.
     * @return Optional containing User if found, empty otherwise.
     */
    Optional<User> findByUsername(String username);

    /**
     * Checks if a user exists by username.
     *
     * @param username The username to check.
     * @return true if a user exists with the given username, false otherwise.
     */
    Boolean existsByUsername(String username);

    /**
     * Checks if a user exists by email.
     *
     * @param email The email to check.
     * @return true if a user exists with the given email, false otherwise.
     */
    Boolean existsByEmail(String email);
}
