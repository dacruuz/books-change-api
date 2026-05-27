package br.com.bookschange.api.application.book.adapters.in.dtos.request;

import jakarta.validation.constraints.NotBlank;

public record BookRequest(
        @NotBlank(message = "O nome do livro é obrigatório")
        String name,

        @NotBlank(message = "O nome do autor é obrigatório")
        String author,

        @NotBlank(message = "O nome da editora é obrigatório")
        String publisher,

        String resume,

        @NotBlank(message = "A categoria do livro é obrigatória")
        String category,

        @NotBlank(message = "O estado atual do livro é obrigatório")
        String currentCondition
) {
}
