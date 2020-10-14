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

  @NotBlank(message = "{hashtag.name}")
  private String name;

}
