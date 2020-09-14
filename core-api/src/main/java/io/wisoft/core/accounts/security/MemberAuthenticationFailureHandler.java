package io.wisoft.core.accounts.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MemberAuthenticationFailureHandler implements AuthenticationFailureHandler {

  private final ObjectMapper mapper = new ObjectMapper();

  @Override
  public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                      AuthenticationException exception) throws IOException, ServletException {
    String errorMessage = "로그인에 실패했습니다.";

    response.setStatus(HttpStatus.UNAUTHORIZED.value());
    response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);

    if (exception instanceof BadCredentialsException) {
      errorMessage = "비밀번호가 일치하지 않습니다.";
    } else if (exception instanceof UsernameNotFoundException) {
      errorMessage = "해당 이메일은 존재하지 않습니다.";
    }

    mapper.writeValue(response.getWriter(), errorMessage);
  }

}
