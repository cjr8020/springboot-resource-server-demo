package com.standard.demo.resources;

import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api")
public class HelloWorldResource {

  private static final Logger log = LoggerFactory.getLogger(HelloWorldResource.class);

  @RequestMapping(value = "hello")
  @PreAuthorize("hasAuthority('ADMIN_USER') or hasAuthority('STANDARD_USER')")
  public String hello() {
    log.info("say hello.. ");

    /*
     * TODO: refactor this as some sort of AuthDetails scoped proxy.
     */
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    Object details = authentication.getDetails();
    if (details instanceof OAuth2AuthenticationDetails) {
      OAuth2AuthenticationDetails authDetails = (OAuth2AuthenticationDetails) details;

      /*
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
      log.info("OAuth2AuthenticationDetails: {}", authDetails.getDecodedDetails());

      @SuppressWarnings("unchecked")
      Map<String, Object> decodedDetails = (Map<String, Object>)authDetails.getDecodedDetails();

      log.info("jit: {}", decodedDetails.get("jit") );
    } else {
      log.warn("not OAuth2AuthenticationDetails");
    }

    return "hello world";
  }

  @RequestMapping(value = "admin-hello")
  @PreAuthorize("hasAuthority('ADMIN_USER')")
  public String adminHello() {
    log.info("say ADMIN hello..");
    return "ADMIN hello world";
  }

}
