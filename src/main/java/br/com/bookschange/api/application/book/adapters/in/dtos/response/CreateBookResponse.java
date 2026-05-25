package br.com.bookschange.api.application.book.adapters.in.dtos.response;

import java.util.UUID;

public record CreateBookResponse(
        UUID uuid,
        String name,
        String author,
        String publisher,
        String resume,
        String category,
        String currentCondition
) {
}
