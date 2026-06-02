package br.com.bookschange.infrastructure.shared.util;

public final class CNPJUtil {

    private CNPJUtil() {
        throw new IllegalStateException("Classe utilitária");
    }

    /**
     * Remove qualquer caractere não numérico do CNPJ.
     *
     * Ex:
     * 12.345.678/0001-99 -> 12345678000199
     * 12 345 678 0001 99 -> 12345678000199
     */
    public static String normalize(String cnpj) {
        if (cnpj == null || cnpj.isBlank()) {
            return null;
        }

        return cnpj.replaceAll("\\D", "");
    }

    /**
     * Formata CNPJ para exibição.
     *
     * Ex:
     * 12345678000199 -> 12.345.678/0001-99
     */
    public static String format(String cnpj) {
        String normalizedCnpj = normalize(cnpj);

        if (normalizedCnpj == null || normalizedCnpj.length() != 14) {
            return cnpj;
        }

        return normalizedCnpj.replaceFirst(
                "(\\d{2})(\\d{3})(\\d{3})(\\d{4})(\\d{2})",
                "$1.$2.$3/$4-$5"
        );
    }
}