package br.com.bookschange.infrastructure.shared;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Component
public class ApiResponseBuilder {

    public ResponseEntity<?> buildError(Exception exception) {
        Map<String, Object> response = new LinkedHashMap<>();

        if (exception instanceof MethodArgumentNotValidException e) {
            List<Map<String, String>> errors = e.getBindingResult()
                    .getFieldErrors()
                    .stream()
                    .map(error -> {
                        Map<String, String> field = new HashMap<>();
                        field.put("field", error.getField());
                        field.put("message", error.getDefaultMessage());

                        return field;
                    })
                    .toList();

            response.put("message", "Erro de validação");
            response.put("errors", errors);
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    public ResponseEntity<?> buildCreated(Object data) {
        Map<String, Object> response = new LinkedHashMap<>();

        response.put("message", "Registro criado com sucesso");
        response.put("data", data);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
