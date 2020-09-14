package io.wisoft.core.accounts.security;

import io.wisoft.core.accounts.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

public class MemberAuthenticationProvider implements AuthenticationProvider {

  @Autowired
  private LoginService loginService;
  @Autowired
  private PasswordEncoder passwordEncoder;

  @Override
  @Transactional
  public Authentication authenticate(Authentication authentication) throws AuthenticationException {
    String email = authentication.getName();
    String password = (String) authentication.getCredentials();

    MemberContext memberContext = (MemberContext) loginService.findMemberByEmail(email);

    if (!passwordEncoder.matches(password, memberContext.getPassword())) {
      throw new BadCredentialsException("잘못된 비밀번호 입니다.");
    }

    return new MemberAuthenticationToken(memberContext.getLoginDto(), null, memberContext.getAuthorities());
  }

  @Override
  public boolean supports(Class<?> authentication) {
    return authentication.equals(MemberAuthenticationToken.class);
  }

}
