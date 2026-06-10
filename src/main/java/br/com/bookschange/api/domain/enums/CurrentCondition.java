package br.com.bookschange.api.domain.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CurrentCondition {
    NEW("NOVO"),
    EXCELLENT("EXCELENTE"),
    GOOD("BOM"),
    REGULAR("REGULAR"),
    BAD("RUIM");

    @JsonValue // Serialize enum description value to user
    private final String description;
}
