package io.wisoft.core.accounts.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.wisoft.core.accounts.dto.RequestToLogin;
import io.wisoft.core.accounts.exception.LoginEmailInvalidException;
import io.wisoft.core.accounts.exception.LoginMethodNotEqualException;
import io.wisoft.core.accounts.exception.LoginPasswordInvalidException;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MemberLoginProcessFilter extends AbstractAuthenticationProcessingFilter {

  private final ObjectMapper objectMapper = new ObjectMapper();

  public MemberLoginProcessFilter() {
    super(new AntPathRequestMatcher("/api/accounts/login"));
  }

  @Override
  public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
      throws AuthenticationException, IOException, ServletException {

    response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);

    if (!HttpMethod.POST.name().equals(request.getMethod())) {
      response.setStatus(HttpStatus.BAD_REQUEST.value());
      objectMapper.writeValue(response.getWriter(), "로그인은 반드시 POST로 요청해야 합니다.");
      throw new LoginMethodNotEqualException("error");
    }

    RequestToLogin requestToLogin = objectMapper.readValue(request.getReader(), RequestToLogin.class);

    if (StringUtils.isEmpty(requestToLogin.getEmail())) {
      response.setStatus(HttpStatus.BAD_REQUEST.value());
      objectMapper.writeValue(response.getWriter(), "이메일이 비어있습니다.");
      throw new LoginEmailInvalidException("error");
    }

    if (StringUtils.isEmpty(requestToLogin.getPassword())) {
      response.setStatus(HttpStatus.BAD_REQUEST.value());
      objectMapper.writeValue(response.getWriter(), "비밀번호가 비어있습니다.");
      throw new LoginPasswordInvalidException("error");
    }

    MemberAuthenticationToken memberAuthenticationToken =
        new MemberAuthenticationToken(requestToLogin.getEmail(), requestToLogin.getPassword());

    return getAuthenticationManager().authenticate(memberAuthenticationToken);
  }

}
