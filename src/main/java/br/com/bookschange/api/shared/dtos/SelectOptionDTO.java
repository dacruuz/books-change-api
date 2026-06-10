package br.com.bookschange.api.shared.dtos;

import java.util.UUID;

/**
 * Representa uma opção de seleção utilizada nos responses da API.
 *
 * <p>Este DTO é reutilizado sempre que um recurso precisar retornar
 * um objeto simples composto por um identificador único e um texto
 * para exibição no frontend.</p>
 *
 * <p>Exemplo:</p>
 *
 * <pre>
 * {
 *   "uuid": "7801aed4-c067-42eb-ac1a-bda4a3b8c8ce",
 *   "label": "FICÇÃO CIENTÍFICA"
 * }
 * </pre>
 *
 * @param uuid identificador único do recurso
 * @param label texto amigável para exibição ao usuário
 */
public record SelectOptionDTO(
        UUID uuid,
        String label
) {
}
