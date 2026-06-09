package br.com.bookschange.api.application.category.usecases;

import br.com.bookschange.api.application.category.adapters.in.dtos.request.CreateCategoryRequest;
import br.com.bookschange.api.application.category.adapters.in.dtos.response.CategoryResponse;
import br.com.bookschange.api.application.category.mappers.CategoryMapper;
import br.com.bookschange.api.application.category.ports.in.CreateCategoryPortIn;
import br.com.bookschange.api.application.category.ports.out.SaveCategoryPortOut;
import br.com.bookschange.api.domain.models.Category;
import br.com.bookschange.infrastructure.shared.util.DateUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CreateCategoryUseCase implements CreateCategoryPortIn {

    private final CategoryMapper mapper;
    private final SaveCategoryPortOut saveCategoryPortOut;

    @Override
    @Transactional
    public CategoryResponse create(CreateCategoryRequest request) {
        log.info("Criando nova categoria | label: {}", request.label());

        Category category = mapper.createCategoryToEntity(request);
        category.setCreatedAt(DateUtil.now());

        Category createdCategory = saveCategoryPortOut.save(category);

        return mapper.toResponse(createdCategory);
    }
}
