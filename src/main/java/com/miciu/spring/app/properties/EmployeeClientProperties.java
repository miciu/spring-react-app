package com.miciu.spring.app.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@Data
@ConfigurationProperties(prefix = "oauth2.client.employee")
public class EmployeeClientProperties {
  private String clientId;
  private String clientSecret;
  private String accessTokenUri;
  private List<String> scopes;
}
