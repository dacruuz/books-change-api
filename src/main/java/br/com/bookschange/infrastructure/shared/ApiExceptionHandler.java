package br.com.bookschange.infrastructure.shared;

import br.com.bookschange.api.domain.exceptions.BusinessException;
import br.com.bookschange.api.domain.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@RequiredArgsConstructor
public class ApiExceptionHandler {

    private final ApiResponseBuilder apiResponseBuilder;

    // Generic Exception

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGenericException(Exception e) {
        return apiResponseBuilder.buildInternalServerError(e);
    }

    // Auxiliary Exceptions

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<?> handleBusinessException(BusinessException e) {
        return apiResponseBuilder.buildBusinessError(e);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<?> handleNotFoundException(NotFoundException e) {
        return apiResponseBuilder.buildNotFoundError(e);
    }

    // Spring Exceptions

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleMethodArgumentNotValid(MethodArgumentNotValidException e) {
        return apiResponseBuilder.buildError(e);
    }
}
