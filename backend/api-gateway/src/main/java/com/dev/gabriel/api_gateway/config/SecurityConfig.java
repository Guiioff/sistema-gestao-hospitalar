package com.dev.gabriel.api_gateway.config;

import com.dev.gabriel.api_gateway.utils.KeyUtils;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;

@Configuration
public class SecurityConfig {
  @Value("${security.caminho-chave-publica}")
  private String caminhoChavePublica;

  private RSAPublicKey publicKey;

  @PostConstruct
  public void loadKeys() throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
    this.publicKey = (RSAPublicKey) KeyUtils.decodeKey(this.caminhoChavePublica);
  }

  @Bean
  public JwtDecoder jwtDecoder() {
    return NimbusJwtDecoder.withPublicKey(this.publicKey).build();
  }
}
