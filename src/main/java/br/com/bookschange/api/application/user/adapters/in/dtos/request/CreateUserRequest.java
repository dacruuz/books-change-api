package br.com.bookschange.api.application.user.adapters.in.dtos.request;

import br.com.bookschange.api.domain.enums.Gender;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record CreateUserRequest(
        @NotBlank(message = "O nome obrigatório")
        String name,

        @NotBlank(message = "O cpf é obrigatório")
        String cpf,

        @NotNull(message = "O gênero é obrigatório")
        Gender gender,

        @NotBlank(message = "O email é obrigatório")
        String email,

        @NotBlank(message = "A senha é obrigatória")
        String password,

        @NotNull(message = "A data de nascimento é obrigatório")
        LocalDate birthDate
) {
}
