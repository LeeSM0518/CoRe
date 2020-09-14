package io.wisoft.core.accounts.exception;


import org.springframework.security.core.AuthenticationException;

public class LoginMethodNotEqualException extends AuthenticationException {

  public LoginMethodNotEqualException(String msg) {
    super(msg);
  }

}
