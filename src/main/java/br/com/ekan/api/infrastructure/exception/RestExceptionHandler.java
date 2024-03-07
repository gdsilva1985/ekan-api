package br.com.ekan.api.infrastructure.exception;


import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class RestExceptionHandler  {

    @ExceptionHandler(InvalidParameterException.class)
    public ResponseEntity<?> handleInvalidParameterException(InvalidParameterException exception,
                                                             HttpServletRequest request) {
        return new ResponseEntity<>(buildError(exception, HttpStatus.BAD_REQUEST)
                , null, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<?> handleNotFoundException(NotFoundException exception,
                                                     HttpServletRequest request) {
        return new ResponseEntity<>(buildError(exception, HttpStatus.NOT_FOUND)
                , null, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleException(Exception exception,
                                             HttpServletRequest request) {
        return new ResponseEntity<>(buildError(exception, HttpStatus.INTERNAL_SERVER_ERROR)
                , null, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationExceptions(MethodArgumentNotValidException exception) {
        Map<String, String> errors = new HashMap<>();
        exception.getBindingResult().getFieldErrors().forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
        return new ResponseEntity<>(buildError(exception, HttpStatus.BAD_REQUEST, errors)
                , null, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<?> handleEntityNotFoundException(EntityNotFoundException exception,
                                                     HttpServletRequest request) {
        return new ResponseEntity<>(buildError(exception, HttpStatus.NOT_FOUND)
                , null, HttpStatus.NOT_FOUND);
    }

    private ErrorDetail buildError(Exception exception, HttpStatus status) {
        return new ErrorDetail.Builder()
                .timeStamp(String.valueOf(new Date().getTime()))
                .status(String.valueOf(status.value()))
                .title("Error.")
                .message(exception.getMessage())
                .developerMessage(exception.getClass().getName())
                .build();
    }

    private ErrorDetail buildError(Exception exception,  HttpStatus status, Map<String, String> errors) {
        return new ErrorDetail.Builder()
                .timeStamp(String.valueOf(new Date().getTime()))
                .status(String.valueOf(status.value()))
                .title("Error.")
                .message(errors.toString())
                .developerMessage(exception.getClass().getName())
                .build();
    }
}