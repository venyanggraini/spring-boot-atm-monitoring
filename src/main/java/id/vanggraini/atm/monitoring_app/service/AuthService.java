package id.vanggraini.atm.monitoring_app.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

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
            String token = jwtService.generateToken(user);
            log.info("Login successful for user: {}", request.getUsername());
            return AuthResponse.builder().token(token).build();
        } catch (BadCredentialsException e) {
            log.warn("Login failed for user: {} — {}", request.getUsername(), e.getMessage());
            throw e;
        }
    }
}
