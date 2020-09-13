package io.wisoft.core.tags.service;

import io.wisoft.core.root.entity.Hashtag;
import io.wisoft.core.root.repository.HashtagRepository;
import io.wisoft.core.tags.exception.DuplicateHashtagException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@ActiveProfiles("dev")
public class HashServiceTest {

  @Autowired
  HashtagService hashtagService;
  @Autowired
  HashtagRepository hashtagRepository;

  @Test
  public void 해시태그_생성() throws Exception {
    // given
    hashtagService.createHashtag("java2");

    // when
    Hashtag hashtag = hashtagRepository.findByName("java2");

    // then
    Assert.assertNotNull(hashtag);
    Assert.assertEquals(hashtag.getName(), "java2");
  }

  @Test(expected = DuplicateHashtagException.class)
  public void 해시태그_중복생성_예외() throws Exception {
    // given
    // when
    hashtagService.createHashtag("java");

    // then
    Assert.fail();
  }

}
