package br.com.bookschange.api.application.book.usecases;

import br.com.bookschange.api.application.book.ports.in.DeleteBookPortIn;
import br.com.bookschange.api.application.book.ports.out.DeleteBookPortOut;
import br.com.bookschange.api.application.book.ports.out.FindBookPortOut;
import br.com.bookschange.api.domain.models.Book;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class DeleteBookUseCase implements DeleteBookPortIn {

    private FindBookPortOut findBookPortOut;
    private DeleteBookPortOut deleteBookPortOut;

    @Override
    public void delete(UUID uuid) {
        log.info("Iniciando exclusão de livro | uuid: {}", uuid);

        Book book = findBookPortOut.findByUuidOrThrow(uuid);
        deleteBookPortOut.delete(book);

        log.info("Livro excluído com sucesso");
    }
}
