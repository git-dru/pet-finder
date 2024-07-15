package pet.finder.petfinder.repos;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import pet.finder.petfinder.domain.User;
import pet.finder.petfinder.domain.UserAction;

/**
 * Repository interface for managing UserAction entities.
 */
public interface UserActionRepository extends JpaRepository<UserAction, Long> {

    /**
     * Retrieves the first UserAction associated with the specified action user.
     *
     * @param user The user performing the action.
     * @return The first UserAction associated with the user.
     */
    UserAction findFirstByActionUser(User user);

    /**
     * Retrieves an Optional UserAction by action user ID and pet ID.
     *
     * @param userId The ID of the action user.
     * @param petId  The ID of the pet.
     * @return Optional containing UserAction if found, empty otherwise.
     */
    Optional<UserAction> findByActionUserIdAndPetId(@Param("userId") Long userId, @Param("petId") Long petId);

    /**
     * Deletes UserActions by action user ID and pet ID.
     *
     * @param userId The ID of the action user.
     * @param petId  The ID of the pet.
     * @return The number of UserActions deleted.
     */
    long deleteByActionUserIdAndPetId(@Param("userId") Long userId, @Param("petId") Long petId);
}
