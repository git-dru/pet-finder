package pet.finder.petfinder.rest;

import pet.finder.petfinder.domain.User;
import pet.finder.petfinder.model.response.ApiResDTO;
import pet.finder.petfinder.model.response.JwtAuthenticationResDTO;
import pet.finder.petfinder.model.request.LoginReqDTO;
import pet.finder.petfinder.model.request.SignUpReqDTO;
import pet.finder.petfinder.repos.UserRepository;
import pet.finder.petfinder.util.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import jakarta.validation.Valid;
import java.net.URI;

/**
 * REST Controller for user authentication and registration.
 */
@RestController
@RequestMapping("/api/auth")
public class AuthResource {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtTokenProvider tokenProvider;

    /**
     * Endpoint to authenticate a user with email and password.
     *
     * @param loginRequest The LoginReqDTO containing email and password.
     * @return ResponseEntity containing a JwtAuthenticationResDTO with JWT token
     *         upon successful authentication.
     */
    @PostMapping("/signin")
    public ResponseEntity<JwtAuthenticationResDTO> authenticateUser(@Valid @RequestBody LoginReqDTO loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.generateToken(authentication);
        return ResponseEntity.ok(new JwtAuthenticationResDTO(jwt));
    }

    /**
     * Endpoint to register a new user.
     *
     * @param signUpRequest The SignUpReqDTO containing user details.
     * @return ResponseEntity containing an ApiResDTO indicating success or failure
     *         of user registration.
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpReqDTO signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return new ResponseEntity(new ApiResDTO(false, "Username is already taken!"),
                    HttpStatus.BAD_REQUEST);
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return new ResponseEntity(new ApiResDTO(false, "Email Address already in use!"),
                    HttpStatus.BAD_REQUEST);
        }

        User user = new User(signUpRequest.getName(), signUpRequest.getUsername(),
                signUpRequest.getEmail(), signUpRequest.getPassword());

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        User result = userRepository.save(user);

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/users/{username}")
                .buildAndExpand(result.getUsername()).toUri();

        return ResponseEntity.created(location).body(new ApiResDTO(true, "User registered successfully"));
    }
}
