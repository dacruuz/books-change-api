package br.com.bookschange.api.domain.exceptions;

public class ConflictException extends BusinessException {
    public ConflictException(String message) {
        super(message);
    }
}
