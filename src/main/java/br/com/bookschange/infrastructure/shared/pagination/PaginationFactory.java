package br.com.bookschange.infrastructure.shared.pagination;

import lombok.NoArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@NoArgsConstructor
public class PaginationFactory {

    public static Pageable createPageable(int page, int pageSize) {
        int normalizedPage = Math.max(page - 1, 0);

        return PageRequest.of(normalizedPage, pageSize);
    }
}
