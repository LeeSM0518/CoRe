package io.wisoft.core.accounts.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/accounts")
public class LogoutController {

  @GetMapping("/logout")
  public void logout(HttpServletRequest request, HttpServletResponse response) {
    Authentication authentication =
        SecurityContextHolder.getContext().getAuthentication();

    if (authentication != null) {
      new SecurityContextLogoutHandler().logout(request, response, authentication);
    }
  }

}
