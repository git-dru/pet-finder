package pet.finder.petfinder.model.response;

/**
 * Represents a response data transfer object (DTO) for JWT authentication.
 */
public class JwtAuthenticationResDTO {
    private String accessToken;
    private String tokenType = "Bearer";

    /**
     * Constructs a JWT authentication response with an access token.
     *
     * @param accessToken String containing the JWT access token.
     */
    public JwtAuthenticationResDTO(String accessToken) {
        this.accessToken = accessToken;
    }

    /**
     * Retrieves the JWT access token.
     *
     * @return String containing the JWT access token.
     */
    public String getAccessToken() {
        return accessToken;
    }

    /**
     * Sets the JWT access token.
     *
     * @param accessToken String containing the JWT access token to set.
     */
    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    /**
     * Retrieves the token type (always "Bearer" for JWT tokens).
     *
     * @return String containing the token type.
     */
    public String getTokenType() {
        return tokenType;
    }

    /**
     * Sets the token type.
     *
     * @param tokenType String containing the token type to set.
     */
    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }
}
