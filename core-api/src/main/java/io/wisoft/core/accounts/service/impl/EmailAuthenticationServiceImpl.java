package io.wisoft.core.accounts.service.impl;

import io.wisoft.core.accounts.exception.CodeNotEqualException;
import io.wisoft.core.accounts.exception.EmailDuplicateException;
import io.wisoft.core.accounts.exception.EmailInSessionNotFoundException;
import io.wisoft.core.accounts.exception.EmailNotFoundException;
import io.wisoft.core.accounts.service.EmailAuthenticationService;
import io.wisoft.core.accounts.service.MailService;
import io.wisoft.core.root.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class EmailAuthenticationServiceImpl implements EmailAuthenticationService {

  private final MemberRepository memberRepository;
  private final MailService mailService;

  @Override
  public void requestAuthenticateEmailToSignUp(HttpSession session, String email) {
    if (memberRepository.findByEmail(email) != null)
      throw new EmailDuplicateException();
    mailService.sendCode(session, email);
  }

  @Override
  public void requestAuthenticateEmailToPasswordReset(HttpSession session, String email) {
    if (memberRepository.findByEmail(email) == null)
      throw new EmailNotFoundException();
    mailService.sendCode(session, email);
  }

  @Override
  public void requestAuthenticateCode(HttpSession session, String email, String code) {
    String codeInSession = (String) session.getAttribute(email);
    if (codeInSession == null)
      throw new EmailInSessionNotFoundException();
    else if (!code.equals(codeInSession))
      throw new CodeNotEqualException();
    else {
      session.setAttribute(email, EMAIL_CERTIFIED);
      session.setMaxInactiveInterval(60 * 5);
    }
  }

}
