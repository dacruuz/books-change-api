package br.com.bookschange.api.application.category.usecases;

import br.com.bookschange.api.application.category.ports.in.DeleteCategoryPortIn;
import br.com.bookschange.api.application.category.ports.out.DeleteCategoryPortOut;
import br.com.bookschange.api.application.category.ports.out.FindCategoryPortOut;
import br.com.bookschange.api.domain.models.Category;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class DeleteCategoryUseCase implements DeleteCategoryPortIn {

    private final FindCategoryPortOut findCategoryPortOut;
    private final DeleteCategoryPortOut deleteCategoryPortOut;

    @Override
    @Transactional
    public void delete(UUID uuid) {
        log.info("Excluindo categoria por uuid | uuid: {}", uuid);

        Category category = findCategoryPortOut.findByUuidOrThrow(uuid);

        deleteCategoryPortOut.delete(category);
        log.info("Categoria excluída com sucesso");
    }
}
