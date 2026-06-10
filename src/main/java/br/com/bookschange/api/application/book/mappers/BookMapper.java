package br.com.bookschange.api.application.book.mappers;

import br.com.bookschange.api.application.book.adapters.in.dtos.request.CreateBookRequest;
import br.com.bookschange.api.application.book.adapters.in.dtos.request.UpdateBookRequest;
import br.com.bookschange.api.application.book.adapters.in.dtos.response.BookResponse;
import br.com.bookschange.api.domain.models.Book;
import br.com.bookschange.api.domain.models.BookCategory;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BookMapper {
    Book bookRequestToEntity(CreateBookRequest request);

    @Mapping(target = "owner", ignore = true)
    @Mapping(target = "bookCategories", ignore = true)
    void updateBookFromRequest(UpdateBookRequest request, @MappingTarget Book book);

    @Mapping(target = "ownerUuid", source = "owner.uuid")
    @Mapping(target = "categories", source = "bookCategories")
    BookResponse toBookResponse(Book book);

    default List<String> mapBookCategories(List<BookCategory> bookCategories) {
        return bookCategories
                .stream()
                .map(bookCategory -> bookCategory.getCategory().getLabel())
                .toList();
    }
}
