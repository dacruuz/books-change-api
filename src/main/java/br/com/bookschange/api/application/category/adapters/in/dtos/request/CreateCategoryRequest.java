package br.com.bookschange.api.application.category.adapters.in.dtos.request;

import jakarta.validation.constraints.NotBlank;

public record CreateCategoryRequest(
        @NotBlank(message = "A label é obrigatória")
        String label,

        @NotBlank(message = "O identificador é obrigatório")
        String slug, // Same writing of label but without accentuation

        @NotBlank(message = "A descrição é obrigatória")
        String description
) {
}
