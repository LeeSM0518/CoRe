package io.wisoft.core.accounts.service;

import io.wisoft.core.accounts.exception.DuplicateEmailException;
import io.wisoft.core.accounts.exception.DuplicateNameException;
import io.wisoft.core.accounts.exception.NotFoundInterestException;
import io.wisoft.core.root.entity.Hashtag;
import io.wisoft.core.root.entity.Member;
import io.wisoft.core.root.repository.HashtagRepository;
import io.wisoft.core.root.repository.MemberRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.jupiter.api.Order;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@ActiveProfiles("dev")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SignUpServiceTest {

  @Autowired
  SignUpService signUpService;
  @Autowired
  HashtagRepository hashtagRepository;
  @Autowired
  MemberRepository memberRepository;

  @Test
  @Before
  public void 초기설정() throws Exception {
    // given
    String email = "asd@asd.com";
    String name = "asd";
    String tag = "java";

    // when
    if (hashtagRepository.findByName(tag) == null)
      hashtagRepository.save(Hashtag.create(tag));
    if (memberRepository.findByEmail(email) == null)
      signUpService.signUp(email, name, "1234", Collections.singletonList("java"));

    // then
  }

  @Test
  public void 회원가입() throws Exception {
    // given
    Long countOfTagged1 = hashtagRepository.findByName("java").getCountOfTagged();
    String email = "asd5@asd.com";
    String name = "asd5";

    // when
    signUpService.signUp(email, name, "1234", Collections.singletonList("java"));

    // then
    Member member = memberRepository.findByEmail(email);
    Long countOfTagged2 = hashtagRepository.findByName("java").getCountOfTagged();
    Assert.assertNotNull(member);
    Assert.assertNotNull(countOfTagged1);
    Assert.assertNotNull(countOfTagged2);
    Assert.assertEquals(name, member.getName());
    Assert.assertEquals(java.util.Optional.of(countOfTagged1 + 1L).get(), countOfTagged2);
  }

  @Test(expected = DuplicateEmailException.class)
  @Order(2)
  public void 이메일_중복_예외() throws Exception {
    // given
    String email = "asd@asd.com";
    String name = "asd";

    // when
    signUpService.signUp(email, name, "1234", Collections.singletonList("java"));

    // then
    Assert.fail("이메일 중복 처리 에러");
  }

  @Test(expected = DuplicateNameException.class)
  @Order(1)
  public void 이름_중복_예외() throws Exception {
    // given
    String email = "asd3@asd.com";
    String name = "asd";

    // when
    signUpService.signUp(email, name, "1234", Collections.singletonList("java"));

    // then
    Assert.fail("이름 중복 처리 에러");
  }

  @Test(expected = NotFoundInterestException.class)
  public void 해시태그_없음_예외() throws Exception {
    // given
    String email = "asd2@asd.com";
    String name = "asd2";

    // when
    signUpService.signUp(email, name, "1234", Collections.singletonList("java2"));

    // then
    Assert.fail("해시태그 없음 처리 에러");
  }

}
