package com.mainul35.chatapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.OffsetDateTime;
import java.util.Arrays;

@ControllerAdvice
public class ApplicationExceptionHandler {

    private final String DUPLICATE_ENTRY = "DUPLICATE_ENTRY";
    private final String NOT_FOUND = "NOT_FOUND";
    private final String UNHANDLED_EXCEPTION = "UNHANDLED_EXCEPTION";
    private final String BAD_REQUEST = "BAD_REQUEST";
    private final String CONFLICT = "CONFLICT";
    private final String UNAUTHORIZED = "UNAUTHORIZED";

    @ExceptionHandler(UserAlreadyRegisteredException.class)
    public ResponseEntity<?> globalExceptionHandler(UserAlreadyRegisteredException ex, WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(OffsetDateTime.now(), this.CONFLICT, ex.getMessage(), request.getDescription(false));
        printStackTrace(ex);
        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }

    private void printStackTrace(Exception ex) {
        StackTraceElement[] trace = ex.getStackTrace();
        StringBuilder traceLines = new StringBuilder();
        traceLines.append("Caused By: ").append(ex.fillInStackTrace()).append("\n");
        Arrays.stream(trace).filter(f -> f.getClassName().contains("com.mainul35"))
                .forEach(traceElement -> traceLines.append("\tat ").append(traceElement).append("\n"));
        System.out.println(traceLines);
    }
}
