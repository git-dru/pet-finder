package pet.finder.petfinder.util;

import io.jsonwebtoken.*;
import pet.finder.petfinder.model.UserPrincipal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * JwtTokenProvider is responsible for generating, validating, and parsing JWT
 * tokens.
 */
@Component
public class JwtTokenProvider {

    private static final Logger logger = LoggerFactory.getLogger(JwtTokenProvider.class);

    // JWT secret key and expiration time in milliseconds
    private String jwtSecret = "bXkgdHJlYXRpbmRlcmEgYXBwIHNlY3JldCBrZXkgYmFzZTY0IGNvZGUgZm9yIGVuY29kZSBhbmQgZGVjb2RlIC0+IHJlYWxseSBleGNpdGluZw==";

    private int jwtExpirationInMs = 24 * 60 * 2400;

    /**
     * Generates a JWT token for the given authentication object.
     *
     * @param authentication the authentication object containing user details
     * @return JWT token as a string
     */
    public String generateToken(Authentication authentication) {

        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();

        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtExpirationInMs);

        return Jwts.builder()
                .setSubject(Long.toString(userPrincipal.getId()))
                .setIssuedAt(new Date())
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    /**
     * Retrieves the user ID from the given JWT token.
     *
     * @param token JWT token string
     * @return user ID extracted from the token
     */
    public Long getUserIdFromJWT(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token.replace("Bearer ", ""))
                .getBody();

        return Long.parseLong(claims.getSubject());
    }

    /**
     * Validates the given JWT token.
     *
     * @param authToken JWT token to validate
     * @return true if the token is valid, false otherwise
     */
    public boolean validateToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException ex) {
            logger.error("Invalid JWT signature");
        } catch (MalformedJwtException ex) {
            logger.error("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            logger.error("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            logger.error("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            logger.error("JWT claims string is empty.");
        }
        return false;
    }
}
