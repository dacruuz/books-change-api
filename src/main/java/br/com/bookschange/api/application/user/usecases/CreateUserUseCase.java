package br.com.bookschange.api.application.user.usecases;

import br.com.bookschange.api.application.user.adapters.in.dtos.request.CreateUserRequest;
import br.com.bookschange.api.application.user.adapters.in.dtos.response.UserResponse;
import br.com.bookschange.api.application.user.mappers.UserMapper;
import br.com.bookschange.api.application.user.ports.in.CreateUserPortIn;
import br.com.bookschange.api.application.user.ports.out.FindUserPortOut;
import br.com.bookschange.api.application.user.ports.out.SaveUserPortOut;
import br.com.bookschange.api.domain.enums.UserType;
import br.com.bookschange.api.domain.exceptions.BusinessException;
import br.com.bookschange.api.domain.models.User;
import br.com.bookschange.infrastructure.shared.util.CPFUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CreateUserUseCase implements CreateUserPortIn {

    private final UserMapper mapper;
    private final SaveUserPortOut saveUserPortOut;
    private final FindUserPortOut findUserPortOut;

    @Override
    public UserResponse create(String userType, CreateUserRequest request) {
        log.info("Iniciando criação de usuário | email: {}", request.email());

        validateData(request);

        User user = mapper.createUserRequestToEntity(request);
        UserType parsedUserType = UserType.fromValue(userType);

        normalizeData(user);

        user.setUserType(parsedUserType);

        User createdUser = saveUserPortOut.save(user);

        log.info("Usuário criado com sucesso | uuid: {} | tipo: {}", createdUser.getUuid(), createdUser.getUserType());

        return mapper.entityToUserResponse(createdUser);
    }

    private void normalizeData(User user) {
        user.setCpf(CPFUtil.normalize(user.getCpf()));
        user.setEmail(user.getEmail().trim().toLowerCase());
    }

    private void validateData(CreateUserRequest request) {
        validateCpf(request);
        validateEmail(request);
    }

    private void validateEmail(CreateUserRequest request) {
        String normalizedEmail = request.email().trim().toLowerCase();
        boolean emailAlreadyExists = findUserPortOut.existsByEmail(normalizedEmail);

        if (emailAlreadyExists) {
            log.warn("Tentativa de cadastro com e-mail já existente | email: {}", normalizedEmail);
            throw new BusinessException("Já existe um usuário cadastrado com esse e-mail");
        }
    }

    private void validateCpf(CreateUserRequest request) {
        String normalizedCpf = CPFUtil.normalize(request.cpf());
        boolean cpfAlreadyExists = findUserPortOut.existsByCpf(normalizedCpf);

        if (cpfAlreadyExists) {
            log.warn("Tentativa de cadastro com CPF já existente");
            throw new BusinessException("Já existe um usuário cadastrado com esse cpf");
        }
    }
}
