package pet.finder.petfinder.util;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Custom exception to represent application-specific errors that result in an
 * internal server error (500).
 */
@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class AppException extends RuntimeException {

    /**
     * Constructs a new application exception with the specified detail message.
     *
     * @param message the detail message (which is saved for later retrieval by the
     *                getMessage() method).
     */
    public AppException(String message) {
        super(message);
    }

    /**
     * Constructs a new application exception with the specified detail message and
     * cause.
     *
     * @param message the detail message (which is saved for later retrieval by the
     *                getMessage() method).
     * @param cause   the cause (which is saved for later retrieval by the
     *                getCause() method).
     */
    public AppException(String message, Throwable cause) {
        super(message, cause);
    }
}
