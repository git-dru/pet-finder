package pet.finder.petfinder.model.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import pet.finder.petfinder.enums.ActionType;

/**
 * Represents a user action request data transfer object (DTO).
 */
@Getter
@Setter
public class UserActionReqDTO {

    /**
     * Identifier of the pet involved in the action.
     * Constraints: Must not be null.
     */
    @NotNull
    private Long petId;

    /**
     * Type of action to be performed on the pet.
     * Constraints: Must not be null.
     */
    @NotNull
    private ActionType action;
}
