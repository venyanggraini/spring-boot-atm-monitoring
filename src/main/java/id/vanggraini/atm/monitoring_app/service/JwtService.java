package id.vanggraini.atm.monitoring_app.service;

import java.util.Date;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import id.vanggraini.atm.monitoring_app.config.Jwt;
import id.vanggraini.atm.monitoring_app.config.JwtConfig;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class JwtService {

    private final JwtConfig jwtConfig;

    public Jwt generateAccessToken(UserDetails userDetails) {
        return generateToken(userDetails, jwtConfig.getAccessTokenExpiration());
    }

    public Jwt generateRefreshToken(UserDetails userDetails) {
        return generateToken(userDetails, jwtConfig.getRefreshTokenExpiration());
    }

    private Claims getClaims(String token) {
        return Jwts.parser()
            .verifyWith(jwtConfig.getSecretKey())
            .build()
            .parseSignedClaims(token)
            .getPayload();
    }

    public Jwt parseToken(String token) {
        try {
            var claims = getClaims(token);
            return new Jwt(claims, jwtConfig.getSecretKey());
        } catch (ExpiredJwtException e) {
            log.warn("Expired JWT token");
            throw e;
        } catch (JwtException e) {
            log.warn("Unauthorized");
            return null;
        }
    }

    private Jwt generateToken(UserDetails userDetails, Long tokenExpiration) {
        var claims = Jwts.claims()
            .subject(userDetails.getUsername())
            .issuedAt(new Date())
            .expiration(new Date(System.currentTimeMillis() + tokenExpiration))
            .build();
        
        return new Jwt(claims, jwtConfig.getSecretKey());
    }
}
