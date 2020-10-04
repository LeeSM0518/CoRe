package io.wisoft.core.accounts.service.impl;

import io.wisoft.core.accounts.dto.LoginDto;
import io.wisoft.core.accounts.dto.RequestToChangePassword;
import io.wisoft.core.accounts.dto.RequestToChangeProfile;
import io.wisoft.core.accounts.exception.BeforePasswordNotEqualException;
import io.wisoft.core.accounts.exception.NameDuplicateException;
import io.wisoft.core.accounts.exception.NewPasswordNotEqualException;
import io.wisoft.core.accounts.exception.UncertifiedMemberException;
import io.wisoft.core.accounts.service.EditService;
import io.wisoft.core.root.entity.Member;
import io.wisoft.core.root.repository.MemberRepository;
import io.wisoft.core.utils.FileManagementUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

@Service
@Transactional
@RequiredArgsConstructor
public class EditServiceImpl implements EditService {

  private final PasswordEncoder passwordEncoder;
  private final MemberRepository memberRepository;

  public void changePassword(HttpServletRequest request, HttpServletResponse response, RequestToChangePassword dto) {
    Authentication authentication = getAuthentication();

    LoginDto loginDto = (LoginDto) authentication.getPrincipal();
    String password = loginDto.getPassword();

    if (!passwordEncoder.matches(dto.getBeforePassword(), password))
      throw new BeforePasswordNotEqualException();

    if (!dto.getNewPassword().equals(dto.getCheckNewPassword()))
      throw new NewPasswordNotEqualException();

    String email = loginDto.getEmail();
    Member member = memberRepository.findByEmail(email);
    member.changePassword(passwordEncoder.encode(dto.getCheckNewPassword()));
    memberRepository.save(member);

    new SecurityContextLogoutHandler().logout(request, response, authentication);
  }

  public void changeProfile(RequestToChangeProfile dto) {
    Authentication authentication = getAuthentication();

    LoginDto loginDto = (LoginDto) authentication.getPrincipal();

    Member member = memberRepository.findByName(dto.getName());
    if (member != null)
      throw new NameDuplicateException();

    loginDto.setName(dto.getName());

    Member membersToChange = memberRepository.findByEmail(loginDto.getEmail());
    membersToChange.changeProfile(dto.getName(), dto.getWebsite(), dto.getIntroduction(), dto.getIsPublicEmail());
    memberRepository.save(membersToChange);
  }

  public void uploadPhoto(MultipartFile multipartFile) {
    Authentication authentication = getAuthentication();

    LoginDto loginDto = (LoginDto) authentication.getPrincipal();
    String extension = FileManagementUtil.getExtension(multipartFile);
    String username = loginDto.getEmail().split("@")[0];

    String filePath = FileManagementUtil.IMAGE_PATH + "/" + username + "." + extension;
    try {
      multipartFile.transferTo(new File(filePath));
    } catch (IOException e) {
      e.printStackTrace();
    }

    Member member = memberRepository.findByEmail(loginDto.getEmail());
    member.changePhoto(username + "." + extension);
    memberRepository.save(member);
  }

  public void deletePhoto() {
    Authentication authentication = getAuthentication();
    LoginDto loginDto = (LoginDto) authentication.getPrincipal();
    Member member = memberRepository.findByEmail(loginDto.getEmail());
    member.changePhoto(null);
    memberRepository.save(member);
  }

  private Authentication getAuthentication() {
    Authentication authentication =
        SecurityContextHolder.getContext().getAuthentication();

    if (authentication == null)
      throw new UncertifiedMemberException();
    return authentication;
  }

}
