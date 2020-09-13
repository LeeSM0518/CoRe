package io.wisoft.core.root.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Comment {

  @Id
  @GeneratedValue
  @Column(name = "comment_id")
  private Long id;

  private int commentedCodeNumber;

  private String content;

  private LocalDateTime createdDateTime;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "feed_id")
  private Feed writtenFeed;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "member_id")
  private Member writer;

  @ManyToMany
  @JoinTable(name = "members_who_like_comments",
      joinColumns = @JoinColumn(name = "comment_id"),
      inverseJoinColumns = @JoinColumn(name = "member_id"))
  private List<Member> membersWhoLike;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "replied_id")
  private Comment replied;

  @OneToMany(mappedBy = "replied", cascade = CascadeType.ALL)
  private List<Comment> replies = new ArrayList<>();

}
