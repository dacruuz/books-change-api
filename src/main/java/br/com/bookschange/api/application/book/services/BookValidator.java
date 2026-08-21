package br.com.bookschange.api.application.book.services;

import br.com.bookschange.api.domain.exceptions.NotFoundException;
import br.com.bookschange.api.domain.models.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class BookValidator {

    public void validateCategories(List<Category> categories) {
        if (categories.isEmpty()) {
            throw new NotFoundException("Categoria não encontrada");
        }
    }
}
