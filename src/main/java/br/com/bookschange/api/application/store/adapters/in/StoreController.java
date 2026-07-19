package br.com.bookschange.api.application.store.adapters.in;

import br.com.bookschange.api.application.store.adapters.in.dtos.request.CreateStoreRequest;
import br.com.bookschange.api.application.store.adapters.in.dtos.request.UpdateStoreRequest;
import br.com.bookschange.api.application.store.adapters.in.dtos.response.StoreResponse;
import br.com.bookschange.api.application.store.ports.in.*;
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
    private final UpdateStorePortIn updateStorePortIn;
    private final DeleteStorePortIn deleteStorePortIn;
    private final AssignStoreAddressPortIn assignStoreAddressPortIn;

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

    @PutMapping("/{uuid}")
    public ResponseEntity<?> update(@PathVariable UUID uuid, @Valid @RequestBody UpdateStoreRequest request) {
        StoreResponse response = updateStorePortIn.update(uuid, request);
        return apiResponseBuilder.buildSuccess(response);
    }

    @DeleteMapping("/{storeUuid}/owner/{ownerUuid}")
    public ResponseEntity<?> delete(@PathVariable UUID storeUuid) {
        deleteStorePortIn.delete(storeUuid);
        return apiResponseBuilder.buildDeleted();
    }

    @PutMapping("/{storeUuid}/address/{addressUuid}")
    public ResponseEntity<?> associate(@PathVariable UUID storeUuid,
                                       @PathVariable UUID addressUuid) {
        assignStoreAddressPortIn.assign(storeUuid, addressUuid);
        return apiResponseBuilder.buildSuccess("Loja associada com o endereço");
    }
}
