//package io.wisoft.core.feeds.repository;
//
//import io.wisoft.core.root.entity.Feed;
//import io.wisoft.core.root.entity.Hashtag;
//import io.wisoft.core.root.entity.Member;
//import io.wisoft.core.root.repository.FeedRepository;
//import io.wisoft.core.root.repository.HashtagRepository;
//import io.wisoft.core.root.repository.MemberRepository;
//import org.junit.Assert;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.Collections;
//import java.util.List;
//
//import static java.time.LocalDateTime.now;
//import static java.util.Arrays.asList;
//import static java.util.Collections.singletonList;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//@Transactional
//public class FeedRepositoryTest {
//
//  @Autowired
//  FeedRepository feedRepository;
//  @Autowired
//  FeedPagingRepository feedPagingRepository;
//  @Autowired
//  MemberRepository memberRepository;
//  @Autowired
//  HashtagRepository hashtagRepository;
//
//  @Before
//  public void init() {
//    hashtagRepository.save(Hashtag.create("java"));
//    hashtagRepository.save(Hashtag.create("spring"));
//    hashtagRepository.save(Hashtag.create("kotlin"));
//
//    Hashtag java = hashtagRepository.findByName("java");
//    Hashtag spring = hashtagRepository.findByName("spring");
//    Hashtag kotlin = hashtagRepository.findByName("kotlin");
//    Assert.assertNotNull(java);
//    Assert.assertNotNull(spring);
//    Assert.assertNotNull(kotlin);
//
//    String email = "test@test.com";
//    Member member = Member.create(email, "name", "1234",
//        singletonList(java));
//    memberRepository.save(member);
//
//    Member byEmail = memberRepository.findByEmail(email);
//    Assert.assertNotNull(byEmail);
//
//    Feed feed1 = Feed.create("주제1", "code", "content",
//        now(), singletonList(java), byEmail);
//    Feed feed2 = Feed.create("주제2", "code", "content",
//        now(), asList(java, spring), byEmail);
//    Feed feed3 = Feed.create("주제3", "code", "content",
//        now(), singletonList(kotlin), byEmail);
//
//    feedRepository.save(feed1);
//    feedRepository.save(feed2);
//    feedRepository.save(feed3);
//  }
//
//  @Test
//  public void 피드_조회() throws Exception {
//    // given
//    PageRequest request = Feed.getRequestPageFoundByHashtags(0);
//    // when
////    List<Feed> byHashtags = feedPagingRepository.findByHashtags(Collections.singletonList("java"));
//    Page<Feed> byHashtags = feedRepository.findByHashtags(Collections.singletonList("java"), request);
//    List<Feed> content = byHashtags.getContent();
//
//    // then
//    Assert.assertEquals(2, content.size());
//  }
//
//}