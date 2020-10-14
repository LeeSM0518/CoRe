package io.wisoft.core.accounts.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RequestToChangeProfile {

  @NotBlank(message = "이름을 반드시 입력해주세요. 공백은 불가합니다.")
  @Size(min = 3, max = 10)
  private String name;
  private String website;
  private String introduction;
  @NotNull(message = "이메일 공개 여부를 반드시 입력해주세요.")
  private Boolean isPublicEmail;

}