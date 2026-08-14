<a name="select-option-dto"></a>
# SelectOptionDTO — DTO reutilizável para opções de seleção

## Propósito
DTO leve (id + label) para respostas que alimentam dropdowns/combos no frontend, evitando expor toda entidade.

## Responsabilidades
- Ser reutilizável em vários endpoints que apenas precisam de identificador e rótulo.

## Convenções
- Implementar como `record` (imutável e conciso).
- Mapear explicitamente em mappers (MapStruct ou métodos utilitários).

## Boas práticas
- Usar sempre que o frontend precisa apenas de uma visão resumida do recurso.
- Documentar quais campos populam o `label` (ex.: `categoria.label`).

## Testes
- Garantir mapeamento correto em mappers que convertem coleções de entidades para `List<SelectOptionDTO>`.

## Código de referência
- [SelectOptionDTO.java](https://github.com/dacruuz/books-change-api/blob/documentation/issue-59-documentacao-api/src/main/java/br/com/bookschange/api/shared/dtos/SelectOptionDTO.java)
