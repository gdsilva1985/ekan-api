package br.com.ekan.api.infrastructure.exception;


import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

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

    private ErrorDetail buildError(Exception exception, HttpStatus status) {
        return new ErrorDetail.Builder()
                .timeStamp(String.valueOf(new Date().getTime()))
                .status(String.valueOf(status.value()))
                .title("Error.")
                .message(exception.getMessage())
                .developerMessage(exception.getClass().getName())
                .build();
    }
}