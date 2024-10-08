package com.thuanht.ezbooking.exception;

import com.thuanht.ezbooking.dto.response.APIResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class GlobalExceptionHandle {

    @ExceptionHandler(AppException.class)
    public ResponseEntity<APIResponse> handleAppException(AppException ex) {
        APIResponse response = APIResponse.builder()
                .success(false)
                .message(ex.getMessage())
                .data("")
                .build();
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
