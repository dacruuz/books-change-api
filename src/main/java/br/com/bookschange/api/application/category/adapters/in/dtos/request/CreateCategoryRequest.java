package br.com.bookschange.api.application.category.adapters.in.dtos.request;

import jakarta.validation.constraints.NotBlank;

public record CreateCategoryRequest(
        @NotBlank
        String label,

        @NotBlank
        String slug, // Same writing of label but without accentuation

        @NotBlank
        String description
) {
}
