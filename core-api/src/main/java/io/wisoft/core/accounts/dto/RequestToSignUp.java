package io.wisoft.core.accounts.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RequestToSignUp {

  @Email(message = "이메일 형식에 맞춰서 요청해야 합니다.")
  private String email;
  @NotBlank(message = "이름을 반드시 입력해주세요. 공백은 입력 불가합니다.")
  private String name;
  @NotBlank(message = "비밀번호를 반드시 입력해주세요. 공백은 입력 불가합니다.")
  private String password;
  @NotEmpty(message = "관심사를 최소 한 개를 선택해야 합니다.")
  private List<String> interests;

}
