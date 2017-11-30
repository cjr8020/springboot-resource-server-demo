package com.standard.demo.configuration;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.JwtAccessTokenConverterConfigurer;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
@EnableConfigurationProperties(OAuthProperties.class)
public class JwtConfiguration {

  @Autowired
  private OAuthProperties oAuthProperties;

  @Bean
  public JwtAccessTokenConverter accessTokenConverter() {
    JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
    converter.setSigningKey(oAuthProperties.getSigningKey());
    converter.setAccessTokenConverter( new JwtConverter() );
    return converter;
  }

  @Bean
  public TokenStore tokenStore() {
    return new JwtTokenStore(accessTokenConverter());
  }

  @Bean
  public ResourceServerTokenServices tokenServices() {
    DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
    defaultTokenServices.setTokenStore(tokenStore());
    defaultTokenServices.setSupportRefreshToken(true);
    return defaultTokenServices;
  }

  /**
   * JWT Converter whose primary purpose is to copy
   * JWT content into OAuth2Authentication
   */
  public static class JwtConverter
      extends DefaultAccessTokenConverter
      implements JwtAccessTokenConverterConfigurer {

    @Override
    public void configure(JwtAccessTokenConverter converter) {
      converter.setAccessTokenConverter(this);
    }

    @Override
    public OAuth2Authentication extractAuthentication(Map<String, ?> map) {
      OAuth2Authentication auth = super.extractAuthentication(map);
      auth.setDetails(map); // copy JWT content into Authentication
      return auth;
    }
  }

}
