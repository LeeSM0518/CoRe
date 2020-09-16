package io.wisoft.core.hashtags.advice;

import io.wisoft.core.hashtags.exception.HashtagDuplicateException;
import io.wisoft.core.hashtags.exception.HashtagNameContainsWhiteSpaceException;
import io.wisoft.core.root.advice.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice("io.wisoft.core.hashtags")
public class HashtagExceptionAdvice {

  @ExceptionHandler(HashtagDuplicateException.class)
  public ResponseEntity<ErrorResponse> hashtagDuplicate() {
    return ResponseEntity
        .status(HttpStatus.CONFLICT)
        .body(new ErrorResponse("해시태그가 이미 존재합니다."));
  }

  @ExceptionHandler(HashtagNameContainsWhiteSpaceException.class)
  public ResponseEntity<ErrorResponse> hashtagNameContainsWhiteSpace() {
    return ResponseEntity
        .status(HttpStatus.BAD_REQUEST)
        .body(new ErrorResponse("이름에 공백은 기입할 수 없습니다."));
  }

}
