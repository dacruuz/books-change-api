package br.com.bookschange.api.application.store.adapters.in;

import br.com.bookschange.api.application.store.adapters.in.dtos.request.CreateStoreRequest;
import br.com.bookschange.api.application.store.adapters.in.dtos.response.StoreResponse;
import br.com.bookschange.api.application.store.ports.in.CreateStorePortIn;
import br.com.bookschange.api.application.store.ports.in.FindStorePortIn;
import br.com.bookschange.infrastructure.shared.ApiResponseBuilder;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/store")
@RequiredArgsConstructor
public class StoreController {

    private final ApiResponseBuilder apiResponseBuilder;
    private final CreateStorePortIn createStorePortIn;
    private final FindStorePortIn findStorePortIn;

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody CreateStoreRequest request) {
        StoreResponse response = createStorePortIn.create(request);
        return apiResponseBuilder.buildCreated(response);
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<?> findByUuid(@PathVariable UUID uuid) {
        StoreResponse response = findStorePortIn.findByUuid(uuid);
        return apiResponseBuilder.buildSuccess(response);
    }
}
