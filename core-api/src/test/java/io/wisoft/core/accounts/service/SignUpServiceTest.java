//package io.wisoft.core.accounts.service;
//
//import io.wisoft.core.accounts.dto.TestMemberDto;
//import io.wisoft.core.root.entity.Hashtag;
//import io.wisoft.core.root.entity.Member;
//import io.wisoft.core.root.repository.HashtagRepository;
//import io.wisoft.core.root.repository.MemberRepository;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.Collections;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//@Transactional
//public class SignUpServiceTest {
//
//  @Autowired
//  SignUpService signUpService;
//  @Autowired
//  HashtagRepository hashtagRepository;
//  @Autowired
//  MemberRepository memberRepository;
//  @Autowired
//  PasswordEncoder passwordEncoder;
//
//  public TestMemberDto signUpAndGetMember() {
//    Hashtag hashtag = Hashtag.create("java");
//    hashtagRepository.save(hashtag);
//    String email = "nalsm0518@gmail.com";
//    String name = "min";
//    String password = "1234";
//    Member member = Member.create(email, name, passwordEncoder.encode(password), Collections.singletonList(hashtag));
//    memberRepository.save(member);
//    return new TestMemberDto(email, name, password);
//  }
//
//}
