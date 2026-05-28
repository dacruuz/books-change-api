package br.com.bookschange.api.application.user.usecases;

import br.com.bookschange.api.application.user.adapters.in.dtos.request.CreateUserRequest;
import br.com.bookschange.api.application.user.adapters.in.dtos.response.CreateUserResponse;
import br.com.bookschange.api.application.user.mappers.UserMapper;
import br.com.bookschange.api.application.user.ports.in.CreateUserPortIn;
import br.com.bookschange.api.application.user.ports.out.CreateUserPortOut;
import br.com.bookschange.api.domain.models.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateUserUseCase implements CreateUserPortIn {

    private final UserMapper mapper;
    private final CreateUserPortOut createUserPortOut;

    @Override
    public CreateUserResponse create(CreateUserRequest request) {
        User user = mapper.createUserRequestToEntity(request);
        user.setActive(true);

        User createdUser = createUserPortOut.create(user);
        return mapper.toCreateUserResponse(createdUser);
    }
}
