package io.wisoft.core.accounts.service.impl;

import io.wisoft.core.accounts.exception.CodeNotEqualException;
import io.wisoft.core.accounts.exception.EmailDuplicateException;
import io.wisoft.core.accounts.exception.EmailInSessionNotFoundException;
import io.wisoft.core.accounts.service.EmailAuthenticationService;
import io.wisoft.core.root.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.Random;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class EmailAuthenticationServiceImpl implements EmailAuthenticationService {

  private final MemberRepository memberRepository;
  private final JavaMailSender mailSender;
  @Value("${spring.mail.username}")
  private String FROM_ADDRESS;

  @Override
  public void requestAuthenticateEmail(HttpSession session, String email) {
    if (memberRepository.findByEmail(email) != null)
      throw new EmailDuplicateException();
    String code = String.valueOf(getRandomCode());
    sendAuthenticateEmail(email, code);
    session.setMaxInactiveInterval(60);
    session.setAttribute(email, code);
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

  private void sendAuthenticateEmail(String email, String code) {
    SimpleMailMessage message = new SimpleMailMessage();
    message.setTo(email);
    message.setFrom(FROM_ADDRESS);
    message.setSubject("CoRe 회원가입 인증코드 입니다.");
    message.setText("인증코드: " + code);
    mailSender.send(message);
  }

  private int getRandomCode() {
    Random random = new Random(System.currentTimeMillis());
    return random.nextInt(1000000);
  }

}
