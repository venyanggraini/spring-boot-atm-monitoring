package id.vanggraini.atm.monitoring_app.dto.auth;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import id.vanggraini.atm.monitoring_app.config.Jwt;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@JsonInclude(Include.NON_NULL)
public class AuthResponse {
    private Jwt accessToken;
    private Jwt refreshToken;
    private String publicKey;
    private String message;
}
