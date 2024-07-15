package pet.finder.petfinder.model.response;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import pet.finder.petfinder.enums.ActionType;

/**
 * Represents a response data transfer object (DTO) for user action details.
 */
@Getter
@Setter
public class UserActionResDTO {

    private Long id;

    @NotNull
    private Long petId;

    private ActionType action;

    @NotNull
    private Long actionUser;

}
