package br.com.bookschange.api.application.book.adapters.in;

import br.com.bookschange.api.application.book.adapters.in.dtos.request.CreateBookRequest;
import br.com.bookschange.api.application.book.adapters.in.dtos.response.CreateBookResponse;
import br.com.bookschange.api.application.book.ports.in.CreateBookPortIn;
import br.com.bookschange.infrastructure.shared.ApiResponseBuilder;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BooksController {

    private final ApiResponseBuilder apiResponseBuilder;
    private final CreateBookPortIn createBookPortIn;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody @Valid CreateBookRequest request) {
        CreateBookResponse response = createBookPortIn.create(request);
        return apiResponseBuilder.buildCreated(response);
    }
}
