package pet.finder.petfinder.util;

import java.util.ArrayList;
import java.util.stream.Collectors;
import lombok.Getter;
import lombok.Setter;

/**
 * ReferencedWarning is a class representing a warning message with optional
 * parameters.
 * It provides methods to add parameters and generate a formatted message
 * string.
 */
@Getter
@Setter
public class ReferencedWarning {

    private String key = null;
    private ArrayList<Object> params = new ArrayList<>();

    /**
     * Adds a parameter to the list of parameters for the warning message.
     *
     * @param param the parameter to add
     */
    public void addParam(final Object param) {
        params.add(param);
    }

    /**
     * Generates and returns the formatted warning message as a string.
     *
     * @return the formatted warning message
     */
    public String toMessage() {
        String message = key;
        if (!params.isEmpty()) {
            message += "," + params.stream()
                    .map(Object::toString)
                    .collect(Collectors.joining(","));
        }
        return message;
    }

}
