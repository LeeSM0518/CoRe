package io.wisoft.core.accounts.advice;

import io.wisoft.core.accounts.exception.NotEqualCodeException;
import io.wisoft.core.accounts.exception.NotFoundEmailInSessionException;
import io.wisoft.core.root.advice.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice("io.wisoft.core.accounts")
public class AuthenticationCodeExceptionAdvice {

  @ExceptionHandler(NotFoundEmailInSessionException.class)
  public ResponseEntity<ErrorResponse> notFoundEmailInSession() {
    return ResponseEntity
        .status(HttpStatus.NOT_FOUND)
        .body(new ErrorResponse("인증 코드가 존재하지 않습니다. 인증 코드를 재요청해 주시길 바랍니다."));
  }

  @ExceptionHandler(NotEqualCodeException.class)
  public ResponseEntity<ErrorResponse> notEqualCode() {
    return ResponseEntity
        .status(HttpStatus.UNAUTHORIZED)
        .body(new ErrorResponse("인증 코드가 일치하지 않습니다. 인증 코드를 재확인해 주시길 바랍니다."));
  }

  @ExceptionHandler(NumberFormatException.class)
  public ResponseEntity<ErrorResponse> codeFormatException() {
    return ResponseEntity
        .status(HttpStatus.BAD_REQUEST)
        .body(new ErrorResponse("인증 코드는 반드시 숫자로 이루어져야 합니다."));
  }

}
