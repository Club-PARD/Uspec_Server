package com.example.mz.global.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class MZExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ExceptionResponse> handleMZException(CustomException e){
        return new ResponseEntity<>(ExceptionResponse.builder()
                .httpStatus(e.getExceptionCode().getStatus())
                .message(e.getExceptionCode().getMessage())
                .build(),e.getExceptionCode().getStatus());
    }

}
