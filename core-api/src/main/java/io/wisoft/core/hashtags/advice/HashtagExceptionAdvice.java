package io.wisoft.core.hashtags.advice;

import io.wisoft.core.hashtags.exception.HashtagDuplicateException;
import io.wisoft.core.hashtags.exception.HashtagNameContainsSpecialCharException;
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
        .body(new ErrorResponse("태그가 이미 존재합니다."));
  }

  @ExceptionHandler(HashtagNameContainsWhiteSpaceException.class)
  public ResponseEntity<ErrorResponse> hashtagNameContainsWhiteSpace() {
    return ResponseEntity
        .status(HttpStatus.BAD_REQUEST)
        .body(new ErrorResponse("태그에 공백을 기입할 수 없습니다."));
  }

  @ExceptionHandler(HashtagNameContainsSpecialCharException.class)
  public ResponseEntity<ErrorResponse> hashtagNameContainsSpecialChar() {
    return ResponseEntity
        .status(HttpStatus.BAD_REQUEST)
        .body(new ErrorResponse("태그에 특수문자를 기입할 수 없습니다."));
  }

}
