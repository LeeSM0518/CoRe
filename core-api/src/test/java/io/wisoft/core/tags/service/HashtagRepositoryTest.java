package io.wisoft.core.tags.service;

import io.wisoft.core.root.entity.Hashtag;
import io.wisoft.core.root.repository.HashtagRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("dev")
@Transactional
public class HashtagRepositoryTest {

  @Autowired
  HashtagRepository repository;

  @Test
  public void 존재하는_관심사_조회() throws Exception {
    // given
    repository.save(Hashtag.create("c"));
    repository.save(Hashtag.create("python"));
    List<String> interests = Arrays.asList("c", "python");
    // when
    List<Hashtag> hashtagList = repository.findByNames(interests);

    // then
    System.out.println("count: " + hashtagList.size());
    Assert.assertEquals(hashtagList.size(), interests.size());
  }

  @Test
  public void 해시태그_이름으로_검색() throws Exception {
    // given
    // when
    Hashtag hashtag = repository.findByName("java");

    // then
    Assert.assertNotNull(hashtag);
    Assert.assertEquals(hashtag.getName(), "java");
  }

}
