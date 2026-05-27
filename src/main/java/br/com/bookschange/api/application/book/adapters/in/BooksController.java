package br.com.bookschange.api.application.book.adapters.in;

import br.com.bookschange.api.application.book.adapters.in.dtos.request.BookRequest;
import br.com.bookschange.api.application.book.adapters.in.dtos.response.BookResponse;
import br.com.bookschange.api.application.book.ports.in.CreateBookPortIn;
import br.com.bookschange.api.application.book.ports.in.FindBookPortIn;
import br.com.bookschange.api.application.book.ports.in.FindPagedBookPortIn;
import br.com.bookschange.api.application.book.ports.in.UpdateBookPortIn;
import br.com.bookschange.infrastructure.shared.ApiResponseBuilder;
import br.com.bookschange.infrastructure.shared.pagination.PageDTO;
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
    private final FindBookPortIn findBookPortIn;
    private final FindPagedBookPortIn findPagedBookPortIn;

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

    @GetMapping("/{uuid}")
    public ResponseEntity<?> findByUuid(@PathVariable("uuid") UUID uuid) {
        BookResponse response = findBookPortIn.findByUuid(uuid);
        return apiResponseBuilder.buildSuccess(response);
    }

    @GetMapping("/paged")
    private ResponseEntity<?> findAllPaged(@RequestParam(defaultValue = "1") int page,
                                           @RequestParam(defaultValue = "10") int pageSize
    ) {
        PageDTO<BookResponse> response = findPagedBookPortIn.findAllPaged(page, pageSize);
        return apiResponseBuilder.buildSuccessPaged(response);
    }
}
