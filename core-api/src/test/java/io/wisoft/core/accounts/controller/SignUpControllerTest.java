//package io.wisoft.core.accounts.controller;
//
//import io.wisoft.core.accounts.dto.RequestToAuthenticateEmail;
//import io.wisoft.core.accounts.dto.RequestToSignUp;
//import io.wisoft.core.root.entity.Hashtag;
//import io.wisoft.core.root.entity.Member;
//import io.wisoft.core.root.repository.HashtagRepository;
//import io.wisoft.core.root.repository.MemberRepository;
//import org.junit.Assert;
//import org.junit.Test;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.ActiveProfiles;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.ResultMatcher;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.Collections;
//
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//@AutoConfigureMockMvc
//@ActiveProfiles("dev")
//@Transactional
//public class SignUpControllerTest {
//
//  @Autowired
//  MockMvc mockMvc;
//  @Autowired
//  ObjectMapper objectMapper;
//  @Autowired
//  MemberRepository memberRepository;
//  @Autowired
//  HashtagRepository hashtagRepository;
//
//  @Test
//  public void 이메일_인증_예외() throws Exception {
//    // given
//    Hashtag hashtag = Hashtag.create("python");
//    hashtagRepository.save(hashtag);
//    String email = "qwe@qwe.qwe";
//    String name = "zxczxc";
//    memberRepository.save(
//        Member.create(email, name, "1234", Collections.singletonList(hashtag)));
//
//    // when
//    RequestToAuthenticateEmail dto = new RequestToAuthenticateEmail(email);
//    mockMvc.perform(
//        post("/api/accounts/signup/email")
//        .content(objectMapper.writeValueAsString(dto))
//        .contentType(MediaType.APPLICATION_JSON_VALUE)
//        .accept(MediaType.APPLICATION_JSON_UTF8))
//        .andDo(print())
//        .andExpect(status().isConflict());
//  }
//
//  @Test
//  public void 회원가입() throws Exception {
//    // given
//    RequestToSignUp dto1 = getRequestToSignUp("qwe@qwe.", "qwe", "qwe", "java");
//    RequestToSignUp dto2 = getRequestToSignUp("qwe@qwe.qwe", "", "qwe", "java");
//    RequestToSignUp dto3 = getRequestToSignUp("qwe@qwe.qwe", "qwe", "", "java");
//    RequestToSignUp dto4 = getRequestToSignUp("qwe@qwe.qwe", "qwe", "qwe", "");
//    RequestToSignUp dto5 = getRequestToSignUp("qwe@qwe.qwe", "qwe", "qwe", "java");
//
//    // when
//    requestApi(dto1, status().isBadRequest());
//    requestApi(dto2, status().isBadRequest());
//    requestApi(dto3, status().isBadRequest());
//    requestApi(dto4, status().isBadRequest());
//    requestApi(dto5, status().isOk());
//
//    // then
//    Member member = memberRepository.findByEmail(dto5.getEmail());
//    System.out.println(member);
//    Assert.assertNotNull(member);
//    Assert.assertEquals(dto5.getName(), member.getName());
//  }
//
//  private RequestToSignUp getRequestToSignUp(String s, String qwe, String qwe2, String java) {
//    return RequestToSignUp.builder()
//        .email(s)
//        .name(qwe)
//        .password(qwe2)
//        .interests(Collections.singletonList(java))
//        .build();
//  }
//
//  private void requestApi(RequestToSignUp dto, ResultMatcher matcher) throws Exception {
//    mockMvc.perform(
//        post("/api/accounts/signup")
//            .content(objectMapper.writeValueAsString(dto))
//            .contentType(MediaType.APPLICATION_JSON_VALUE)
//            .accept(MediaType.APPLICATION_PROBLEM_JSON_UTF8))
//        .andDo(print())
//        .andExpect(matcher);
//  }
//
//}
