package io.wisoft.core.accounts.service;

import javax.servlet.http.HttpSession;

public interface EmailAuthenticationService {

  String EMAIL_CERTIFIED = "CERTIFIED";

  void requestAuthenticateEmailToSignUp(HttpSession session, String email);
  void requestAuthenticateEmailToPasswordReset(HttpSession session, String email);
  void requestAuthenticateCode(HttpSession session, String email, String code);

}
