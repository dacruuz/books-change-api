package br.com.bookschange.api.application.user.adapters.in;

import br.com.bookschange.api.application.user.adapters.in.dtos.request.CreateUserRequest;
import br.com.bookschange.api.application.user.adapters.in.dtos.response.CreateUserResponse;
import br.com.bookschange.api.application.user.adapters.in.dtos.response.FindUserResponse;
import br.com.bookschange.api.application.user.ports.in.CreateUserPortIn;
import br.com.bookschange.api.application.user.ports.in.FindUserPortIn;
import br.com.bookschange.api.application.user.ports.in.InactiveActiveUserPortIn;
import br.com.bookschange.infrastructure.shared.ApiResponseBuilder;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final ApiResponseBuilder apiResponseBuilder;
    private final CreateUserPortIn createUserPortIn;
    private final FindUserPortIn findUserPortIn;
    private final InactiveActiveUserPortIn inactiveActiveUserPortIn;

    @PostMapping("/{userType}")
    public ResponseEntity<?> create(@PathVariable String userType, @RequestBody @Valid CreateUserRequest request) {
        CreateUserResponse response = createUserPortIn.create(userType, request);
        return apiResponseBuilder.buildCreated(response);
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<?> findByUuid(@PathVariable String uuid) {
        FindUserResponse response = findUserPortIn.findByUuid(UUID.fromString(uuid));
        return apiResponseBuilder.buildSuccess(response);
    }

    @PutMapping("/{uuid}/param/{param}")
    public ResponseEntity<?> inactiveActive(@PathVariable String uuid,
                                            @PathVariable String param
    ) {
        FindUserResponse response = inactiveActiveUserPortIn.inactiveActive(UUID.fromString(uuid), param);
        return apiResponseBuilder.buildSuccess(response);
    }
}
