package br.com.bookschange.api.application.store.services;

import br.com.bookschange.api.domain.models.Store;
import br.com.bookschange.infrastructure.shared.util.CNPJUtil;
import br.com.bookschange.infrastructure.shared.util.PhoneUtil;
import org.springframework.stereotype.Component;

@Component
public class StoreNormalizer {

    public void normalize(Store store) {

        if (store.getCnpj() != null) {
            store.setCnpj(CNPJUtil.normalize(store.getCnpj()));
        }

        if (store.getPhone() != null) {
            store.setPhone(PhoneUtil.normalize(store.getPhone()));
        }

        if (store.getCommercialEmail() != null) {
            store.setCommercialEmail(store.getCommercialEmail().trim().toLowerCase());
        }

        if (store.getSlug() != null) {
            store.setSlug(store.getSlug().trim().toLowerCase());
        }
    }
}