package io.wisoft.core.feeds.dto;

import io.wisoft.core.root.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberDto {

  private String name;
  private String photo;

  public MemberDto(Member member) {
    this.name = member.getName();
    this.photo = member.getPhoto();
  }

}
