<a name="base-model"></a>
# BaseModel — modelo base para entidades

## Propósito
Centralizar atributos e comportamentos comuns a entidades persistidas (identificador, flags e timestamps), evitando duplicação.

## Responsabilidades
- Fornecer UUID único gerado automaticamente.
- Manter flag de ativação (`active`) para soft-delete/controle lógico.
- Registrar `createdAt` no `@PrePersist`.

## Convenções e localização
- Local sugerido no repo: `infrastructure.shared.models` (já existente).
- Evitar colocar lógica de negócio específica aqui — manter apenas comportamento transversal (auditing, active flag).

## Boas práticas / atenção
- Ao usar Lombok em entidades JPA, prefira combinações seguras (ex.: construtor sem-args protegido, getters controlados) para evitar problemas com proxies e frameworks.
- Não conflitar hooks de persistência com lógica de negócio que deva estar no domínio/use-cases.

## Testes
- Testar o preenchimento automático de `createdAt` e o comportamento do flag `active` em persistência.

## Código de referência
- [BaseModel.java](https://github.com/dacruuz/books-change-api/blob/documentation/issue-59-documentacao-api/src/main/java/br/com/bookschange/infrastructure/shared/models/BaseModel.java)
