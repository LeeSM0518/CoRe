package io.wisoft.core.accounts.controller;

import io.wisoft.core.accounts.dto.RequestToChangePassword;
import io.wisoft.core.accounts.service.EditService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/accounts/edit")
public class EditController {

  private final EditService editService;

  @PutMapping("/password")
  public void changePassword(HttpServletRequest request, HttpServletResponse response,
                             @RequestBody @Valid RequestToChangePassword dto) {
    editService.changePassword(request, response, dto);
  }

}
