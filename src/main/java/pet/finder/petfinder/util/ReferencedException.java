package pet.finder.petfinder.util;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * ReferencedException is an exception class used to indicate a conflict or
 * reference issue.
 * It returns an HTTP status code 409 (Conflict) when thrown.
 */
@ResponseStatus(HttpStatus.CONFLICT)
public class ReferencedException extends RuntimeException {

    /**
     * Constructs a new ReferencedException with no detail message.
     */
    public ReferencedException() {
        super();
    }

    /**
     * Constructs a new ReferencedException with a specified referenced warning
     * message.
     *
     * @param referencedWarning the referenced warning providing the detail message
     */
    public ReferencedException(final ReferencedWarning referencedWarning) {
        super(referencedWarning.toMessage());
    }

}
