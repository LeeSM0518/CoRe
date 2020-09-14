package io.wisoft.core.accounts.controller;

import io.wisoft.core.accounts.dto.RequestToChangePassword;
import io.wisoft.core.accounts.dto.RequestToChangeProfile;
import io.wisoft.core.accounts.service.EditService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

  @PutMapping("/profile")
  public void changeProfile(@RequestBody @Valid RequestToChangeProfile dto) {
    editService.changeProfile(dto);
  }

  @PostMapping("/photo")
  public void uploadPhoto(@RequestParam("photo") MultipartFile file) throws Exception {
    editService.uploadPhoto(file);
  }

  @DeleteMapping("/photo")
  public void deletePhoto() {
    editService.deletePhoto();
  }

}
