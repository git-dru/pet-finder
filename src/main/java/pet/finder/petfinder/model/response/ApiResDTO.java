package pet.finder.petfinder.model.response;

/**
 * Represents a generic API response data transfer object (DTO).
 */
public class ApiResDTO {
    private Boolean success;
    private String message;

    /**
     * Constructs an API response with success status and message.
     *
     * @param success Boolean indicating the success status of the API operation.
     * @param message String containing a descriptive message related to the API
     *                operation.
     */
    public ApiResDTO(Boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    /**
     * Retrieves the success status of the API operation.
     *
     * @return Boolean representing the success status.
     */
    public Boolean getSuccess() {
        return success;
    }

    /**
     * Sets the success status of the API operation.
     *
     * @param success Boolean indicating the success status to set.
     */
    public void setSuccess(Boolean success) {
        this.success = success;
    }

    /**
     * Retrieves the message associated with the API operation.
     *
     * @return String containing the descriptive message.
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets the message associated with the API operation.
     *
     * @param message String containing the descriptive message to set.
     */
    public void setMessage(String message) {
        this.message = message;
    }
}
