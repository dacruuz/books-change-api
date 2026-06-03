package br.com.bookschange.api.application.store.services;

import br.com.bookschange.api.application.store.ports.out.FindStorePortOut;
import br.com.bookschange.api.domain.exceptions.BusinessException;
import br.com.bookschange.infrastructure.shared.util.CNPJUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class StoreValidator {

    private final FindStorePortOut findStorePortOut;

    public void validateEmail(String email) {
        String normalizedEmail = email.trim().toLowerCase();

        if (findStorePortOut.existsByEmail(normalizedEmail)) {
            log.warn("Tentativa de cadastro com e-mail existente");
            throw new BusinessException("Já existe uma loja cadastrada com esse e-mail");
        }
    }

    public void validateCnpj(String cnpj) {
        String normalizedCnpj = CNPJUtil.normalize(cnpj);

        if (findStorePortOut.existsByCnpj(normalizedCnpj)) {
            log.warn("Tentativa de cadastro com CNPJ existente");
            throw new BusinessException("Já existe uma loja cadastrada com esse CNPJ");
        }
    }

    public void validateSlug(String slug) {
        String normalizedSlug = slug.trim().toLowerCase();

        if (findStorePortOut.existsBySlug(normalizedSlug)) {
            log.warn("Tentativa de cadastro com identificador existente");
            throw new BusinessException("Já existe uma loja cadastrada com esse identificador");
        }
    }

    public void validateCreation(String email, String cnpj, String slug) {
        validateEmail(email);
        validateCnpj(cnpj);
        validateSlug(slug);
    }

    public void validateUpdate(UUID uuid, String slug) {
        String normalizedSlug = slug.trim().toLowerCase();

        findStorePortOut.findBySlug(normalizedSlug)
                .ifPresent(store -> {
                    if (!store.getUuid().equals(uuid)) {
                        log.warn("Tentativa de edição utilizando identificador já existente | uuid: {}", uuid);
                        throw new BusinessException("Já existe uma loja cadastrada com esse identificador");
                    }
                });
    }
}