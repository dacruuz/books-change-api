package br.com.bookschange.api.application.user.usecases;

import br.com.bookschange.api.application.user.adapters.in.dtos.request.CreateUserRequest;
import br.com.bookschange.api.application.user.adapters.in.dtos.response.CreateUserResponse;
import br.com.bookschange.api.application.user.mappers.UserMapper;
import br.com.bookschange.api.application.user.ports.in.CreateUserPortIn;
import br.com.bookschange.api.application.user.ports.out.CreateUserPortOut;
import br.com.bookschange.api.application.user.ports.out.FindUserPortOut;
import br.com.bookschange.api.domain.enums.UserType;
import br.com.bookschange.api.domain.exceptions.BusinessException;
import br.com.bookschange.api.domain.models.User;
import br.com.bookschange.infrastructure.shared.util.CPFUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateUserUseCase implements CreateUserPortIn {

    private final UserMapper mapper;
    private final CreateUserPortOut createUserPortOut;
    private final FindUserPortOut findUserPortOut;

    @Override
    public CreateUserResponse create(String userType, CreateUserRequest request) {
        validateCpf(request);

        User user = mapper.createUserRequestToEntity(request);
        user.setActive(true);

        UserType parsedUserType = UserType.fromValue(userType);
        user.setUserType(parsedUserType);

        User createdUser = createUserPortOut.create(user);
        return mapper.toCreateUserResponse(createdUser);
    }

    private void validateCpf(CreateUserRequest request) {
        String normalizedCpf = CPFUtil.normalize(request.cpf());
        boolean cpfAlreadyExists = findUserPortOut.existsByCpf(normalizedCpf);

        if (cpfAlreadyExists) throw new BusinessException("Já existe um usuário cadastrado com esse cpf");
    }
}
