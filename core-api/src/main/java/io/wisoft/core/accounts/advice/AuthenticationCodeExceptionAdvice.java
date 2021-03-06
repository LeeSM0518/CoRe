package io.wisoft.core.accounts.advice;

import io.wisoft.core.accounts.exception.CodeNotEqualException;
import io.wisoft.core.accounts.exception.EmailInSessionNotFoundException;
import io.wisoft.core.accounts.exception.EmailNotAuthenticateException;
import io.wisoft.core.accounts.exception.EmailNotFoundException;
import io.wisoft.core.root.advice.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailSendException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice("io.wisoft.core.accounts")
public class AuthenticationCodeExceptionAdvice {

  @ExceptionHandler(EmailInSessionNotFoundException.class)
  public ResponseEntity<ErrorResponse> notFoundEmailInSession() {
    return ResponseEntity
        .status(HttpStatus.NOT_FOUND)
        .body(new ErrorResponse("인증 코드가 존재하지 않습니다."));
  }

  @ExceptionHandler(CodeNotEqualException.class)
  public ResponseEntity<ErrorResponse> notEqualCode() {
    return ResponseEntity
        .status(HttpStatus.UNAUTHORIZED)
        .body(new ErrorResponse("인증 코드가 일치하지 않습니다."));
  }

  @ExceptionHandler(NumberFormatException.class)
  public ResponseEntity<ErrorResponse> codeFormatException() {
    return ResponseEntity
        .status(HttpStatus.BAD_REQUEST)
        .body(new ErrorResponse("인증 코드는 반드시 숫자로 이루어져야 합니다."));
  }

  @ExceptionHandler(EmailNotAuthenticateException.class)
  public ResponseEntity<ErrorResponse> emailNotAuthenticate() {
    return ResponseEntity
        .status(HttpStatus.UNAUTHORIZED)
        .body(new ErrorResponse("인증되지 않은 이메일이거나 인증 시간 초과로 재인증을 받아야 합니다."));
  }

  @ExceptionHandler(EmailNotFoundException.class)
  public ResponseEntity<ErrorResponse> emailNotFound() {
    return ResponseEntity
        .status(HttpStatus.NOT_FOUND)
        .body(new ErrorResponse("이메일을 찾을 수 없습니다."));
  }

  @ExceptionHandler(MailSendException.class)
  public ResponseEntity<ErrorResponse> mailSend() {
    return ResponseEntity
        .status(HttpStatus.REQUEST_TIMEOUT)
        .body(new ErrorResponse("인증 번호 전송을 실패했습니다. 재요청 해주시길 바랍니다."));
  }

}
