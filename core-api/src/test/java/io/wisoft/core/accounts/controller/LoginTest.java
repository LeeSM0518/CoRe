package io.wisoft.core.accounts.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.wisoft.core.TestConfig;
import io.wisoft.core.accounts.dto.RequestToLogin;
import io.wisoft.core.accounts.dto.TestMemberDto;
import io.wisoft.core.accounts.service.SignUpServiceTest;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@Import(TestConfig.class)
public class LoginTest {

  @Autowired
  MockMvc mockMvc;
  @Autowired
  ObjectMapper objectMapper;
  @Autowired
  SignUpServiceTest signUpServiceTest;

  @Test
  public void 로그인() throws Exception {
    // given
    TestMemberDto member = signUpServiceTest.signUpAndGetMember();
    Assert.assertNotNull(member);
    RequestToLogin requestToLogin = new RequestToLogin(member.getEmail(), member.getPassword());

    // when
    When(requestToLogin, post("/api/accounts/login"), status().isOk());
  }

  @Test
  public void 로그인_이메일_없음_예외() throws Exception {
    // given
    TestMemberDto member = signUpServiceTest.signUpAndGetMember();
    Assert.assertNotNull(member);
    RequestToLogin requestToLogin = new RequestToLogin("asd", member.getPassword());

    // when
    When(requestToLogin, post("/api/accounts/login"), status().isUnauthorized());
  }

  @Test
  public void 로그인_비밀번호_불일치_예외() throws Exception {
    // given
    TestMemberDto member = signUpServiceTest.signUpAndGetMember();
    Assert.assertNotNull(member);
    RequestToLogin requestToLogin = new RequestToLogin(member.getEmail(), "qqq");

    // when
    When(requestToLogin, post("/api/accounts/login"), status().isUnauthorized());
  }

  @Test
  public void 로그인_GET_요청_예외() throws Exception {
    // given
    TestMemberDto member = signUpServiceTest.signUpAndGetMember();
    Assert.assertNotNull(member);
    RequestToLogin requestToLogin = new RequestToLogin(member.getEmail(), member.getPassword());

    // when
    When(requestToLogin, get("/api/accounts/login"), status().isBadRequest());
  }

  @Test
  public void 로그인_이메일_널_예외() throws Exception {
    // given
    RequestToLogin requestToLogin = new RequestToLogin("", "1234");

    // when
    When(requestToLogin, post("/api/accounts/login"), status().isBadRequest());
  }

  @Test
  public void 로그인_이메일_빈칸_예외() throws Exception {
    // given
    RequestToLogin requestToLogin = new RequestToLogin(" ", "1234");

    // when
    When(requestToLogin, post("/api/accounts/login"), status().isBadRequest());
  }

  private void When(RequestToLogin requestToLogin, MockHttpServletRequestBuilder post, ResultMatcher badRequest)
      throws Exception {
    mockMvc.perform(
        post
            .content(objectMapper.writeValueAsString(requestToLogin))
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .accept(MediaType.APPLICATION_JSON_UTF8))
        .andDo(print())
        .andExpect(badRequest);
  }

}
