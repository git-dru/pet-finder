package pet.finder.petfinder.rest;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pet.finder.petfinder.enums.ActionType;
import pet.finder.petfinder.model.request.UserActionReqDTO;
import pet.finder.petfinder.model.response.UserActionResDTO;
import pet.finder.petfinder.service.UserActionService;
import pet.finder.petfinder.util.JwtTokenProvider;

/**
 * REST Controller for handling user actions on pets.
 */
@RestController
@RequestMapping(value = "/api/userActions", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserActionResource {

    private final UserActionService userActionService;

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    /**
     * Constructor injection of UserActionService.
     *
     * @param userActionService The UserActionService instance to use.
     */
    public UserActionResource(final UserActionService userActionService) {
        this.userActionService = userActionService;
    }

    /**
     * Endpoint to retrieve all user actions for the authenticated user.
     *
     * @param token The JWT token in the Authorization header.
     * @return ResponseEntity containing a list of UserActionResDTOs.
     */
    @GetMapping
    public ResponseEntity<List<UserActionResDTO>> getAllUserActions(
            @RequestHeader(name = "Authorization") String token) {
        Long userId = jwtTokenProvider.getUserIdFromJWT(token);
        return ResponseEntity.ok(userActionService.findAll(userId));
    }

    /**
     * Endpoint to retrieve the action type for a specific pet by its ID.
     *
     * @param token The JWT token in the Authorization header.
     * @param petId The ID of the pet.
     * @return ResponseEntity containing the ActionType for the given pet ID.
     */
    @GetMapping("/{petId}")
    public ResponseEntity<ActionType> getUserAction(@RequestHeader(name = "Authorization") String token,
            @PathVariable(name = "petId") final Long petId) {
        Long userId = jwtTokenProvider.getUserIdFromJWT(token);
        return ResponseEntity.ok(userActionService.get(userId, petId));
    }

    /**
     * Endpoint to create or update a user action for a pet.
     *
     * @param token         The JWT token in the Authorization header.
     * @param userActionDTO The UserActionReqDTO containing pet ID and action type.
     * @return ResponseEntity containing the ActionType created or updated.
     */
    @PostMapping
    public ResponseEntity<ActionType> createUserAction(
            @RequestHeader(name = "Authorization") String token,
            @RequestBody @Valid final UserActionReqDTO userActionDTO) {
        Long userId = jwtTokenProvider.getUserIdFromJWT(token);
        final ActionType action = userActionService.createOrUpdate(userId, userActionDTO);
        return new ResponseEntity<>(action, HttpStatus.CREATED);
    }

    /**
     * Endpoint to delete a user action for a specific pet by its ID.
     *
     * @param token The JWT token in the Authorization header.
     * @param petId The ID of the pet.
     * @return ResponseEntity with no content upon successful deletion.
     */
    @DeleteMapping("/{petId}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deleteUserAction(@RequestHeader(name = "Authorization") String token,
            @PathVariable(name = "petId") final Long petId) {
        Long userId = jwtTokenProvider.getUserIdFromJWT(token);
        userActionService.delete(userId, petId);
        return ResponseEntity.noContent().build();
    }
}
