package id.vanggraini.atm.monitoring_app.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import id.vanggraini.atm.monitoring_app.config.Jwt;
import id.vanggraini.atm.monitoring_app.dto.auth.AuthRequest;
import id.vanggraini.atm.monitoring_app.dto.auth.AuthResponse;
import id.vanggraini.atm.monitoring_app.service.impl.UserDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final UserDetailsServiceImpl userDetailsService;
    private final JwtService jwtService;
    private final RsaKeyService rsaKeyService;

    public AuthResponse login(AuthRequest request) {
        log.info("Login attempt for user: {}", request.getUsername());
        try {
            String plainPassword = rsaKeyService.decrypt(request.getPassword());

            authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), plainPassword)
            );

            UserDetails user = userDetailsService.loadUserByUsername(request.getUsername());
            Jwt accessToken = jwtService.generateAccessToken(user);
            Jwt refreshToken = jwtService.generateRefreshToken(user);

            log.info("Login successful for user: {}", request.getUsername());
            return AuthResponse.builder().accessToken(accessToken).refreshToken(refreshToken).build();
        } catch (BadCredentialsException e) {
            log.warn("Login failed for user: {} — {}", request.getUsername(), e.getMessage());
            throw e;
        }
    }

    public AuthResponse refresh(String refreshToken) {
        log.info("Refresh token attempt");
        Jwt jwt = jwtService.parseToken(refreshToken);
        if (jwt == null || jwt.isExpired()) {
            log.warn("Refresh failed — invalid or expired refresh token");
            throw new BadCredentialsException("Invalid or expired refresh token");
        }

        UserDetails userDetails = userDetailsService.loadUserByUsername(jwt.getUsername());
        Jwt newAccessToken = jwtService.generateAccessToken(userDetails);
        Jwt newRefreshToken = jwtService.generateRefreshToken(userDetails);

        log.info("Refresh successful for user: {}", jwt.getUsername());
        return AuthResponse.builder().accessToken(newAccessToken).refreshToken(newRefreshToken).build();
    }
}
