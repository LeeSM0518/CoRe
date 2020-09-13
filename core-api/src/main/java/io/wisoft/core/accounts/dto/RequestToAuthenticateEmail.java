package io.wisoft.core.accounts.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RequestToAuthenticateEmail {

  @Email(message = "이메일을 형식에 맞춰서 올바르게 작성해주세요.")
  private String email;

}
