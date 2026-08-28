package br.com.bookschange.api.application.category.usecases;

import br.com.bookschange.api.application.category.adapters.in.dtos.response.CategoryResponse;
import br.com.bookschange.api.application.category.mappers.CategoryMapper;
import br.com.bookschange.api.application.category.ports.out.FindCategoryPortOut;
import br.com.bookschange.api.domain.models.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FindCategoryUseCaseTest {

    @Mock private CategoryMapper mapper;
    @Mock private FindCategoryPortOut findCategoryPortOut;

    @InjectMocks
    FindCategoryUseCase useCase;

    private Category category;
    private List<Category> categoryList;
    private CategoryResponse expectedResponse;
    private List<CategoryResponse> expectedResponseList;

    @BeforeEach
    void setUp() {
        category = mock(Category.class);
        expectedResponse = mock(CategoryResponse.class);

        categoryList = new ArrayList<>();
        categoryList.add(category);

        expectedResponseList = new ArrayList<>();
        expectedResponseList.add(expectedResponse);
    }

    @Test
    @DisplayName("Deve buscar todas as categorias com sucesso")
    void shouldFindAllCategoriesSuccessfully() {
        // --- ARRANGE
        when(findCategoryPortOut.findAll()).thenReturn(categoryList);
        when(mapper.entityToCategoryResponse(category)).thenReturn(expectedResponse);

        // --- ACT
        List<CategoryResponse> result = useCase.findAll();

        // --- ASSERT
        assertEquals(expectedResponseList, result);
        verify(findCategoryPortOut).findAll();
        verify(mapper).entityToCategoryResponse(category);
    }
}