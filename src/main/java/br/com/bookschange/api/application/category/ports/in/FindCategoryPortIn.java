package br.com.bookschange.api.application.category.ports.in;

import br.com.bookschange.api.application.category.adapters.in.dtos.response.CategoryResponse;

import java.util.List;
import java.util.UUID;

public interface FindCategoryPortIn {
    List<CategoryResponse> findAll();

    CategoryResponse findByUuid(UUID uuid);
}
