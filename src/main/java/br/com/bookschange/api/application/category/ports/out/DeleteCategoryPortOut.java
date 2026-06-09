package br.com.bookschange.api.application.category.ports.out;

import br.com.bookschange.api.domain.models.Category;

public interface DeleteCategoryPortOut {
    void delete(Category category);
}
