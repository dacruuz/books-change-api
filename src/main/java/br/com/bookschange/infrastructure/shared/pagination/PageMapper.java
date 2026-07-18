package br.com.bookschange.infrastructure.shared.pagination;

import org.springframework.stereotype.Component;

@Component
public class PageMapper {

    public <T> PageDTO<T> toPageDTO(org.springframework.data.domain.Page<T> page) {
        return new PageDTO<>(
                page.getNumber() + 1,
                page.getSize(),
                page.getTotalPages(),
                page.getTotalElements(),
                page.getContent()
        );
    }
}
