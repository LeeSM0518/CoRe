package io.wisoft.core.accounts.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RequestToAuthenticateCode {

  @Email(message = "이메일을 형식에 맞춰서 올바르게 작성해주세요.")
  private String email;
  @NotEmpty(message = "인증 코드를 다시 입력해주세요.")
  private String code;

}
