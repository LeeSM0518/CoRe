package io.wisoft.core.accounts.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
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
  @Range(min = 1, message = "관심사를 최소한 한 개 선택해야 합니다.")
  private List<String> interests;

}
