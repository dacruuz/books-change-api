package br.com.bookschange.infrastructure.shared.util;

public final class CPFUtil {
    private CPFUtil() { throw new IllegalStateException("Classe utilitária"); }

    /**
     * Remove qualquer caractere não numérico do CPF.
     *
     * Ex:
     * 000.000.000-00 -> 00000000000,
     * 000 000 000 00 -> 00000000000
     */
    public static String normalize(String cpf) {
        if (cpf == null || cpf.isBlank()) {
            return null;
        }

        return cpf.replaceAll("\\D", "");
    }

    /**
     * Formata CPF para exibição.
     *
     * Ex:
     * 00000000000 -> 000.000.000-00
     */
    public static String format(String cpf) {
        String normalizedCpf = normalize(cpf);

        if (normalizedCpf == null || normalizedCpf.length() != 11) {
            return cpf;
        }

        return normalizedCpf.replaceFirst(
                "(\\d{3})(\\d{3})(\\d{3})(\\d{2})",
                "$1.$2.$3-$4"
        );
    }
}
