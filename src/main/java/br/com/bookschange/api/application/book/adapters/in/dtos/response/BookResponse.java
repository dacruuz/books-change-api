package br.com.bookschange.api.application.book.adapters.in.dtos.response;

import br.com.bookschange.api.domain.enums.CurrentCondition;

import java.util.UUID;

public record BookResponse(
        UUID uuid,
        String name,
        String author,
        String publisher,
        String resume,
        String category,
        CurrentCondition currentCondition,
        UUID ownerUuid
) {
}
