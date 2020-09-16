package io.wisoft.core.feeds.controller;

import io.wisoft.core.feeds.dto.RequestToCreateFeed;
import io.wisoft.core.feeds.dto.ResponseToGetFeed;
import io.wisoft.core.feeds.service.FeedService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/feeds")
@RequiredArgsConstructor
public class FeedController {

  private final FeedService feedService;

  @PostMapping
  public void createFeed(@RequestBody @Valid RequestToCreateFeed dto) {
    feedService.createFeed(dto);
  }

  @GetMapping
  public List<ResponseToGetFeed> getFeeds(@RequestParam("page") int page) {
    return feedService.getFeedsByPage(page);
  }

}
