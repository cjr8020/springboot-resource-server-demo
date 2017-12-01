package com.standard.demo.configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;

/**
 * AuthnContextDetails
 *
   OAuth2AuthenticationDetails: {
     aud=[test-oauth2-resourceid],
     user_name=john.doe,
     scope=[read, write, trust],
     exp=1512009851,
     authorities=[STANDARD_USER],
     jti=df64d274-4f30-433c-8557-c27004c3e4bf,
     client_id=test-web-clientid
   }
 */
public class AuthnContextDetails {

  private static final Logger log = LoggerFactory.getLogger(AuthnContextDetails.class);

  private List<String> audience;
  private String username;
  private List<String> scopes;
  private Long expriresTimestamp;
  private List<String> authorities;
  private String jti;
  private String clientId;

  @SuppressWarnings("unchecked")
  public AuthnContextDetails(final Authentication authentication) {

    Object details = authentication.getDetails();
    try {
      OAuth2AuthenticationDetails authDetails = (OAuth2AuthenticationDetails) details;
      log.info("OAuth2AuthenticationDetails: {}", authDetails.getDecodedDetails());
      Map<String, Object> decodedDetails = (Map<String, Object>) authDetails.getDecodedDetails();
      this.audience = (ArrayList<String>) decodedDetails.get("aud");
      this.username = (String) decodedDetails.get("user_name");
      this.jti = (String) decodedDetails.get("jti");
    } catch (Throwable throwable) {
      log.error("not OAuth2 Authentication Details. Error: {}", throwable);
    }
  }

  public List<String> getAudience() {
    return audience;
  }

  public void setAudience(List<String> audience) {
    this.audience = audience;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public List<String> getScopes() {
    return scopes;
  }

  public void setScopes(List<String> scopes) {
    this.scopes = scopes;
  }

  public Long getExpriresTimestamp() {
    return expriresTimestamp;
  }

  public void setExpriresTimestamp(Long expriresTimestamp) {
    this.expriresTimestamp = expriresTimestamp;
  }

  public List<String> getAuthorities() {
    return authorities;
  }

  public void setAuthorities(List<String> authorities) {
    this.authorities = authorities;
  }

  public String getJti() {
    return jti;
  }

  public void setJti(String jti) {
    this.jti = jti;
  }

  public String getClientId() {
    return clientId;
  }

  public void setClientId(String clientId) {
    this.clientId = clientId;
  }
}
