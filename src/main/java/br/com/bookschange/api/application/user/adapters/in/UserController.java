package br.com.bookschange.api.application.user.adapters.in;

import br.com.bookschange.api.application.user.adapters.in.dtos.request.CreateUserRequest;
import br.com.bookschange.api.application.user.adapters.in.dtos.response.CreateUserResponse;
import br.com.bookschange.api.application.user.ports.in.CreateUserPortIn;
import br.com.bookschange.infrastructure.shared.ApiResponseBuilder;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final ApiResponseBuilder apiResponseBuilder;
    private final CreateUserPortIn createUserPortIn;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody @Valid CreateUserRequest request) {
        CreateUserResponse response = createUserPortIn.create(request);
        return apiResponseBuilder.buildCreated(response);
    }
}
