package io.wisoft.core.accounts.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.wisoft.core.accounts.dto.LoginDto;
import io.wisoft.core.accounts.dto.ResponseToLogin;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MemberAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

  private final ObjectMapper mapper = new ObjectMapper();

  @Override
  public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                      Authentication authentication) throws IOException, ServletException {
    LoginDto loginDto = (LoginDto) authentication.getPrincipal();
    String username = loginDto.getEmail().split("@")[0];
    ResponseToLogin responseToLogin = new ResponseToLogin(loginDto.getName(), username);

    response.setStatus(HttpStatus.OK.value());
    response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);

    mapper.writeValue(response.getWriter(), responseToLogin);
  }

}
