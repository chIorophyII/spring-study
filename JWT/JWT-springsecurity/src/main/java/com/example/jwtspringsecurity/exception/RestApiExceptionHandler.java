package com.example.jwtspringsecurity.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.BindingResult;
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

    @ExceptionHandler({IllegalArgumentException.class, NullPointerException.class, IOException.class, MethodArgumentNotValidException.class, UsernameNotFoundException.class})
    public ResponseEntity<StatusMessage> handleApiRequestException(Exception ex) {
        return exceptionHandle(ex);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StatusMessage> hadleValidException(MethodArgumentNotValidException ex) {
        StatusMessage statusMessage = makeErrorResponse(ex.getBindingResult());
        return new ResponseEntity<StatusMessage>(statusMessage, HttpStatus.BAD_REQUEST);
    }

    private StatusMessage makeErrorResponse(BindingResult bindingResult) {
        StatusMessage.StatusEnum httpStatus = StatusMessage.StatusEnum.BAD_REQUEST;
        String message = "";

        if (bindingResult.hasErrors()) {
            message = bindingResult.getFieldError().getDefaultMessage();
            String bindResultCode = bindingResult.getFieldError().getCode();
        }
        return new StatusMessage(httpStatus, message);
    }

}