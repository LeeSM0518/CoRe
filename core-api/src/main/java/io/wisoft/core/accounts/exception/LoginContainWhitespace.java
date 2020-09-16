package io.wisoft.core.accounts.exception;

import org.springframework.security.core.AuthenticationException;

public class LoginContainWhitespace extends AuthenticationException {
  public LoginContainWhitespace(String error) {
    super(error);
  }
}
