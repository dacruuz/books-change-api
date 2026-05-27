package br.com.bookschange.api.application.user.usecases;

import br.com.bookschange.api.application.user.ports.in.CreateUserPortIn;
import br.com.bookschange.api.application.user.ports.out.CreateUserPortOut;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateUserUseCase implements CreateUserPortIn {

    private final CreateUserPortOut createUserPortOut;

}
