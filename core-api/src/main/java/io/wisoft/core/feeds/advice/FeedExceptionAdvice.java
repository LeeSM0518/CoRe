package io.wisoft.core.feeds.advice;

import io.wisoft.core.feeds.exception.RelatedTagsNotFoundException;
import io.wisoft.core.root.advice.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice("io.wisoft.core.feeds")
public class FeedExceptionAdvice {

  @ExceptionHandler(RelatedTagsNotFoundException.class)
  public ResponseEntity<ErrorResponse> relatedTagsNotFound() {
    return ResponseEntity
        .status(HttpStatus.NOT_FOUND)
        .body(new ErrorResponse("관련 태그를 찾을 수 없습니다."));
  }

}
