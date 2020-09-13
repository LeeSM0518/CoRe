package io.wisoft.core.root.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
@Table(uniqueConstraints = {
    @UniqueConstraint(
        columnNames = {"name", "email"}
    )
})
public class Member {

  @Id
  @GeneratedValue
  @Column(name = "member_id")
  private Long id;

  private String name;

  private String photo;

  private String email;

  private String password;

  private String introduction;

  private Long countOfLikes = 0L;

  @ManyToMany
  @JoinTable(name = "member_interests",
  joinColumns = @JoinColumn(name = "member_id"),
  inverseJoinColumns = @JoinColumn(name = "hashtag_id"))
  private List<Hashtag> interests = new ArrayList<>();

  @ManyToMany
  private List<Member> following = new ArrayList<>();

  @ManyToMany(mappedBy = "following")
  private List<Member> followers = new ArrayList<>();

  @OneToMany(mappedBy = "writer", cascade = CascadeType.ALL)
  private List<Feed> writtenFeeds = new ArrayList<>();

  @OneToMany(mappedBy = "writer", cascade = CascadeType.ALL)
  private List<Comment> writtenComments = new ArrayList<>();

  @ManyToMany(mappedBy = "membersWhoLike")
  private List<Feed> likedFeeds = new ArrayList<>();

  @ManyToMany(mappedBy = "membersWhoLike")
  private List<Comment> likedComments = new ArrayList<>();

  public static Member create(String email, String name, String password, List<Hashtag> interests) {
    Member member = new Member();
    member.setName(name);
    member.setEmail(email);
    member.setPassword(password);
    member.setInterests(interests);
    return member;
  }

}
