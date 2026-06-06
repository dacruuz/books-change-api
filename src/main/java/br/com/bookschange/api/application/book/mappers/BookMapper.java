package br.com.bookschange.api.application.book.mappers;

import br.com.bookschange.api.application.book.adapters.in.dtos.request.BookRequest;
import br.com.bookschange.api.application.book.adapters.in.dtos.response.BookResponse;
import br.com.bookschange.api.domain.models.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface BookMapper {
    Book bookRequestToEntity(BookRequest request);

    void updateBookFromRequest(BookRequest request, @MappingTarget Book book);

    @Mapping(target = "ownerUuid", source = "owner.uuid")
    BookResponse toBookResponse(Book book);
}
