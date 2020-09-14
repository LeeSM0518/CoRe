package io.wisoft.core.accounts.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RequestToChangePassword {

  @NotBlank(message = "이전 비밀번호를 반드시 입력해주세요. 공백은 불가능합니다.")
  private String beforePassword;
  @NotBlank(message = "새 비밀번호를 반드시 입력해주세요. 공백은 불가능합니다.")
  private String newPassword;
  @NotBlank(message = "새 비밀번호 확인 반드시 입력해주세요. 공백은 불가능합니다.")
  private String checkNewPassword;

}
