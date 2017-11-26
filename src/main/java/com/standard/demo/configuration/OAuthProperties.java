package com.standard.demo.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * OAuth properties.
 * Using spring's typesafe configuration properties.
 */
@ConfigurationProperties(prefix = "oauth")
public class OAuthProperties {

  private String resourceId;
  private String signingKey;

  public String getResourceId() {
    return resourceId;
  }
  public void setResourceId(String resourceId) {
    this.resourceId = resourceId;
  }

  public String getSigningKey() {
    return signingKey;
  }
  public void setSigningKey(String signingKey) {
    this.signingKey = signingKey;
  }

}
