package br.com.bookschange.api.application.category.usecases;

import br.com.bookschange.api.application.bookcategory.ports.out.DeleteBookCategoryPortOut;
import br.com.bookschange.api.application.bookcategory.ports.out.FindBookCategoryPortOut;
import br.com.bookschange.api.application.category.ports.in.DeleteCategoryPortIn;
import br.com.bookschange.api.application.category.ports.out.DeleteCategoryPortOut;
import br.com.bookschange.api.application.category.ports.out.FindCategoryPortOut;
import br.com.bookschange.api.domain.models.BookCategory;
import br.com.bookschange.api.domain.models.Category;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class DeleteCategoryUseCase implements DeleteCategoryPortIn {

    private final FindCategoryPortOut findCategoryPortOut;
    private final DeleteCategoryPortOut deleteCategoryPortOut;
    private final FindBookCategoryPortOut findBookCategoryPortOut;
    private final DeleteBookCategoryPortOut deleteBookCategoryPortOut;

    @Override
    @Transactional
    public void delete(UUID uuid) {
        log.info("Excluindo categoria por uuid | uuid: {}", uuid);

        Category category = findCategoryPortOut.findByUuidOrThrow(uuid);

        deleteBookCategories(category);

        deleteCategoryPortOut.delete(category);
        log.info("Categoria excluída com sucesso");
    }

    /**
     * Busca e exclui possíveis relacionamentos de categorias de livros existentes
     * @param category
     */
    private void deleteBookCategories(Category category) {
        List<BookCategory> bookCategoryList = findBookCategoryPortOut
                .findAllByCategoryUuid(category.getUuid());

        if (!bookCategoryList.isEmpty()) {
            log.info("Excluindo categorias dos livros existentes | Lista: {} livros encontrados", bookCategoryList.size());
            deleteBookCategoryPortOut.deleteAll(bookCategoryList);
            return;
        }
        log.warn("Nenhum livro encontrado para exclusão de categoria | categoryUuid: {}", category.getUuid());
    }
}
