package br.com.bookschange.api.domain.enums;

import br.com.bookschange.api.domain.exceptions.BusinessException;

public enum UserType {
    DEFAULT,
    STORE;

    public static UserType fromValue(String value) {

        for (UserType type : values()) {
            if (type.name().equalsIgnoreCase(value)) {
                return type;
            }
        }

        throw new BusinessException("Tipo de usuário inválido: " + value);
    }
}
