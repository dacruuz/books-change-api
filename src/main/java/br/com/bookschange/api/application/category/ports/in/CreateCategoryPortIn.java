package br.com.bookschange.api.application.category.ports.in;

import br.com.bookschange.api.application.category.adapters.in.dtos.request.CreateCategoryRequest;
import br.com.bookschange.api.application.category.adapters.in.dtos.response.CategoryResponse;

public interface CreateCategoryPortIn {
    CategoryResponse create(CreateCategoryRequest request);
}
