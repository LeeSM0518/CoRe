package io.wisoft.core.accounts.service;

import javax.servlet.http.HttpSession;

public interface EmailAuthenticationService {

  void requestAuthenticateEmail(HttpSession session, String email);
  void requestAuthenticateCode(HttpSession session, String email, String code);

}
