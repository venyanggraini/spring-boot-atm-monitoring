package id.vanggraini.atm.monitoring_app.config;

import java.util.Date;

import javax.crypto.SecretKey;

import com.fasterxml.jackson.annotation.JsonValue;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

public class Jwt {

    private final Claims claims;
    private final SecretKey secretKey;

    public Jwt(Claims claims, SecretKey secretKey) {
        this.claims = claims;
        this.secretKey = secretKey;
    }

    public boolean isExpired() {
        return claims.getExpiration().before(new Date());
    }

    public String getUsername() {
        return claims.getSubject();
    }

    @JsonValue
    public String toString() {
        return Jwts.builder().claims(claims).signWith(secretKey).compact();
    }
}
