package ucc.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ucc.exception.PlayerException;
import ucc.exception.TooManyRequestException;

@RestControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler(TooManyRequestException.class)
    public ResponseEntity<String> tooManyRequestExceptionHandler(TooManyRequestException exception) {
        return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).build();
    }

    @ExceptionHandler(PlayerException.class)
    public ResponseEntity<String> playerExceptionHandler(PlayerException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
    }
}
