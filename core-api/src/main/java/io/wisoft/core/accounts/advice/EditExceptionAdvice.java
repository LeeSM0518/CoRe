package io.wisoft.core.accounts.advice;

import io.wisoft.core.accounts.exception.BeforePasswordNotEqualException;
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
        .body(new ErrorResponse("이전 비밀번호가 틀렸습니다."));
  }

  @ExceptionHandler(NewPasswordNotEqualException.class)
  public ResponseEntity<ErrorResponse> newPasswordNotEqual() {
    return ResponseEntity
        .status(HttpStatus.BAD_REQUEST)
        .body(new ErrorResponse("새 비밀번호와 새 비밀번호 확인이 서로 다릅니다."));
  }

}
