package io.wisoft.core.feeds.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.util.List;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RequestToCreateFeed {

  @NotEmpty(message = "요약을 반드시 입력해야 합니다.")
  private String summary;
  @NotEmpty(message = "메인 코드를 반드시 입력해야 합니다.")
  private String mainCode;
  @NotEmpty(message = "내용을 반드시 입력해야 합니다.")
  private String content;
  @NotEmpty(message = "관련 주제를 최소 한 개를 선택해야 합니다.")
  private List<String> relatedTags;

}
