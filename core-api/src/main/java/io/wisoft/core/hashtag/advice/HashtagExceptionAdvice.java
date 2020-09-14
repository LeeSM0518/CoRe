package io.wisoft.core.hashtag.advice;

import io.wisoft.core.hashtag.exception.HashtagDuplicateException;
import io.wisoft.core.root.advice.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice("io.wisoft.core.hashtag")
public class HashtagExceptionAdvice {

  @ExceptionHandler(HashtagDuplicateException.class)
  public ResponseEntity<ErrorResponse> hashtagDuplicate() {
    return ResponseEntity
        .status(HttpStatus.CONFLICT)
        .body(new ErrorResponse("해시태그가 이미 존재합니다."));
  }

}
