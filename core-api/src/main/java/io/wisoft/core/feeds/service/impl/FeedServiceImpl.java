package io.wisoft.core.feeds.service.impl;

import io.wisoft.core.accounts.dto.LoginDto;
import io.wisoft.core.feeds.dto.RequestToCreateFeed;
import io.wisoft.core.feeds.dto.ResponseToGetFeed;
import io.wisoft.core.feeds.service.FeedService;
import io.wisoft.core.root.entity.Feed;
import io.wisoft.core.root.entity.Hashtag;
import io.wisoft.core.root.entity.Member;
import io.wisoft.core.root.repository.FeedRepository;
import io.wisoft.core.root.repository.HashtagRepository;
import io.wisoft.core.root.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static io.wisoft.core.feeds.dto.ResponseToGetFeed.getFeeds;
import static io.wisoft.core.root.entity.Feed.getRequestPageFoundByHashtags;

@Service
@RequiredArgsConstructor
@Transactional
public class FeedServiceImpl implements FeedService {

  private final FeedRepository feedRepository;
  private final MemberRepository memberRepository;
  private final HashtagRepository hashtagRepository;

  public void createFeed(RequestToCreateFeed dto) {
    Member writer = getMemberWhoRequested();

    List<Hashtag> createdHashtags = dto.getRelatedTags().stream().map(Hashtag::create).collect(Collectors.toList());
    List<Hashtag> foundHashtags = hashtagRepository.findByNames(dto.getRelatedTags());

    List<Hashtag> hashtagList = createdHashtags.stream()
        .filter(createdTag -> foundHashtags
            .stream()
            .noneMatch(foundTag -> createdTag.getName().equals(foundTag.getName())))
        .collect(Collectors.toList());

    hashtagList.forEach(Hashtag::tagged);
    foundHashtags.forEach(Hashtag::tagged);
    if (hashtagList.size() != 0)
      hashtagRepository.saveAll(hashtagList);

    foundHashtags.addAll(hashtagList);

    Feed feed = Feed.create(dto.getTitle(), dto.getMainCode(),
        dto.getContent(), LocalDateTime.now(), foundHashtags, writer);
    feedRepository.save(feed);
  }

  @Transactional(readOnly = true)
  public List<ResponseToGetFeed> getFeedsByPage(int page) {
    Member member = getMemberWhoRequested();

    List<String> hashtagList = member.getInterests()
        .stream()
        .map(Hashtag::getName)
        .collect(Collectors.toList());

    Page<Feed> feedsInPage = feedRepository.findByHashtags(hashtagList, getRequestPageFoundByHashtags(page));
    List<Feed> feeds = feedsInPage.getContent();
    return getFeeds(feeds);
  }

  private Member getMemberWhoRequested() {
    Authentication authentication =
        SecurityContextHolder.getContext().getAuthentication();

    LoginDto loginDto = (LoginDto) authentication.getPrincipal();

    return memberRepository.findByEmail(loginDto.getEmail());
  }

  public void likeFeed(int feedId) {

  }

}
