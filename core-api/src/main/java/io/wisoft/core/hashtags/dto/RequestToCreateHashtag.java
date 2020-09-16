package io.wisoft.core.hashtags.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RequestToCreateHashtag {

  @NotBlank(message = "태그 이름을 반드시 입력해주세요.")
  private String name;

}
