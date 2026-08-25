package br.com.bookschange.infrastructure.shared;

import br.com.bookschange.api.domain.exceptions.BusinessException;
import br.com.bookschange.api.domain.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
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

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<?> handleHttpMessageNotReadable(HttpMessageNotReadableException e) {
        Throwable cause = e.getCause();

        if (cause instanceof tools.jackson.databind.exc.InvalidFormatException ife && ife.getTargetType().isEnum()) {
            String field = ife.getPath().isEmpty()
                    ? "desconhecido"
                    : ife.getPath().get(ife.getPath().size() - 1).getPropertyName();

            String acceptedValues = java.util.Arrays.toString(ife.getTargetType().getEnumConstants());

            String message = String.format(
                    "Valor '%s' inválido para o campo '%s'. Valores aceitos: %s",
                    ife.getValue(), field, acceptedValues
            );

            return apiResponseBuilder.buildInvalidEnumError(message);
        }

        return apiResponseBuilder.buildInvalidEnumError("Corpo da requisição inválido ou mal formatado");
    }
}
