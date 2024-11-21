package com.dev.gabriel.api_gateway.config;

import com.dev.gabriel.api_gateway.utils.KeyUtils;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;

@Configuration
public class SecurityConfig {
  @Value("${security.caminho-chave-privada}")
  private String caminhoChavePrivada;

  @Value("${security.caminho-chave-publica}")
  private String caminhoChavePublica;

  private RSAPrivateKey privateKey;
  private RSAPublicKey publicKey;

  @PostConstruct
  public void loadKeys() throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
    this.privateKey = (RSAPrivateKey) KeyUtils.decodeKey(this.caminhoChavePrivada);
    this.publicKey = (RSAPublicKey) KeyUtils.decodeKey(this.caminhoChavePublica);
  }
}
