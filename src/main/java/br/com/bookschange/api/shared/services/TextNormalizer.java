package br.com.bookschange.api.shared.services;

import br.com.bookschange.infrastructure.shared.util.CNPJUtil;
import br.com.bookschange.infrastructure.shared.util.CPFUtil;
import br.com.bookschange.infrastructure.shared.util.PhoneUtil;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class TextNormalizer {

    public String normalize(String value) {
        if (checkNullValue(value)) return value;

        return value.trim();
    }

    public String normalizeToUpperCase(String value) {
        if (checkNullValue(value)) return value;

        return value.trim().toUpperCase();
    }

    public String normalizeToLowerCase(String value) {
        if (checkNullValue(value)) return value;

        return value.trim().toLowerCase();
    }

    public String normalizeCpf(String value) {
        if (checkNullValue(value)) return value;

        return CPFUtil.normalize(value);
    }

    public String normalizeCnpj(String value) {
        if (checkNullValue(value)) return value;

        return CNPJUtil.normalize(value);
    }

    public String normalizePhone(String value) {
        if (checkNullValue(value)) return value;

        return PhoneUtil.normalize(value);
    }

    public String normalizeZipCode(String value) {
        if (checkNullValue(value)) return value;

        return value.replaceAll("\\D", "");
    }

    public String capitalize(String value) {
        if (checkNullValue(value)) return value;

        return value.substring(0, 1).toUpperCase(Locale.ROOT)
                + value.substring(1).toLowerCase(Locale.ROOT);
    }

    private static boolean checkNullValue(String value) {
        if (value == null || value.isBlank()) {
            return true;
        }
        return false;
    }
}
