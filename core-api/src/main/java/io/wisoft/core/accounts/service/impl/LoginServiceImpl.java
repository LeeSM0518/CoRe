package io.wisoft.core.accounts.service.impl;

import io.wisoft.core.accounts.dto.LoginDto;
import io.wisoft.core.accounts.security.MemberContext;
import io.wisoft.core.accounts.service.LoginService;
import io.wisoft.core.root.entity.Member;
import io.wisoft.core.root.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {

  private final MemberRepository memberRepository;
  private ModelMapper modelMapper = new ModelMapper();

  @Override
  public UserDetails findMemberByEmail(String email) throws UsernameNotFoundException {
    Member member = memberRepository.findByEmail(email);
    if (member == null) {
      throw new UsernameNotFoundException("Invalid Password.");
    }

    LoginDto loginDto = modelMapper.map(member, LoginDto.class);

    return new MemberContext(loginDto, Collections.singletonList(new SimpleGrantedAuthority("USER")));
  }

}
