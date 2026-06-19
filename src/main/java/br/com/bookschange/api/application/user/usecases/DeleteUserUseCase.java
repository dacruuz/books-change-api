package br.com.bookschange.api.application.user.usecases;

import br.com.bookschange.api.application.book.ports.out.DeleteBookPortOut;
import br.com.bookschange.api.application.book.ports.out.FindBookPortOut;
import br.com.bookschange.api.application.store.ports.in.DeleteStorePortIn;
import br.com.bookschange.api.application.store.ports.out.FindStorePortOut;
import br.com.bookschange.api.application.user.ports.in.DeleteUserPortIn;
import br.com.bookschange.api.application.user.ports.out.DeleteUserPortOut;
import br.com.bookschange.api.application.user.ports.out.FindUserPortOut;
import br.com.bookschange.api.domain.models.Book;
import br.com.bookschange.api.domain.models.User;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class DeleteUserUseCase implements DeleteUserPortIn {

    private final FindUserPortOut findUserPortOut;
    private final DeleteUserPortOut deleteUserPortOut;
    private final FindBookPortOut findBookPortOut;
    private final DeleteBookPortOut deleteBookPortOut;
    private final FindStorePortOut findStorePortOut;
    private final DeleteStorePortIn deleteStorePortIn;

    @Override
    @Transactional
    public void delete(UUID uuid) {
        log.info("Iniciando exclusão de usuário | uuid: {}", uuid);

        User user = findUserPortOut.findByUuidOrThrow(uuid);

        deleteUserBooks(user.getUuid());
        deleteUserStore(user.getUuid());

        deleteUserPortOut.delete(user);
        log.info("Usuário excluído com sucesso | uuid: {}", uuid);
    }

    private void deleteUserBooks(UUID ownerUuid) {
        log.debug("Buscando possíveis livros do usuário | ownerUuid: {}", ownerUuid);
        List<Book> books = findBookPortOut.findAllByOwnerUuid(ownerUuid);

        if (books.isEmpty()) {
            log.debug("Usuário não possui livros para exclusão | ownerUuid: {}", ownerUuid);
            return;
        }

        log.info("Excluindo {} livro(s) do usuário | ownerUuid: {}", books.size(), ownerUuid);
        deleteBookPortOut.deleteAll(books);
    }

    private void deleteUserStore(UUID ownerUuid) {
        log.debug("Buscando possível loja do usuário | ownerUuid: {}", ownerUuid);

        findStorePortOut.findByOwnerUuid(ownerUuid)
                .ifPresentOrElse(
                        store -> {
                            log.info("Excluindo loja do usuário | storeUuid: {}", store.getUuid());
                            deleteStorePortIn.delete(store.getUuid(), ownerUuid);
                        },
                        () -> log.debug("Usuário não possui loja cadastrada | ownerUuid: {}", ownerUuid)
                );


    }
}
