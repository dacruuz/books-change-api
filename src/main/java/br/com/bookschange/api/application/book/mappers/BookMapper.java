package br.com.bookschange.api.application.book.mappers;

import br.com.bookschange.api.application.book.adapters.in.dtos.request.CreateBookRequest;
import br.com.bookschange.api.application.book.adapters.in.dtos.request.FilterBookRequest;
import br.com.bookschange.api.application.book.adapters.in.dtos.request.UpdateBookRequest;
import br.com.bookschange.api.application.book.adapters.in.dtos.response.BookResponse;
import br.com.bookschange.api.application.book.dtos.BookFilter;
import br.com.bookschange.api.domain.models.Book;
import br.com.bookschange.api.domain.models.BookCategory;
import br.com.bookschange.api.shared.dtos.SelectOptionDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BookMapper {
    Book createBookRequestToEntity(CreateBookRequest request);

    @Mapping(target = "owner", ignore = true)
    @Mapping(target = "bookCategories", ignore = true)
    void updateBookFromRequest(UpdateBookRequest request, @MappingTarget Book book);

    BookFilter filterBookRequestToBookFilter(FilterBookRequest request);

    @Mapping(target = "ownerUuid", source = "owner.uuid")
    @Mapping(target = "categories", source = "bookCategories")
    BookResponse entityToBookResponse(Book book);

    default List<SelectOptionDTO> mapBookCategories(List<BookCategory> bookCategories) {
        return bookCategories
            .stream()
            .map(bookCategory ->
                new SelectOptionDTO(
                    bookCategory.getCategory().getUuid(),
                    bookCategory.getCategory().getLabel()
                ))
            .toList();
    }
}
