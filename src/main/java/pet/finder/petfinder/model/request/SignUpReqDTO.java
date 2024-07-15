package pet.finder.petfinder.model.request;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

/**
 * Represents a sign-up request data transfer object (DTO).
 */
@Getter
@Setter
public class SignUpReqDTO {
    /**
     * Name of the user signing up.
     * Constraints: Must not be blank, and length must be between 4 and 40
     * characters.
     */
    @NotBlank
    @Size(min = 4, max = 40)
    private String name;

    /**
     * Username chosen by the user signing up.
     * Constraints: Must not be blank, and length must be between 3 and 15
     * characters.
     */
    @NotBlank
    @Size(min = 3, max = 15)
    private String username;

    /**
     * Email address of the user signing up.
     * Constraints: Must not be blank, valid email format, and length must be up to
     * 40 characters.
     */
    @NotBlank
    @Size(max = 40)
    @Email
    private String email;

    /**
     * Password chosen by the user signing up.
     * Constraints: Must not be blank, and length must be between 6 and 20
     * characters.
     */
    @NotBlank
    @Size(min = 6, max = 20)
    private String password;
}
