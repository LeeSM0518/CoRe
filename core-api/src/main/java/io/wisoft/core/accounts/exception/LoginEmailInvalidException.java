package io.wisoft.core.accounts.exception;

import org.springframework.security.core.AuthenticationException;

public class LoginEmailInvalidException extends AuthenticationException {
  public LoginEmailInvalidException(String error) {
    super(error);
  }
}
