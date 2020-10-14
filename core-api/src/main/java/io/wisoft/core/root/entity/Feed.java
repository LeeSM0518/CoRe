package io.wisoft.core.root.entity;

import lombok.*;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@Entity
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Feed {

  @Id
  @GeneratedValue
  @Column(name = "feed_id")
  private Long id;

  private String title;

  private String mainCode;

  private String content;

  private LocalDateTime createdDateTime;

  @ManyToMany
  @JoinTable(name = "related_tags",
      joinColumns = @JoinColumn(name = "feed_id"),
      inverseJoinColumns = @JoinColumn(name = "hashtag_id"))
  private List<Hashtag> relatedTags = new ArrayList<>();

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "member_id")
  private Member writer;

  @OneToMany(mappedBy = "writtenFeed", cascade = CascadeType.ALL)
  private List<Comment> comments;

  @ManyToMany
  @JoinTable(name = "members_who_like_feeds",
      joinColumns = @JoinColumn(name = "feed_id"),
      inverseJoinColumns = @JoinColumn(name = "member_id"))
  private List<Member> membersWhoLike = new ArrayList<>();

  public static Feed create(String title, String mainCode, String content, LocalDateTime createdDateTime,
                            List<Hashtag> relatedTags, Member writer) {
    Feed feed = new Feed();
    feed.setTitle(title);
    feed.setMainCode(mainCode);
    feed.setContent(content);
    feed.setCreatedDateTime(createdDateTime);
    feed.setRelatedTags(relatedTags);
    feed.setWriter(writer);
    return feed;
  }

  public void like(Member member) {

  }

  public static PageRequest getRequestPageFoundByHashtags(int page) {
    return PageRequest.of(page, 30, Sort.Direction.ASC, "createdDateTime");
  }

}
