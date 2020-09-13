package io.wisoft.core.tags.service.impl;

import io.wisoft.core.tags.exception.DuplicateHashtagException;
import io.wisoft.core.root.entity.Hashtag;
import io.wisoft.core.root.repository.HashtagRepository;
import io.wisoft.core.tags.service.HashtagService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HashtagServiceImpl implements HashtagService {

  private final HashtagRepository hashtagRepository;

  public void createHashtag(String name) {
    Hashtag hashtag = hashtagRepository.findByName(name);
    if (hashtag != null)
      throw new DuplicateHashtagException();
    hashtagRepository.save(Hashtag.create(name));
  }

}
