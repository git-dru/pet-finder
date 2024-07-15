package pet.finder.petfinder.rest;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pet.finder.petfinder.model.request.SignUpReqDTO;
import pet.finder.petfinder.model.response.UserResDTO;
import pet.finder.petfinder.service.UserService;
import pet.finder.petfinder.util.JwtTokenProvider;
import pet.finder.petfinder.util.ReferencedException;
import pet.finder.petfinder.util.ReferencedWarning;

/**
 * REST Controller for managing users.
 */
@RestController
@RequestMapping(value = "/api/users", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserResource {

    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;

    /**
     * Constructor injection of UserService dependency.
     *
     * @param userService Service for managing users.
     */
    public UserResource(final UserService userService, final JwtTokenProvider jwtTokenProvider) {
        this.userService = userService;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    /**
     * Endpoint to retrieve a user by ID.
     *
     * @param id ID of the user to retrieve.
     * @return ResponseEntity containing UserResDTO representing the retrieved user.
     */
    @GetMapping
    public ResponseEntity<UserResDTO> getUser(@RequestHeader(name = "Authorization") String token) {
        Long id = jwtTokenProvider.getUserIdFromJWT(token);
        return ResponseEntity.ok(userService.get(id));
    }

    /**
     * Endpoint to update a user.
     *
     * @param id      ID of the user to update.
     * @param userDTO Updated user information in SignUpReqDTO format.
     * @return ResponseEntity containing the ID of the updated user.
     */
    @PutMapping
    public ResponseEntity<Long> updateUser(@RequestHeader(name = "Authorization") String token,
            @RequestBody @Valid final SignUpReqDTO userDTO) {
        Long id = jwtTokenProvider.getUserIdFromJWT(token);
        userService.update(id, userDTO);
        return ResponseEntity.ok(id);
    }

    /**
     * Endpoint to delete a user.
     *
     * @param id ID of the user to delete.
     * @return ResponseEntity indicating success with HTTP status 204 (No Content).
     * @throws ReferencedException if the user is referenced elsewhere and cannot be
     *                             deleted.
     */
    @DeleteMapping
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deleteUser(@RequestHeader(name = "Authorization") String token) {
        Long id = jwtTokenProvider.getUserIdFromJWT(token);
        final ReferencedWarning referencedWarning = userService.getReferencedWarning(id);
        if (referencedWarning != null) {
            throw new ReferencedException(referencedWarning);
        }
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
