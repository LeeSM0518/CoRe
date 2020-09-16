package io.wisoft.core.feeds.service;

import io.wisoft.core.feeds.dto.RequestToCreateFeed;
import io.wisoft.core.feeds.dto.ResponseToGetFeed;

import java.util.List;

public interface FeedService {

  void createFeed(RequestToCreateFeed dto);
  List<ResponseToGetFeed> getFeedsByPage(int page);

}
