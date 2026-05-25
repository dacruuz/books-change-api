package br.com.bookschange.api.application.book.mappers;

import br.com.bookschange.api.application.book.adapters.in.dtos.request.CreateBookRequest;
import br.com.bookschange.api.application.book.adapters.in.dtos.response.CreateBookResponse;
import br.com.bookschange.api.domain.Book;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookMapper {
    Book creatBookRequestToEntity(CreateBookRequest request);

    CreateBookResponse toCreateBookResponse(Book book);
}
