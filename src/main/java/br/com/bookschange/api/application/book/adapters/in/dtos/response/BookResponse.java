package br.com.bookschange.api.application.book.adapters.in.dtos.response;

import br.com.bookschange.api.domain.enums.CurrentCondition;

import java.util.List;
import java.util.UUID;

public record BookResponse(
        UUID uuid,
        String name,
        String author,
        String publisher,
        String resume,
        List<String> categories,
        CurrentCondition currentCondition,
        UUID ownerUuid
) {
}
