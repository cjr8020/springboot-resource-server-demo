package com.standard.demo.resources;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
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
    return "hello world";
  }

  @RequestMapping(value = "admin-hello")
  @PreAuthorize("hasAuthority('ADMIN_USER')")
  public String adminHello() {
    log.info("say ADMIN hello..");
    return "ADMIN hello world";
  }

}
