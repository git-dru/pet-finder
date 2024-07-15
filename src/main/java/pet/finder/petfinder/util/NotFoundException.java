package pet.finder.petfinder.util;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * NotFoundException is an exception class used to indicate that a requested
 * resource was not found.
 * It returns an HTTP status code 404 (Not Found) when thrown.
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException {

    /**
     * Constructs a new NotFoundException with no detail message.
     */
    public NotFoundException() {
        super();
    }

    /**
     * Constructs a new NotFoundException with the specified detail message.
     *
     * @param message the detail message (which is saved for later retrieval by the
     *                getMessage() method)
     */
    public NotFoundException(final String message) {
        super(message);
    }

}
