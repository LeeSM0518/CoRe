package io.wisoft.core.accounts.service.impl;

import io.wisoft.core.accounts.exception.DuplicateEmailException;
import io.wisoft.core.accounts.exception.NotEqualCodeException;
import io.wisoft.core.accounts.exception.NotFoundEmailInSessionException;
import io.wisoft.core.accounts.service.EmailAuthenticationService;
import io.wisoft.core.root.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Random;

@Service
@Profile("dev")
@RequiredArgsConstructor
public class EmailAuthenticationServiceImpl implements EmailAuthenticationService {

  private final MemberRepository memberRepository;
  private final JavaMailSender mailSender;
  @Value("${spring.mail.username}")
  private String FROM_ADDRESS;

  @Override
  public void requestAuthenticateEmail(HttpSession session, String email) {
    if (memberRepository.findByEmail(email) != null)
      throw new DuplicateEmailException();
    int code = getRandomCode();
    sendAuthenticateEmail(email, code);
    session.setMaxInactiveInterval(60);
    session.setAttribute(email, code);
  }

  @Override
  public void requestAuthenticateCode(HttpSession session, String email, String code) {
    Integer codeInSession = (Integer) session.getAttribute(email);
    if (codeInSession == null)
      throw new NotFoundEmailInSessionException();
    else if (!Integer.valueOf(code).equals(codeInSession))
      throw new NotEqualCodeException();
  }

  private void sendAuthenticateEmail(String email, int code) {
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
