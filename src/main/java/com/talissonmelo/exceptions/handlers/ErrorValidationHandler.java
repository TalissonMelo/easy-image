package com.talissonmelo.exceptions.handlers;

import com.talissonmelo.exceptions.domain.Details;
import com.talissonmelo.exceptions.domain.Field;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@ControllerAdvice
public class ErrorValidationHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {Exception.class})
    protected ResponseEntity<Details> handleConflict(RuntimeException exception) {

        Details details = new Details("Bad Request Exception",
                HttpStatus.BAD_REQUEST.value(),
                LocalDateTime.now(),
                exception.getClass().getName() + " " + exception.getMessage(),
                Arrays.asList());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(details);
    }

    @ExceptionHandler(value = {DataIntegrityViolationException.class})
    protected ResponseEntity<Details> handleConflict(DataIntegrityViolationException exception) {
        Details details = new Details("Check the registered data, there may be unique records already registered in the system.",
                HttpStatus.BAD_REQUEST.value(),
                LocalDateTime.now(),
                exception.getClass().getName() + " " + exception.getMessage(),
                Arrays.asList());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(details);

    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        return super.handleHttpMessageNotReadable(ex, headers, status, request);
    }

    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {

        Details error = new Details("Bad Request Exception", HttpStatus.BAD_REQUEST.value(), LocalDateTime.now(), ex.getClass().getName() + " " + ex.getMessage(), Arrays.asList());


        return super.handleExceptionInternal(ex, error, headers, status, request);
    }

    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<Field> errors = new ArrayList<>();

        for (ObjectError error : ex.getBindingResult().getAllErrors()) {
            String name = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            errors.add(new Field(name, message));
        }

        Details error = new Details("Bad Request Exception", HttpStatus.BAD_REQUEST.value(), LocalDateTime.now(), ex.getClass().getName() + " " + ex.getMessage(), errors);


        return super.handleExceptionInternal(ex, error, headers, status, request);
    }
}
