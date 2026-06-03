package br.com.bookschange.api.application.address.adapters.in;

import br.com.bookschange.api.application.address.adapters.in.dtos.request.CreateAddressRequest;
import br.com.bookschange.api.application.address.adapters.in.dtos.request.UpdateAddressRequest;
import br.com.bookschange.api.application.address.adapters.in.dtos.response.AddressResponse;
import br.com.bookschange.api.application.address.ports.in.CreateAddressPortIn;
import br.com.bookschange.api.application.address.ports.in.DeleteAddressPortIn;
import br.com.bookschange.api.application.address.ports.in.FindAddressPortIn;
import br.com.bookschange.api.application.address.ports.in.UpdateAddressPortIn;
import br.com.bookschange.infrastructure.shared.ApiResponseBuilder;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/address")
@RequiredArgsConstructor
public class AddressController {

    private final ApiResponseBuilder apiResponseBuilder;
    private final CreateAddressPortIn createAddressPortIn;
    private final FindAddressPortIn findAddressPortIn;
    private final UpdateAddressPortIn updateAddressPortIn;
    private final DeleteAddressPortIn deleteAddressPortIn;

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody CreateAddressRequest request) {
        AddressResponse response = createAddressPortIn.create(request);
        return apiResponseBuilder.buildCreated(response);
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<?> findByUuid(@PathVariable UUID uuid) {
        AddressResponse response = findAddressPortIn.findByUuid(uuid);
        return apiResponseBuilder.buildSuccess(response);
    }

    @PutMapping("/{uuid}")
    public ResponseEntity<?> update(@PathVariable UUID uuid,
                                    @Valid @RequestBody UpdateAddressRequest request
    ) {
        AddressResponse response = updateAddressPortIn.update(uuid, request);
        return apiResponseBuilder.buildSuccess(response);
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<?> delete(@PathVariable UUID uuid) {
        deleteAddressPortIn.delete(uuid);
        return apiResponseBuilder.buildDeleted();
    }
}
