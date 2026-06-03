package br.com.bookschange.api.application.address.adapters.in;

import br.com.bookschange.api.application.address.adapters.in.dtos.request.CreateAddressRequest;
import br.com.bookschange.api.application.address.adapters.in.dtos.response.AddressResponse;
import br.com.bookschange.api.application.address.ports.in.CreateAddressPortIn;
import br.com.bookschange.api.application.address.ports.in.FindAddressPortIn;
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
}
