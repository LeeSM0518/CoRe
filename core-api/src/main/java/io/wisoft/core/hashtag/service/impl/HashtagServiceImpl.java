package io.wisoft.core.hashtag.service.impl;

import io.wisoft.core.hashtag.exception.HashtagDuplicateException;
import io.wisoft.core.root.entity.Hashtag;
import io.wisoft.core.root.repository.HashtagRepository;
import io.wisoft.core.hashtag.service.HashtagService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class HashtagServiceImpl implements HashtagService {

  private final HashtagRepository hashtagRepository;

  public void createHashtag(String name) {
    Hashtag hashtag = hashtagRepository.findByName(name);
    if (hashtag != null)
      throw new HashtagDuplicateException();
    hashtagRepository.save(Hashtag.create(name));
  }

}
