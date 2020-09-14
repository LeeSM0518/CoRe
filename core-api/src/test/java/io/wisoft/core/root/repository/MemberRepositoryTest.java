package io.wisoft.core.root.repository;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("dev")
@Transactional
public class MemberRepositoryTest {

//  @Autowired
//  MemberRepository memberRepository;
//  @Autowired
//  HashtagRepository hashtagRepository;
//
//  @Test
//  @Rollback(false)
//  public void 회원가입() throws Exception {
//    // given
//    List<Hashtag> hashtags = Arrays.asList(Hashtag.create("java"), Hashtag.create("c"));
//    hashtagRepository.saveAll(hashtags);
//    String email = "asd@asd.zxc";
//    Member member = Member.create(email, "asd", "1234", hashtags);
//    memberRepository.save(member);
//
//    // when
//    Member getMember = memberRepository.findByEmail(email);
//
//    // then
//    Assert.assertNotNull(getMember);
//    Assert.assertEquals(member.getName(), getMember.getName());
//  }

}
