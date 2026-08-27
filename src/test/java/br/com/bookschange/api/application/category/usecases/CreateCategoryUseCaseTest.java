package br.com.bookschange.api.application.category.usecases;

import br.com.bookschange.api.application.category.adapters.in.dtos.request.CreateCategoryRequest;
import br.com.bookschange.api.application.category.adapters.in.dtos.response.CategoryResponse;
import br.com.bookschange.api.application.category.mappers.CategoryMapper;
import br.com.bookschange.api.application.category.ports.out.FindCategoryPortOut;
import br.com.bookschange.api.application.category.ports.out.SaveCategoryPortOut;
import br.com.bookschange.api.domain.models.Category;
import br.com.bookschange.api.shared.services.TextNormalizer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

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
    @DisplayName("Deve criar uma nova categoria com sucesso")
    void shouldCreateNewCategoryNormally() {
        // --- ARRANGE ---
        when(normalizer.normalizeToLowerCase(request.slug())).thenReturn("ficcao-cientifica");
        when(findCategoryPortOut.existsBySlug("ficcao-cientifica")).thenReturn(false);
        when(mapper.createCategoryToEntity(request)).thenReturn(mappedCategory);
        when(normalizer.normalizeToLowerCase(mappedCategory.getSlug())).thenReturn("ficcao-cientifica");
        when(normalizer.normalizeToUpperCase(mappedCategory.getLabel())).thenReturn("FICÇÃO CIENTÍFICA");
        when(saveCategoryPortOut.save(mappedCategory)).thenReturn(mappedCategory);

        CategoryResponse expectedResponse = mock(CategoryResponse.class);
        when(mapper.entityToCategoryResponse(mappedCategory)).thenReturn(expectedResponse);

        // --- ACT ---
        CategoryResponse result = useCase.create(request);

        // --- ASSERT ---
        assertEquals(expectedResponse, result);

        ArgumentCaptor<Category> categoryCaptor = ArgumentCaptor.forClass(Category.class);
        verify(saveCategoryPortOut).save(categoryCaptor.capture());

        Category savedCategory = categoryCaptor.getValue();
        assertEquals("FICÇÃO CIENTÍFICA", savedCategory.getLabel());
        assertEquals("ficcao-cientifica", savedCategory.getSlug());
        verify(findCategoryPortOut).existsBySlug("ficcao-cientifica");
        verify(normalizer, times(1)).normalizeToUpperCase(anyString());
        verify(normalizer, times(2)).normalizeToLowerCase(anyString());
    }
}
