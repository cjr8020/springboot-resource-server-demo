package com.standard.demo.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * AuthnContextDetail configuration factory
 */
@Configuration
public class AuthnContextDetailsFactory {

  @Bean
  @Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
  public AuthnContextDetails createAuthnContextDetails() {
    // this method should be called on every request
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    return new AuthnContextDetails(authentication);
  }

}
