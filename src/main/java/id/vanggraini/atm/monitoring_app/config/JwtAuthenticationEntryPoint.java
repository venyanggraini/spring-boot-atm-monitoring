package id.vanggraini.atm.monitoring_app.config;

import java.io.IOException;
import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import id.vanggraini.atm.monitoring_app.dto.ErrorResponse;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import tools.jackson.databind.ObjectMapper;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private final ObjectMapper objectMapper;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException authException) throws IOException {

        String message = "Unauthorized";
        Exception jwtException = (Exception) request.getAttribute("jwt_exception");

        if (jwtException instanceof ExpiredJwtException) {
            message = "Token expired, please login again";
        } else if (jwtException != null) {
            message = "Invalid token";
        }

        log.warn("Unauthorized request to {} {} — {}", request.getMethod(), request.getRequestURI(), message);

        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        objectMapper.writeValue(response.getOutputStream(),
                new ErrorResponse(HttpStatus.UNAUTHORIZED.value(), message, LocalDateTime.now()));
    }
}
