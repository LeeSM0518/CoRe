package io.wisoft.core.accounts.service;

import io.wisoft.core.accounts.dto.RequestToChangePassword;
import io.wisoft.core.accounts.dto.RequestToChangeProfile;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface EditService {

  void changePassword(HttpServletRequest request, HttpServletResponse response, RequestToChangePassword dto);

  void changeProfile(RequestToChangeProfile dto);

  void uploadPhoto(MultipartFile multipartFile);

  void deletePhoto();

}
