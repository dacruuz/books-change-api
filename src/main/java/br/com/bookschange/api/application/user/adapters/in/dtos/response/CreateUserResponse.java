package br.com.bookschange.api.application.user.adapters.in.dtos.response;

import br.com.bookschange.api.domain.enums.Gender;
import br.com.bookschange.api.domain.enums.UserType;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;
import java.util.UUID;

public record CreateUserResponse(
        UUID uuid,
        String name,
        String cpf,
        Gender gender,
        String email,
        String password,
        LocalDate birthDate,
        UserType userType
) {
}
