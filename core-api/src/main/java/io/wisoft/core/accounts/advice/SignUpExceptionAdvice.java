package io.wisoft.core.accounts.advice;

import io.wisoft.core.accounts.exception.EmailDuplicateException;
import io.wisoft.core.accounts.exception.NameDuplicateException;
import io.wisoft.core.accounts.exception.InterestNotFoundException;
import io.wisoft.core.root.advice.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice("io.wisoft.core.accounts")
public class SignUpExceptionAdvice {

  @ExceptionHandler(InterestNotFoundException.class)
  public ResponseEntity<ErrorResponse> notFoundInterest() {
    return ResponseEntity
        .status(HttpStatus.BAD_REQUEST)
        .body(new ErrorResponse("관심사 중에 존재하지 않는 해시태그가 존재합니다. 관심사를 재등록하시길 바랍니다."));
  }

  @ExceptionHandler(EmailDuplicateException.class)
  public ResponseEntity<ErrorResponse> duplicateEmail() {
    return ResponseEntity
        .status(HttpStatus.CONFLICT)
        .body(new ErrorResponse("이메일이 이미 존재합니다."));
  }

  @ExceptionHandler(NameDuplicateException.class)
  public ResponseEntity<ErrorResponse> duplicateName() {
    return ResponseEntity
        .status(HttpStatus.CONFLICT)
        .body(new ErrorResponse("이름이 이미 존재합니다."));
  }

}
