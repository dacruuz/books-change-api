package br.com.bookschange.infrastructure.shared.util;

import java.time.LocalDateTime;

public final class DateUtil {

    private DateUtil() { throw new IllegalStateException("Classe utilitária"); }

    public static LocalDateTime now() {
        return LocalDateTime.now();
    }
}
