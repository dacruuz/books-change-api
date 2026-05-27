package br.com.bookschange.infrastructure.shared;

import br.com.bookschange.infrastructure.shared.pagination.PageDTO;
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

    public ResponseEntity<?> buildInternalServerError(Exception exception) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno no servidor");
    }

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

    public ResponseEntity<?> buildBusinessError(Exception exception) {
        Map<String, Object> response = new LinkedHashMap<>();

        response.put("message", exception.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    public ResponseEntity<?> buildNotFoundError(Exception exception) {
        Map<String, Object> response = new LinkedHashMap<>();

        response.put("message", exception.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    public ResponseEntity<?> buildConflictError(Exception exception) {
        Map<String, Object> response = new LinkedHashMap<>();

        response.put("message", exception.getMessage());

        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }

    public ResponseEntity<?> buildCreated(Object data) {
        Map<String, Object> response = new LinkedHashMap<>();

        response.put("message", "Registro criado com sucesso");
        response.put("data", data);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    public ResponseEntity<?> buildSuccess(Object data) {
        Map<String, Object> response = new LinkedHashMap<>();

        response.put("message", "Registro atualizado com sucesso");
        response.put("data", data);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    public ResponseEntity<?> buildSuccessPaged(PageDTO pageDTO) {
        Map<String, Object> response = new LinkedHashMap<>();
        Map<String, Object> page = new LinkedHashMap<>();

        page.put("page", pageDTO.page());
        page.put("pageSize", pageDTO.pageSize());
        page.put("totalPages", pageDTO.totalPages());

        response.put("message", "Registro atualizado com sucesso");
        response.put("data", pageDTO.content());
        response.put("page", page);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
