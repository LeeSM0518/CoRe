package io.wisoft.core.accounts.service.impl;

import io.wisoft.core.accounts.exception.EmailNotFoundException;
import io.wisoft.core.accounts.service.MailService;
import io.wisoft.core.accounts.service.PasswordResetService;
import io.wisoft.core.root.entity.Member;
import io.wisoft.core.root.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static io.wisoft.core.utils.RandomValueUtil.getRandom;

@Service
@Transactional
@RequiredArgsConstructor
public class PasswordRestServiceImpl implements PasswordResetService {

  private final MemberRepository memberRepository;
  private final MailService mailService;
  private final PasswordEncoder passwordEncoder;

  @Override
  public void resetPassword(String email) {
    Member member = memberRepository.findByEmail(email);
    if (member == null)
      throw new EmailNotFoundException();
    String newPassword = getRandom();
    member.changePassword(passwordEncoder.encode(newPassword));
    mailService.sendNewPassword(email, newPassword);
  }

}
