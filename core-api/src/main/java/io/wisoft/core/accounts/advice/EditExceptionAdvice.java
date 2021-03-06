package io.wisoft.core.accounts.advice;

import io.wisoft.core.accounts.exception.BeforePasswordNotEqualException;
import io.wisoft.core.accounts.exception.MultipartNotFoundException;
import io.wisoft.core.accounts.exception.NewPasswordNotEqualException;
import io.wisoft.core.accounts.exception.UncertifiedMemberException;
import io.wisoft.core.root.advice.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice("io.wisoft.core.accounts")
public class EditExceptionAdvice {

  @ExceptionHandler(UncertifiedMemberException.class)
  public ResponseEntity<ErrorResponse> uncertifiedMember() {
    return ResponseEntity
        .status(HttpStatus.UNAUTHORIZED)
        .body(new ErrorResponse("로그인이 되어있지 않습니다."));
  }

  @ExceptionHandler(BeforePasswordNotEqualException.class)
  public ResponseEntity<ErrorResponse> beforePasswordNotEqual() {
    return ResponseEntity
        .status(HttpStatus.CONFLICT)
        .body(new ErrorResponse("기존 비밀번호가 일치하지 않습니다."));
  }
  @ExceptionHandler(NewPasswordNotEqualException.class)
  public ResponseEntity<ErrorResponse> newPasswordNotEqual() {
    return ResponseEntity
        .status(HttpStatus.BAD_REQUEST)
        .body(new ErrorResponse("새 비밀번호와 새 비밀번호 확인이 일치하지 않습니다."));
  }

  @ExceptionHandler(MultipartNotFoundException.class)
  public ResponseEntity<ErrorResponse> multipartNotFound() {
    return ResponseEntity
        .status(HttpStatus.BAD_REQUEST)
        .body(new ErrorResponse("프로필 사진을 찾을 수 없습니다."));
  }

}
