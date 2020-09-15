package io.wisoft.core.accounts.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
public class AccountsConfig {

  @Bean
  public JavaMailSender javaMailSender() {
    return new JavaMailSenderImpl();
  }

}
