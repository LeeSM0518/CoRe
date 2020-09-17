package io.wisoft.core.hashtags.service.impl;

import io.wisoft.core.hashtags.dto.ResponseToFindHashtag;
import io.wisoft.core.hashtags.exception.HashtagDuplicateException;
import io.wisoft.core.hashtags.exception.HashtagNameContainsSpecialCharException;
import io.wisoft.core.hashtags.exception.HashtagNameContainsWhiteSpaceException;
import io.wisoft.core.hashtags.exception.HashtagNameIsNullException;
import io.wisoft.core.hashtags.service.HashtagService;
import io.wisoft.core.root.entity.Hashtag;
import io.wisoft.core.root.repository.HashtagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static io.wisoft.core.root.entity.Hashtag.getRequestPageFoundByName;

@Service
@Transactional
@RequiredArgsConstructor
public class HashtagServiceImpl implements HashtagService {

  private final HashtagRepository hashtagRepository;

  public void createHashtag(String name) {
    if (StringUtils.containsWhitespace(name))
      throw new HashtagNameContainsWhiteSpaceException();

    Hashtag hashtag = hashtagRepository.findByName(name);

    if (hashtag != null)
      throw new HashtagDuplicateException();

    hashtagRepository.save(Hashtag.create(name));
  }

  @Transactional(readOnly = true)
  public List<ResponseToFindHashtag> findHashtagListByName(String name) {
    if (StringUtils.isEmpty(name))
      throw new HashtagNameIsNullException();

    if (StringUtils.containsWhitespace(name))
      throw new HashtagNameContainsWhiteSpaceException();

    Pattern pattern = Pattern.compile("[!@#$%^&*(),.?\":{}|<>]");
    if (pattern.matcher(name).find())
      throw new HashtagNameContainsSpecialCharException();

    Page<Hashtag> byNameStartsWith =
        hashtagRepository.findByNameStartsWith(name, getRequestPageFoundByName());

    return byNameStartsWith
        .getContent()
        .stream()
        .map(elem -> new ResponseToFindHashtag(elem.getName(), elem.getCountOfTagged()))
        .collect(Collectors.toList());
  }

}
