package id.vanggraini.atm.monitoring_app.service;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.util.Base64;

import javax.crypto.Cipher;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class RsaKeyService {

    private final KeyPair keyPair;

    public RsaKeyService() throws Exception {
        KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
        generator.initialize(2048);
        this.keyPair = generator.generateKeyPair();
        log.info("RSA-2048 key pair generated successfully");
    }

    public String getPublicKeyPem() {
        log.debug("Public key requested");
        return "-----BEGIN PUBLIC KEY-----\n" + Base64.getMimeEncoder(64, "\n".getBytes()).encodeToString(keyPair.getPublic().getEncoded()) + "\n-----END PUBLIC KEY-----";
    }

    public String decrypt(String encryptedBase64) {
        try {
            byte[] encryptedBytes = Base64.getDecoder().decode(encryptedBase64);
            Cipher cipher = Cipher.getInstance("RSA/ECB/OAEPWithSHA-256AndMGF1Padding");
            cipher.init(Cipher.DECRYPT_MODE, keyPair.getPrivate());
            log.debug("RSA decryption successful");
            return new String(cipher.doFinal(encryptedBytes));
        } catch (Exception e) {
            log.warn("RSA decryption failed: {}", e.getMessage());
            throw new BadCredentialsException("Invalid username or password");
        }
    }
}
