package io.wisoft.core.feeds.dto;

import io.wisoft.core.root.entity.Feed;
import io.wisoft.core.root.entity.Hashtag;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseToGetFeed {

  private Long id;

  private String summary;

  private String mainCode;

  private String content;

  private LocalDateTime createdDateTime;

  private List<String> relatedTags;

  private MemberDto writer;

  private int countOfComments;

  private List<MemberDto> membersWhoLike;

  public static List<ResponseToGetFeed> getFeeds(List<Feed> feeds) {
    return feeds.stream()
        .map(feed ->
            ResponseToGetFeed.builder()
                .id(feed.getId())
                .summary(feed.getSummary())
                .mainCode(feed.getMainCode())
                .content(feed.getContent())
                .createdDateTime(feed.getCreatedDateTime())
                .relatedTags(feed
                    .getRelatedTags()
                    .stream()
                    .map(Hashtag::getName)
                    .collect(Collectors.toList()))
                .writer(MemberDto.builder()
                    .name(feed.getWriter().getName())
                    .photo(feed.getWriter().getPhoto())
                    .build())
                .countOfComments(feed.getComments().size())
                .membersWhoLike(feed
                    .getMembersWhoLike()
                    .stream()
                    .map(MemberDto::new)
                    .collect(Collectors.toList()))
                .build())
        .collect(Collectors.toList());
  }

}
