package io.wisoft.core.hashtags.repository;

import io.wisoft.core.root.entity.Hashtag;
import io.wisoft.core.root.repository.HashtagRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class HashtagRepositoryTest {

  @Autowired
  HashtagRepository hashtagRepository;

  @Test
  public void 해시태그_조회() throws Exception {
    // given
    saveHashtag("j");
    saveHashtag("ja");
    saveHashtag("je");
    saveHashtag("jrr");
    saveHashtag("jqq");
    PageRequest request = PageRequest.of(0, 10, Sort.Direction.ASC, "name");

    // when
    Page<Hashtag> content = hashtagRepository.findByNameStartsWith("j", request);

    // then
    Assert.assertEquals(content.getContent().size(), 5);
  }

  private void saveHashtag(String name) {
    Hashtag tag1 = Hashtag.create(name);
    hashtagRepository.save(tag1);
  }

}
