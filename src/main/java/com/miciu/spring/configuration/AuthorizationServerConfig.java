package com.miciu.spring.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
  static final int ACCESS_TOKEN_VALIDITY_SECONDS = 5 * 60;
  public static final String CLIENT_CREDENTIALS = "client_credentials";
  public static final String NO_ENCRYPT = "{noop}";

  //username
  public static final String READ_CLIENT_ID = "client_read";
  public static final String WRITE_CLIENT_ID = "client_write";
  //password
  public static final String CLIENT_SECRET = "secret";

  public static final String SCOPE_READ = "read";
  public static final String SCOPE_WRITE = "write";

  @Autowired
  private AuthenticationManager authenticationManager;

  @Override
  public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
    endpoints.authenticationManager(authenticationManager);
  }

  @Override
  public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
    clients.inMemory()
        .withClient(READ_CLIENT_ID)
        .secret(NO_ENCRYPT + CLIENT_SECRET)
        .authorizedGrantTypes(CLIENT_CREDENTIALS)
        .scopes(SCOPE_READ)
        .and()
        .withClient(WRITE_CLIENT_ID)
        .secret(NO_ENCRYPT + CLIENT_SECRET)
        .authorizedGrantTypes(CLIENT_CREDENTIALS)
        .scopes(SCOPE_WRITE)
        .accessTokenValiditySeconds(ACCESS_TOKEN_VALIDITY_SECONDS);
  }
}