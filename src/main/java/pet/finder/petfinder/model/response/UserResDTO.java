package pet.finder.petfinder.model.response;

import lombok.Data;

/**
 * Represents a response data transfer object (DTO) for user details.
 */
@Data
public class UserResDTO {

    private Long id;

    private String name;

    private String username;

    private String email;

    private String password;
}
