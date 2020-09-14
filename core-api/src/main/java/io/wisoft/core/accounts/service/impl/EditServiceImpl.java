package io.wisoft.core.accounts.service.impl;

import io.wisoft.core.accounts.dto.LoginDto;
import io.wisoft.core.accounts.dto.RequestToChangePassword;
import io.wisoft.core.accounts.dto.RequestToChangeProfile;
import io.wisoft.core.accounts.exception.BeforePasswordNotEqualException;
import io.wisoft.core.accounts.exception.NewPasswordNotEqualException;
import io.wisoft.core.accounts.exception.UncertifiedMemberException;
import io.wisoft.core.accounts.security.MemberAuthenticationToken;
import io.wisoft.core.accounts.service.EditService;
import io.wisoft.core.root.entity.Member;
import io.wisoft.core.root.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
@Transactional
@RequiredArgsConstructor
public class EditServiceImpl implements EditService {

  private final PasswordEncoder passwordEncoder;
  private final MemberRepository memberRepository;

  public void changePassword(HttpServletRequest request, HttpServletResponse response, RequestToChangePassword dto) {
    Authentication authentication =
        SecurityContextHolder.getContext().getAuthentication();

    if (authentication == null)
      throw new UncertifiedMemberException();

    LoginDto loginDto = (LoginDto) authentication.getPrincipal();
    String password = loginDto.getPassword();

    if (!passwordEncoder.matches(dto.getBeforePassword(), password))
      throw new BeforePasswordNotEqualException();

    if (!dto.getNewPassword().equals(dto.getCheckNewPassword()))
      throw new NewPasswordNotEqualException();

    String email = loginDto.getEmail();
    Member member = memberRepository.findByEmail(email);
    member.changePassword(passwordEncoder.encode(dto.getCheckNewPassword()));
    memberRepository.save(member);

    new SecurityContextLogoutHandler().logout(request, response, authentication);
  }

  public void changeProfile(RequestToChangeProfile dto) {
    Authentication authentication =
        SecurityContextHolder.getContext().getAuthentication();

    if (authentication == null)
      throw new UncertifiedMemberException();

    LoginDto loginDto = (LoginDto) authentication.getPrincipal();

    // TODO 회원정보 수정
    // TODO loginDto 수정

    MemberAuthenticationToken token =
        new MemberAuthenticationToken(loginDto, null, authentication.getAuthorities());
    SecurityContextHolder.getContext().setAuthentication(token);
  }

}
