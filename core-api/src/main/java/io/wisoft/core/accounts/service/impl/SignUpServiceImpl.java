package io.wisoft.core.accounts.service.impl;

import io.wisoft.core.accounts.exception.EmailDuplicateException;
import io.wisoft.core.accounts.exception.EmailNotAuthenticateException;
import io.wisoft.core.accounts.exception.NameDuplicateException;
import io.wisoft.core.accounts.service.SignUpService;
import io.wisoft.core.root.entity.Hashtag;
import io.wisoft.core.root.entity.Member;
import io.wisoft.core.root.repository.HashtagRepository;
import io.wisoft.core.root.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Collectors;

import static io.wisoft.core.accounts.service.EmailAuthenticationService.EMAIL_CERTIFIED;

@Service
@RequiredArgsConstructor
@Transactional
public class SignUpServiceImpl implements SignUpService {

  private final MemberRepository memberRepository;
  private final HashtagRepository hashtagRepository;
  private final PasswordEncoder passwordEncoder;

  @Override
  public void signUp(HttpSession session, String email, String name, String password, List<String> interests) {
    String auth = (String) session.getAttribute(email);
    if (auth == null || !auth.equals(EMAIL_CERTIFIED))
      throw new EmailNotAuthenticateException();

    if (memberRepository.findByEmail(email) != null)
      throw new EmailDuplicateException();

    if (memberRepository.findByName(name) != null)
      throw new NameDuplicateException();

    List<Hashtag> createdHashtags = interests.stream().map(Hashtag::create).collect(Collectors.toList());
    List<Hashtag> foundHashtags = hashtagRepository.findByNames(interests);

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

    Member member = Member.create(email, name, passwordEncoder.encode(password), foundHashtags);
    memberRepository.save(member);
  }

}
