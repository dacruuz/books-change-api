package br.com.bookschange.api.domain.exceptions;

public class ValidationException extends BusinessException {
    public ValidationException(String message) {
        super(message);
    }
}
