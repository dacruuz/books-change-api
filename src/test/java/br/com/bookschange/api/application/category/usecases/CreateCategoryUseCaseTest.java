package br.com.bookschange.api.application.category.usecases;

import br.com.bookschange.api.application.category.adapters.in.dtos.request.CreateCategoryRequest;
import br.com.bookschange.api.application.category.mappers.CategoryMapper;
import br.com.bookschange.api.application.category.ports.out.FindCategoryPortOut;
import br.com.bookschange.api.application.category.ports.out.SaveCategoryPortOut;
import br.com.bookschange.api.domain.models.Category;
import br.com.bookschange.api.shared.services.TextNormalizer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class CreateCategoryUseCaseTest {

    @Mock private CategoryMapper mapper;
    @Mock private TextNormalizer normalizer;
    @Mock private SaveCategoryPortOut saveCategoryPortOut;
    @Mock private FindCategoryPortOut findCategoryPortOut;

    @InjectMocks
    private CreateCategoryUseCase useCase;

    private CreateCategoryRequest request;
    private Category mappedCategory;

    @BeforeEach
    void setUp() {
        request = new CreateCategoryRequest(
                "Ficcão Científica",
                "ficcao-cientifica",
                "Livros de ficção científica"
        );

        mappedCategory = new Category();
        mappedCategory.setLabel(request.label());
        mappedCategory.setSlug(request.slug());
        mappedCategory.setDescription(request.description());
    }

    @Test
    void shouldCreateNewCategoryNormally() {

    }
}
