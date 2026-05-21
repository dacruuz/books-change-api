package br.com.bookschange.application.features.books.usecases;

import br.com.bookschange.application.features.books.adapters.in.dtos.request.CreateBookRequest;
import br.com.bookschange.application.features.books.adapters.in.dtos.response.CreateBookResponse;
import br.com.bookschange.application.features.books.ports.in.CreateBookPortIn;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateBookUseCase implements CreateBookPortIn {

    private final CreateBookPortIn createBookPortIn;

    @Override
    public CreateBookResponse create(CreateBookRequest request) {
        return null;
    }
}
