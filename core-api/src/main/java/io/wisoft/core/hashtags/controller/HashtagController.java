package io.wisoft.core.hashtags.controller;

import io.wisoft.core.hashtags.dto.RequestToCreateHashtag;
import io.wisoft.core.hashtags.dto.ResponseToFindHashtag;
import io.wisoft.core.hashtags.service.HashtagService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/hashtags")
public class HashtagController {

  private final HashtagService hashtagService;

  @PostMapping
  public void createHashtag(@Valid @RequestBody RequestToCreateHashtag dto) {
    hashtagService.createHashtag(dto.getName());
  }

  @GetMapping
  public List<ResponseToFindHashtag> findHashtagList(@RequestParam("name") String name) {
    return hashtagService.findHashtagListByName(name);
  }

}
