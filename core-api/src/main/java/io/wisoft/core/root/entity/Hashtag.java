package io.wisoft.core.root.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
@Setter
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Hashtag implements Serializable {

  @Id
  @GeneratedValue
  @Column(name = "hashtag_id")
  private Long id;

  @Column(unique = true)
  private String name;

  private Long countOfTagged = 0L;

  public static Hashtag create(String name) {
    Hashtag hashtag = new Hashtag();
    hashtag.setName(name);
    return hashtag;
  }

  public void tagged() {
    this.countOfTagged++;
  }

}
