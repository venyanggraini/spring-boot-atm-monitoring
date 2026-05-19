package id.vanggraini.atm.monitoring_app.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import id.vanggraini.atm.monitoring_app.dto.auth.AuthRequest;
import id.vanggraini.atm.monitoring_app.dto.auth.AuthResponse;
import id.vanggraini.atm.monitoring_app.service.AuthService;
import id.vanggraini.atm.monitoring_app.service.RsaKeyService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/v1/auth")
@AllArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final RsaKeyService rsaKeyService;

    @GetMapping("/init")
    public ResponseEntity<AuthResponse> init() {
        return ResponseEntity.ok(
            AuthResponse.builder().publicKey(rsaKeyService.getPublicKeyPem()).build()
        );
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody AuthRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }

    @PostMapping("/refresh")
    public ResponseEntity<AuthResponse> refresh(@RequestHeader("Authorization") String authHeader) {
        String token = authHeader.startsWith("Bearer ") ? authHeader.substring(7) : authHeader;
        return ResponseEntity.ok(authService.refresh(token));
    }

    @PostMapping("/logout")
    public ResponseEntity<AuthResponse> logout() {
        return ResponseEntity.ok(
            AuthResponse.builder().message("Logged out successfully").build()
        );
    }
}
