package br.com.bookschange.api.application.store.adapters.in.dtos.response;

import java.util.UUID;

public record StoreResponse(
        UUID uuid,
        String name,
        String cnpj,
        String commercialEmail,
        String phone,
        String slug,
        String description,
        UUID ownerUuid
) {
}
