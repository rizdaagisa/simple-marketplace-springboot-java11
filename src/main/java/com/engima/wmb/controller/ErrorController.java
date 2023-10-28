package com.engima.wmb.controller;

import com.engima.wmb.model.response.DataResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

@RestControllerAdvice
public class ErrorController {

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<DataResponse<String>> apiException(ResponseStatusException e){
        return ResponseEntity.status(e.getStatus()).body(
                DataResponse.<String>builder()
                        .status(e.getRawStatusCode())
                        .message(e.getReason())
                        .build()
        );
    }
}
