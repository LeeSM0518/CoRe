package io.wisoft.core.accounts.controller;

import io.wisoft.core.accounts.dto.RequestToAuthenticateCode;
import io.wisoft.core.accounts.dto.RequestToAuthenticateEmail;
import io.wisoft.core.accounts.service.EmailAuthenticationService;
import io.wisoft.core.accounts.service.PasswordResetService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/accounts/password")
@RequiredArgsConstructor
public class PasswordResetController {

  private final EmailAuthenticationService emailAuthenticationService;
  private final PasswordResetService passwordResetService;

  @PostMapping("/email")
  public void requestCode(@Valid @RequestBody RequestToAuthenticateEmail dto, HttpSession session) {
    emailAuthenticationService.requestAuthenticateEmailToPasswordReset(session, dto.getEmail());
  }

  @PostMapping("/reset")
  public void requestReset(@Valid @RequestBody RequestToAuthenticateCode dto, HttpSession session) {
    emailAuthenticationService.requestAuthenticateCode(session, dto.getEmail(), dto.getCode());
    passwordResetService.resetPassword(dto.getEmail());
  }

}
