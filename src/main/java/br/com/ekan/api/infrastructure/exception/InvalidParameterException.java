package br.com.ekan.api.infrastructure.exception;

public class InvalidParameterException extends Exception {
    public InvalidParameterException(String message) {
        super(message);
    }
}
