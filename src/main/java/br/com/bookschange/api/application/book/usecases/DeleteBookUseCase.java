package br.com.bookschange.api.application.book.usecases;

import br.com.bookschange.api.application.book.ports.in.DeleteBookPortIn;
import br.com.bookschange.api.application.book.ports.out.DeleteBookPortOut;
import br.com.bookschange.api.application.book.ports.out.FindBookPortOut;
import br.com.bookschange.api.application.bookcategory.ports.out.DeleteBookCategoryPortOut;
import br.com.bookschange.api.domain.models.Book;
import br.com.bookschange.api.domain.models.BookCategory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class DeleteBookUseCase implements DeleteBookPortIn {

    private final FindBookPortOut findBookPortOut;
    private final DeleteBookPortOut deleteBookPortOut;
    private final DeleteBookCategoryPortOut deleteBookCategoryPortOut;

    @Override
    public void delete(UUID uuid) {
        log.info("Iniciando exclusão de livro | uuid: {}", uuid);

        Book book = findBookPortOut.findByUuidOrThrow(uuid);

        deleteBookCategories(book.getBookCategories());
        deleteBookPortOut.delete(book);

        log.info("Livro excluído com sucesso");
    }

    private void deleteBookCategories(List<BookCategory> bookCategoryList) {
        if (!bookCategoryList.isEmpty()) {
            log.info("Excluindo categorias relacionados ao livro.");

            deleteBookCategoryPortOut.deleteAll(bookCategoryList);
            return;
        }
        log.warn("Nenhuma categoria do livro encontrada");
    }
}
