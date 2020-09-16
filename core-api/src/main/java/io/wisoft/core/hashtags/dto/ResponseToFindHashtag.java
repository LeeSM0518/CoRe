package io.wisoft.core.hashtags.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseToFindHashtag {

  private String name;
  private Long countOfTagged;

}
