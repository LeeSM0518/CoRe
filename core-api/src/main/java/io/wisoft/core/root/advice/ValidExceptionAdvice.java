package io.wisoft.core.root.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Objects;
import java.util.stream.Collectors;

@RestControllerAdvice("io.wisoft.core")
public class ValidExceptionAdvice {

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ErrorResponse> invalidException(
      MethodArgumentNotValidException ex) {
    String errorCodes = ex.getBindingResult().getAllErrors()
        .stream()
        .map(error -> Objects.requireNonNull(error.getDefaultMessage()))
        .collect(Collectors.joining("\n"));
    return ResponseEntity
        .status(HttpStatus.BAD_REQUEST)
        .body(new ErrorResponse(errorCodes));
  }

}
