package pet.finder.petfinder.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * JwtAuthenticationEntryPoint handles unauthorized access to protected
 * resources.
 * It implements Spring Security's AuthenticationEntryPoint interface to
 * customize
 * the behavior when authentication fails.
 */
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationEntryPoint.class);

    /**
     * Commence the authentication failure handling.
     *
     * @param httpServletRequest  the request that resulted in an authentication
     *                            failure
     * @param httpServletResponse the response to send
     * @param e                   the authentication exception that caused the
     *                            failure
     * @throws IOException      if an input or output exception occurs
     * @throws ServletException if a servlet exception occurs
     */
    @Override
    public void commence(HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse,
            AuthenticationException e) throws IOException, ServletException {
        logger.error("Responding with unauthorized error. Message - {}", e.getMessage());
        httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, e.getMessage());
    }
}
