package br.com.bookschange.infrastructure.shared.util;

public final class PhoneUtil {

    private PhoneUtil() {
        throw new IllegalStateException("Classe utilitária");
    }

    /**
     * Remove qualquer caractere não numérico.
     *
     * Ex:
     * (61) 99999-9999 -> 61999999999
     * +55 (61) 99999-9999 -> 5561999999999
     */
    public static String normalize(String phone) {
        if (phone == null || phone.isBlank()) {
            return null;
        }

        return phone.replaceAll("\\D", "");
    }

    /**
     * Formata telefone brasileiro.
     *
     * Ex:
     * 61999999999 -> (61) 99999-9999
     * 6133334444  -> (61) 3333-4444
     */
    public static String format(String phone) {
        String normalizedPhone = normalize(phone);

        if (normalizedPhone == null) {
            return null;
        }

        // Celular
        if (normalizedPhone.length() == 11) {
            return normalizedPhone.replaceFirst(
                    "(\\d{2})(\\d{5})(\\d{4})",
                    "($1) $2-$3"
            );
        }

        // Fixo
        if (normalizedPhone.length() == 10) {
            return normalizedPhone.replaceFirst(
                    "(\\d{2})(\\d{4})(\\d{4})",
                    "($1) $2-$3"
            );
        }

        return phone;
    }
}