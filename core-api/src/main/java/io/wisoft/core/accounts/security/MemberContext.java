package io.wisoft.core.accounts.security;

import io.wisoft.core.accounts.dto.LoginDto;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.List;

@Getter
@Setter
public class MemberContext extends User {

  private LoginDto loginDto;

  public MemberContext(LoginDto loginDto, List<GrantedAuthority> roles) {
    super(loginDto.getEmail(), loginDto.getPassword(), roles);
    this.loginDto = loginDto;
  }

}
