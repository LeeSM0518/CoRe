//package io.wisoft.core.accounts.service;
//
//import io.wisoft.core.root.domain.Hashtag;
//import io.wisoft.core.root.domain.Member;
//import io.wisoft.core.root.repository.HashtagRepository;
//import io.wisoft.core.root.repository.MemberRepository;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.ActiveProfiles;
//import org.springframework.test.context.junit4.SpringRunner;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//@ActiveProfiles("dev")
//public class EmailAuthenticationServiceImplTest {
//
//  @Autowired
//  EmailAuthenticationService emailAuthenticationService;
//  @Autowired
//  MemberRepository memberRepository;
//  @Autowired
//  HashtagRepository hashtagRepository;
//
//  @Test
//  public void 회원가입_코드_요청() throws Exception {
//    List<Hashtag> hashtags = Arrays.asList(Hashtag.create("java"), Hashtag.create("c"));
//    hashtagRepository.saveAll(hashtags);
//    String email = "asd@asd.zxc";
//    Member member = Member.create(email, "asd", "1234", hashtags);
//    memberRepository.save(member);
//    emailAuthenticationService.requestAuthenticateEmail("nalsm0518@gmail.com");
//     when
//
//     then
//  }
//
//}
