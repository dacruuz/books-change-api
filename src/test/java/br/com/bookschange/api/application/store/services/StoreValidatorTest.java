package br.com.bookschange.api.application.store.services;

import br.com.bookschange.api.application.store.ports.out.FindStorePortOut;
import br.com.bookschange.api.domain.models.Store;
import br.com.bookschange.api.domain.models.User;
import br.com.bookschange.api.shared.services.TextNormalizer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StoreValidatorTest {

    @Mock private FindStorePortOut findStorePortOut;

    private StoreValidator validator;
    private UUID ownerUuid;
    private Store store;

    @BeforeEach
    void setUp() {
        validator = new StoreValidator(new TextNormalizer(), findStorePortOut);
        ownerUuid = UUID.randomUUID();
        store = new Store();
        store.setCommercialEmail("teste@email.com");
        store.setCnpj("00.000.000/0000-00");
        store.setSlug("Slug-Teste");
    }

    @Test
    @DisplayName("Deve validar criação com sucesso")
    void shouldValidateCreationSuccessfully() {
        String normalizedEmail = "TESTE@EMAIL.COM";
        String normalizedCnpj = "00000000000000";
        String normalizedSlug = "slug-teste";

        when(findStorePortOut.existsByEmail(normalizedEmail)).thenReturn(false);
        when(findStorePortOut.existsByCnpj(normalizedCnpj)).thenReturn(false);
        when(findStorePortOut.existsBySlug(normalizedSlug)).thenReturn(false);
        when(findStorePortOut.findByOwnerUuid(ownerUuid)).thenReturn(Optional.empty());

        assertDoesNotThrow(() -> validator.validateCreation(
                store.getCommercialEmail(),
                store.getCnpj(),
                store.getSlug(),
                ownerUuid
        ));

        verify(findStorePortOut).existsByEmail(normalizedEmail);
        verify(findStorePortOut).existsByCnpj(normalizedCnpj);
        verify(findStorePortOut).existsBySlug(normalizedSlug);
        verify(findStorePortOut).findByOwnerUuid(ownerUuid);
    }

//    @Test
//    @DisplayName("Deve validar atualização com sucesso")
//    void shouldValidateUpdateSuccessfully() {
//        String normalizedSlug = "slug-teste";
//
//        when(findStorePortOut.findBySlug(normalizedSlug)).thenReturn(Optional.empty());
//
//        verify();
//    }
}