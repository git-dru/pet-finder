package pet.finder.petfinder.model.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

/**
 * Represents a login request data transfer object (DTO).
 */
@Getter
@Setter
public class LoginReqDTO {
    /**
     * Email address of the user attempting to login.
     */
    @NotBlank
    private String email;

    /**
     * Password of the user attempting to login.
     */
    @NotBlank
    private String password;
}
