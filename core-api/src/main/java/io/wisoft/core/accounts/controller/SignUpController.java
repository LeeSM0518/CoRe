package io.wisoft.core.accounts.controller;

import io.wisoft.core.accounts.dto.RequestToAuthenticateCode;
import io.wisoft.core.accounts.dto.RequestToAuthenticateEmail;
import io.wisoft.core.accounts.dto.RequestToSignUp;
import io.wisoft.core.accounts.service.EmailAuthenticationService;
import io.wisoft.core.accounts.service.SignUpService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/accounts")
@RequiredArgsConstructor
public class SignUpController {

  private final EmailAuthenticationService emailAuthenticationService;
  private final SignUpService signUpService;

  @PostMapping("/signup/email")
  public void requestCode(@Valid @RequestBody RequestToAuthenticateEmail dto, HttpSession session) {
    emailAuthenticationService.requestAuthenticateEmail(session, dto.getEmail());
  }

  @PostMapping("/signup/code")
  public void authenticateCode(@Valid @RequestBody RequestToAuthenticateCode dto, HttpSession session) {
    emailAuthenticationService.requestAuthenticateCode(session, dto.getEmail(), dto.getCode());
  }

  @PostMapping("/signup")
  public void signUp(@Valid @RequestBody RequestToSignUp dto, HttpSession session) {
    signUpService.signUp(session, dto.getEmail(), dto.getName(), dto.getPassword(), dto.getInterests());
  }

}
