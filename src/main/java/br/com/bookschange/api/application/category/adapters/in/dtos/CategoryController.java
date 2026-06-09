package br.com.bookschange.api.application.category.adapters.in.dtos;

import br.com.bookschange.api.application.category.adapters.in.dtos.request.CreateCategoryRequest;
import br.com.bookschange.api.application.category.adapters.in.dtos.response.CategoryResponse;
import br.com.bookschange.api.application.category.ports.in.CreateCategoryPortIn;
import br.com.bookschange.infrastructure.shared.ApiResponseBuilder;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
public class CategoryController {

    private final ApiResponseBuilder apiResponseBuilder;
    private final CreateCategoryPortIn createCategoryPortIn;

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody CreateCategoryRequest request) {
        CategoryResponse response = createCategoryPortIn.create(request);
        return apiResponseBuilder.buildCreated(response);
    }
}
