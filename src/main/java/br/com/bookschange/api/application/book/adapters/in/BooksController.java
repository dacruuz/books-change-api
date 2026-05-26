package br.com.bookschange.api.application.book.adapters.in;

import br.com.bookschange.api.application.book.adapters.in.dtos.request.BookRequest;
import br.com.bookschange.api.application.book.adapters.in.dtos.response.BookResponse;
import br.com.bookschange.api.application.book.ports.in.CreateBookPortIn;
import br.com.bookschange.api.application.book.ports.in.UpdateBookPortIn;
import br.com.bookschange.infrastructure.shared.ApiResponseBuilder;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BooksController {

    private final ApiResponseBuilder apiResponseBuilder;
    private final CreateBookPortIn createBookPortIn;
    private final UpdateBookPortIn updateBookPortIn;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody @Valid BookRequest request) {
        BookResponse response = createBookPortIn.create(request);
        return apiResponseBuilder.buildCreated(response);
    }

    @PutMapping("/{uuid}")
    public ResponseEntity<?> update(@PathVariable("uuid") UUID uuid,
                                    @RequestBody @Valid BookRequest request
    ) {
        BookResponse response = updateBookPortIn.update(uuid, request);
        return apiResponseBuilder.buildSuccess(response);
    }
}
