package pet.finder.petfinder.service;

import org.springframework.stereotype.Service;

import jakarta.validation.Valid;
import pet.finder.petfinder.domain.User;
import pet.finder.petfinder.domain.UserAction;
import pet.finder.petfinder.model.request.SignUpReqDTO;
import pet.finder.petfinder.model.response.UserResDTO;
import pet.finder.petfinder.repos.UserActionRepository;
import pet.finder.petfinder.repos.UserRepository;
import pet.finder.petfinder.util.NotFoundException;
import pet.finder.petfinder.util.ReferencedWarning;

/**
 * Service class for managing users.
 */
@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserActionRepository userActionRepository;

    /**
     * Constructor injection of dependencies.
     *
     * @param userRepository       Repository for accessing user data.
     * @param userActionRepository Repository for accessing user action data.
     */
    public UserService(final UserRepository userRepository,
            final UserActionRepository userActionRepository) {
        this.userRepository = userRepository;
        this.userActionRepository = userActionRepository;
    }

    /**
     * Retrieves a user by ID.
     *
     * @param id ID of the user to retrieve.
     * @return UserResDTO representing the retrieved user.
     * @throws NotFoundException if the user with the given ID does not exist.
     */
    public UserResDTO get(final Long id) {
        return userRepository.findById(id)
                .map(user -> mapToDTO(user, new UserResDTO()))
                .orElseThrow(NotFoundException::new);
    }

    /**
     * Updates an existing user with the provided ID using the data from
     * SignUpReqDTO.
     *
     * @param id      ID of the user to update.
     * @param userDTO DTO containing updated user information.
     * @throws NotFoundException if the user with the given ID does not exist.
     */
    public void update(final Long id, final @Valid SignUpReqDTO userDTO) {
        final User user = userRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(userDTO, user);
        userRepository.save(user);
    }

    /**
     * Deletes a user by ID.
     *
     * @param id ID of the user to delete.
     */
    public void delete(final Long id) {
        userRepository.deleteById(id);
    }

    /**
     * Maps properties from User entity to UserResDTO.
     *
     * @param user    User entity to map.
     * @param userDTO UserResDTO to populate.
     * @return Populated UserResDTO.
     */
    private UserResDTO mapToDTO(final User user, final UserResDTO userDTO) {
        userDTO.setId(user.getId());
        userDTO.setName(user.getName());
        userDTO.setUsername(user.getUsername());
        userDTO.setEmail(user.getEmail());
        userDTO.setPassword(user.getPassword());
        return userDTO;
    }

    /**
     * Maps properties from SignUpReqDTO to User entity.
     *
     * @param userDTO SignUpReqDTO containing user data.
     * @param user    User entity to populate.
     * @return Populated User entity.
     */
    private User mapToEntity(final @Valid SignUpReqDTO userDTO, final User user) {
        user.setUsername(userDTO.getUsername());
        userDTO.setName(user.getName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        return user;
    }

    /**
     * Checks if an email already exists in the database.
     *
     * @param email Email to check.
     * @return true if the email exists, false otherwise.
     */
    public boolean emailExists(final String email) {
        return userRepository.existsByEmail(email);
    }

    /**
     * Retrieves a ReferencedWarning for a user with the given ID.
     * Checks if the user has associated actions and constructs a warning if so.
     *
     * @param id ID of the user to check for referenced actions.
     * @return ReferencedWarning indicating any associated actions, or null if none
     *         found.
     * @throws NotFoundException if the user with the given ID does not exist.
     */
    public ReferencedWarning getReferencedWarning(final Long id) {
        final ReferencedWarning referencedWarning = new ReferencedWarning();
        final User user = userRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        final UserAction actionUserUserAction = userActionRepository.findFirstByActionUser(user);
        if (actionUserUserAction != null) {
            referencedWarning.setKey("user.userAction.actionUser.referenced");
            referencedWarning.addParam(actionUserUserAction.getId());
            return referencedWarning;
        }
        return null;
    }

}
