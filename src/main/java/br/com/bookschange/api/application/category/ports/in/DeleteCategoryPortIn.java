package br.com.bookschange.api.application.category.ports.in;

import java.util.UUID;

public interface DeleteCategoryPortIn {
    void delete(UUID uuid);
}
