package io.wisoft.core.root.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Feed {

  @Id
  @GeneratedValue
  @Column(name = "feed_id")
  private Long id;

  private String summary;

  private String mainCode;

  private String content;

  private LocalDateTime createdDateTime;

  @ManyToMany
  @JoinTable(name = "related_tags",
      joinColumns = @JoinColumn(name = "feed_id"),
      inverseJoinColumns = @JoinColumn(name = "hashtag_id"))
  private List<Hashtag> relatedTags = new ArrayList<>();

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id")
  private Member writer;

  @OneToMany(mappedBy = "writtenFeed", cascade = CascadeType.ALL)
  private List<Comment> comments;

  @ManyToMany
  @JoinTable(name = "members_who_like_feeds",
      joinColumns = @JoinColumn(name = "feed_id"),
      inverseJoinColumns = @JoinColumn(name = "member_id"))
  private List<Member> membersWhoLike = new ArrayList<>();


}
