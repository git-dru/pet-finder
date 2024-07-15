package pet.finder.petfinder.service;

import pet.finder.petfinder.util.NotFoundException;
import pet.finder.petfinder.domain.User;
import pet.finder.petfinder.model.UserPrincipal;
import pet.finder.petfinder.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service class for managing User details and authentication.
 */
@Service
public class AuthService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    /**
     * Retrieves a user by their email and creates a UserDetails object.
     *
     * @param email The email of the user.
     * @return UserDetails representing the user details.
     * @throws UsernameNotFoundException if no user is found with the given email.
     */
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(
                        "User not found with email : " + email));

        return UserPrincipal.create(user);
    }

    /**
     * Retrieves a user by their ID and creates a UserDetails object.
     *
     * @param id The ID of the user.
     * @return UserDetails representing the user details.
     * @throws NotFoundException if no user is found with the given ID.
     */
    @Transactional
    public UserDetails loadUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new NotFoundException("User not found"));

        return UserPrincipal.create(user);
    }
}
