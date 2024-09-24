package com.feignclientapi.demo.infrastructure.exception.handler;

import com.feignclientapi.demo.infrastructure.exception.BusinessException;
import com.feignclientapi.demo.infrastructure.exception.ConflictException;
import com.feignclientapi.demo.infrastructure.exception.UnprocessableEntityException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorResponse> handlerBusinessException(BusinessException e, HttpServletRequest request) {
        return response(e.getMessage(), request, HttpStatus.INTERNAL_SERVER_ERROR, LocalDateTime.now());
    }

    @ExceptionHandler(UnprocessableEntityException.class)
    public ResponseEntity<ErrorResponse> handlerUnprocessableEntityException(UnprocessableEntityException e, HttpServletRequest request) {
        return response(e.getMessage(), request, HttpStatus.UNPROCESSABLE_ENTITY, LocalDateTime.now());
    }

    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<ErrorResponse> handlerConflictException(ConflictException e, HttpServletRequest request) {
        return response(e.getMessage(), request, HttpStatus.CONFLICT, LocalDateTime.now());
    }

    private ResponseEntity<ErrorResponse> response(final String message,
                                                   final HttpServletRequest request,
                                                   final HttpStatus status,
                                                   LocalDateTime dateTime) {
        return ResponseEntity.status(status)
                .body(new ErrorResponse(message, dateTime, status.value(), request.getRequestURI()));
    }
}
