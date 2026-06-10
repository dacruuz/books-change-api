package br.com.bookschange.api.application.category.mappers;

import br.com.bookschange.api.application.category.adapters.in.dtos.request.CreateCategoryRequest;
import br.com.bookschange.api.application.category.adapters.in.dtos.response.CategoryResponse;
import br.com.bookschange.api.domain.models.Category;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    Category createCategoryToEntity(CreateCategoryRequest request);

    CategoryResponse toResponse(Category category);
}
