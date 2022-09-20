package com.example.jwtspringsecurity.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;
import java.nio.charset.Charset;

@RestControllerAdvice
public class RestApiExceptionHandler {
    public ResponseEntity<StatusMessage> exceptionHandle(Exception ex) {
        StatusMessage message = new StatusMessage();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        ex.printStackTrace();
        message.setHttpStatus(StatusMessage.StatusEnum.BAD_REQUEST);
        message.setMessage(ex.getMessage());

        return new ResponseEntity<>(message, headers, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<StatusMessage> handleApiRequestException(IllegalArgumentException ex) {
        return exceptionHandle(ex);
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<StatusMessage> handleApiRequestException(NullPointerException ex) {
        return exceptionHandle(ex);
    }

    @ExceptionHandler(IOException.class)
    public ResponseEntity<StatusMessage> handleApiRequestException(IOException ex) {
        return exceptionHandle(ex);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StatusMessage> handleValidationExceptions(MethodArgumentNotValidException ex) {
        return exceptionHandle(ex);
    }
}