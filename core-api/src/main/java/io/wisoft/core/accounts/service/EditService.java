package io.wisoft.core.accounts.service;

import io.wisoft.core.accounts.dto.RequestToChangePassword;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface EditService {

  void changePassword(HttpServletRequest request, HttpServletResponse response, RequestToChangePassword dto);

}
