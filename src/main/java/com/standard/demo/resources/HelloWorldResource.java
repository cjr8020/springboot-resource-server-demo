package com.standard.demo.resources;

import com.standard.demo.configuration.AuthnContextDetails;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

  @Autowired
  private AuthnContextDetails authnContextDetails;

  @RequestMapping(value = "hello")
  @PreAuthorize("hasAuthority('ADMIN_USER') or hasAuthority('STANDARD_USER')")
  public String hello() {

    log.info("say hello.. ");

    // authnContextDetails shojld be injected per request
    log.info("JWT username: {}", authnContextDetails.getUsername());

    return "hello world";
  }

  @RequestMapping(value = "admin-hello")
  @PreAuthorize("hasAuthority('ADMIN_USER')")
  public String adminHello() {
    log.info("say ADMIN hello..");
    return "ADMIN hello world";
  }

}
