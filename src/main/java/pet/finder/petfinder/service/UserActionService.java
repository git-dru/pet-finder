package pet.finder.petfinder.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import pet.finder.petfinder.domain.User;
import pet.finder.petfinder.domain.UserAction;
import pet.finder.petfinder.enums.ActionType;
import pet.finder.petfinder.model.request.UserActionReqDTO;
import pet.finder.petfinder.model.response.UserActionResDTO;
import pet.finder.petfinder.repos.UserActionRepository;
import pet.finder.petfinder.repos.UserRepository;
import pet.finder.petfinder.util.NotFoundException;

/**
 * Service class for managing user actions related to pets.
 */
@Service
public class UserActionService {

    private final UserActionRepository userActionRepository;
    private final UserRepository userRepository;

    /**
     * Constructor for UserActionService.
     *
     * @param userActionRepository Repository for UserAction entities.
     * @param userRepository       Repository for User entities.
     */
    public UserActionService(final UserActionRepository userActionRepository,
            final UserRepository userRepository) {
        this.userActionRepository = userActionRepository;
        this.userRepository = userRepository;
    }

    /**
     * Retrieves all user actions for a given user.
     *
     * @param userId The ID of the user.
     * @return List of UserActionResDTO representing all user actions.
     */
    public List<UserActionResDTO> findAll(final Long userId) {
        final List<UserAction> userActions = userActionRepository.findAll(Sort.by("id"));
        return userActions.stream()
                .map(userAction -> mapToDTO(userId, userAction, new UserActionResDTO()))
                .toList();
    }

    /**
     * Retrieves the action type for a specific user and pet.
     *
     * @param userId The ID of the user.
     * @param petId  The ID of the pet.
     * @return ActionType representing the action performed by the user on the pet.
     * @throws NotFoundException if no user action is found for the given user and
     *                           pet.
     */
    public ActionType get(final Long userId, final Long petId) {
        return userActionRepository.findByActionUserIdAndPetId(userId, petId)
                .map(UserAction::getAction)
                .orElseThrow(NotFoundException::new);
    }

    /**
     * Creates or updates a user action based on the provided UserActionReqDTO.
     *
     * @param userId        The ID of the user performing the action.
     * @param userActionDTO The UserActionReqDTO containing action details.
     * @return ActionType representing the created or updated action.
     */
    public ActionType createOrUpdate(final Long userId, final @Valid UserActionReqDTO userActionDTO) {
        Optional<UserAction> existingUserAction = userActionRepository.findByActionUserIdAndPetId(userId,
                userActionDTO.getPetId());

        UserAction userAction;
        if (existingUserAction.isPresent()) {
            userAction = existingUserAction.get();
            // Update the existing UserAction
            mapToEntity(userId, userActionDTO, userAction);
        } else {
            userAction = new UserAction();
            mapToEntity(userId, userActionDTO, userAction);
        }

        return userActionRepository.save(userAction).getAction();
    }

    /**
     * Deletes a user action for a specific user and pet.
     *
     * @param userId The ID of the user.
     * @param petId  The ID of the pet.
     */
    @Transactional
    public void delete(final Long userId, final Long petId) {
        userActionRepository.deleteByActionUserIdAndPetId(userId, petId);
    }

    /**
     * Maps UserAction entity to UserActionResDTO for DTO transformation.
     *
     * @param userId        The ID of the user.
     * @param userAction    UserAction entity to map.
     * @param userActionDTO UserActionResDTO to populate.
     * @return UserActionResDTO populated with data from UserAction entity.
     */
    private UserActionResDTO mapToDTO(final Long userId, final UserAction userAction,
            final UserActionResDTO userActionDTO) {
        userActionDTO.setId(userAction.getId());
        userActionDTO.setPetId(userAction.getPetId());
        userActionDTO.setAction(userAction.getAction());
        userActionDTO.setActionUser(userAction.getActionUser() == null ? null : userAction.getActionUser().getId());
        return userActionDTO;
    }

    /**
     * Maps UserActionReqDTO to UserAction entity for entity transformation.
     *
     * @param userId        The ID of the user performing the action.
     * @param userActionDTO UserActionReqDTO containing action details.
     * @param userAction    UserAction entity to populate.
     * @return UserAction entity populated with data from UserActionReqDTO.
     */
    private UserAction mapToEntity(final Long userId, final @Valid UserActionReqDTO userActionDTO,
            final UserAction userAction) {
        userAction.setPetId(userActionDTO.getPetId());
        userAction.setAction(userActionDTO.getAction());
        final User actionUser = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("actionUser not found"));
        userAction.setActionUser(actionUser);
        return userAction;
    }

}
