package io.wisoft.core.hashtag.controller;

import io.wisoft.core.hashtag.dto.RequestToCreateHashtag;
import io.wisoft.core.hashtag.service.HashtagService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/hashtags")
public class HashtagController {

  private final HashtagService hashtagService;

  @PostMapping
  public void createHashtag(@Valid @RequestBody RequestToCreateHashtag dto) {
    hashtagService.createHashtag(dto.getName());
  }

}
