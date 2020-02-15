package com.miciu.spring.app.currentuser;

public class CurrentUser {
  private String name;
  private String roles;

  public CurrentUser(String name, String roles) {
    this.name = name;
    this.roles = roles;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getRoles() {
    return roles;
  }

  public void setRoles(String roles) {
    this.roles = roles;
  }
}
