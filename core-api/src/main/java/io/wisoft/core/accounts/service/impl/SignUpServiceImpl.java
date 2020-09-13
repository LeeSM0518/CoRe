package io.wisoft.core.accounts.service.impl;

import io.wisoft.core.accounts.exception.DuplicateEmailException;
import io.wisoft.core.accounts.exception.DuplicateNameException;
import io.wisoft.core.accounts.exception.NotFoundInterestException;
import io.wisoft.core.accounts.service.SignUpService;
import io.wisoft.core.root.domain.Hashtag;
import io.wisoft.core.root.domain.Member;
import io.wisoft.core.root.repository.HashtagRepository;
import io.wisoft.core.root.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SignUpServiceImpl implements SignUpService {

  private final MemberRepository memberRepository;
  private final HashtagRepository hashtagRepository;

  @Override
  public void signUp(String email, String name, String password, List<String> interests) {
    if(memberRepository.findByEmail(email) != null)
      throw new DuplicateEmailException();

    if(memberRepository.findByName(name) != null)
      throw new DuplicateNameException();

    List<Hashtag> hashtagList = hashtagRepository.findByNames(interests);
    if(hashtagList.size() != interests.size())
      throw new NotFoundInterestException();

    hashtagList.forEach(Hashtag::tagged);
    hashtagRepository.saveAll(hashtagList);

    Member member = Member.create(email, name, password, hashtagList);
    memberRepository.save(member);
  }

}
