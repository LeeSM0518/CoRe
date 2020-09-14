//package io.wisoft.core.hashtag.controller;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import io.wisoft.core.root.entity.Hashtag;
//import io.wisoft.core.root.repository.HashtagRepository;
//import io.wisoft.core.hashtag.dto.RequestToCreateHashtag;
//import org.junit.Assert;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.ActiveProfiles;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.transaction.annotation.Transactional;
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
//public class HashtagControllerTest {
//
//  @Autowired
//  MockMvc mockMvc;
//  @Autowired
//  ObjectMapper objectMapper;
//  @Autowired
//  HashtagRepository hashtagRepository;
//
//  @Test
//  public void 해시태그_생성() throws Exception {
//    // given
//    String name = "java";
//
//    // when
//    RequestToCreateHashtag requestToCreateHashtag = new RequestToCreateHashtag(name);
//    mockMvc.perform(
//        post("/api/hashtags")
//        .content(objectMapper.writeValueAsString(requestToCreateHashtag))
//        .contentType(MediaType.APPLICATION_JSON_VALUE)
//        .accept(MediaType.APPLICATION_JSON_UTF8))
//        .andDo(print())
//        .andExpect(status().isConflict());
//
//    // then
//    Hashtag byName = hashtagRepository.findByName(name);
//    Assert.assertNotNull(byName);
//    Assert.assertEquals(name, byName.getName());
//  }
//
//}
