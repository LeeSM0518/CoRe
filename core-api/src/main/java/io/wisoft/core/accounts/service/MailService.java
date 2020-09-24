package io.wisoft.core.accounts.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

import static io.wisoft.core.utils.RandomValueUtil.getRandom;

@Service
@RequiredArgsConstructor
public class MailService {

  private final JavaMailSender mailSender;
  @Value("${spring.mail.username}")
  private String FROM_ADDRESS;

  public void sendCode(HttpSession session, String email) {
    String code = getRandom();
    sendAuthenticateEmail(email, code, "CoRe 인증코드 입니다.", "인증코드: ");
    session.setMaxInactiveInterval(60);
    session.setAttribute(email, code);
  }

  public void sendNewPassword(String email, String code) {
    sendAuthenticateEmail(email, code, "CoRe 새로운 비밀번호 입니다.", "새 비밀번호: ");
  }

  private void sendAuthenticateEmail(String email, String code, String subject, String text) {
    SimpleMailMessage message = new SimpleMailMessage();
    message.setTo(email);
    message.setFrom(FROM_ADDRESS);
    message.setSubject(subject);
    message.setText(text + code);
    mailSender.send(message);
  }

}
