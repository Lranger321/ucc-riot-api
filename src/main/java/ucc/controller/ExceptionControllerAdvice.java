package ucc.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ucc.exception.TooManyRequestException;

@RestControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler(TooManyRequestException.class)
    public ResponseEntity tooManyRequestExceptionHandler(TooManyRequestException exception) {
        return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).build();
    }
}
