package br.com.bookschange.api.application.store.adapters.in;

import br.com.bookschange.api.application.store.adapters.in.dtos.request.CreateStoreRequest;
import br.com.bookschange.api.application.store.adapters.in.dtos.response.StoreResponse;
import br.com.bookschange.api.application.store.ports.in.CreateStorePortIn;
import br.com.bookschange.infrastructure.shared.ApiResponseBuilder;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/store")
@RequiredArgsConstructor
public class StoreController {

    private final ApiResponseBuilder apiResponseBuilder;
    private final CreateStorePortIn createStorePortIn;

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody CreateStoreRequest request) {
        StoreResponse response = createStorePortIn.create(request);
        return apiResponseBuilder.buildCreated(response);
    }
}
