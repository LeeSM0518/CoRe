package io.wisoft.core.hashtags.service;

import io.wisoft.core.hashtags.dto.ResponseToFindHashtag;

import java.util.List;

public interface HashtagService {

  void createHashtag(String name);

  List<ResponseToFindHashtag> findHashtagListByName(String name);

}
