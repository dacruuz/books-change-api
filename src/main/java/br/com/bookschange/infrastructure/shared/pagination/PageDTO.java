package br.com.bookschange.infrastructure.shared.pagination;

import java.util.List;

public record PageDTO<T>(
        int page,
        int pageSize,
        int totalPages,
        List<T> content
) { }
