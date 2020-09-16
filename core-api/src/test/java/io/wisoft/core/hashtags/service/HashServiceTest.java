package io.wisoft.core.hashtags.service;

import io.wisoft.core.hashtags.dto.ResponseToFindHashtag;
import io.wisoft.core.hashtags.exception.HashtagDuplicateException;
import io.wisoft.core.root.entity.Hashtag;
import io.wisoft.core.root.repository.HashtagRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class HashServiceTest {

  @Autowired
  HashtagService hashtagService;
  @Autowired
  HashtagRepository hashtagRepository;

  @Before
  public void 해시태그_생성() throws Exception {
    // given
    hashtagService.createHashtag("java1");
    hashtagService.createHashtag("java2");
    hashtagService.createHashtag("java3");
    hashtagService.createHashtag("java4");
    hashtagService.createHashtag("java5");

    // when
    Hashtag hashtag = hashtagRepository.findByName("java1");

    // then
    Assert.assertNotNull(hashtag);
    Assert.assertEquals("java1", hashtag.getName());
  }

  @Test(expected = HashtagDuplicateException.class)
  public void 해시태그_중복생성_예외() throws Exception {
    // given
    // when
    hashtagService.createHashtag("java1");

    // then
    Assert.fail();
  }

  @Test
  public void 해시태그_페이지_검색() throws Exception {
    // given
    String name = "java";
    String name2 = "javaa";

    // when
    List<ResponseToFindHashtag> hashtagListByName = hashtagService.findHashtagListByName(name);
    List<ResponseToFindHashtag> hashtagListByName2 = hashtagService.findHashtagListByName(name2);

    // then
    Assert.assertNotNull(hashtagListByName);
    Assert.assertEquals(5, hashtagListByName.size());
    Assert.assertEquals("java1", hashtagListByName.get(0).getName());

    Assert.assertNotNull(hashtagListByName2);
    Assert.assertEquals(0, hashtagListByName2.size());
  }


}
