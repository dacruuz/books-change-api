<a name="validators"></a>
# Validators (serviços de validação por feature)

## Propósito
Centralizar validações de negócio simples que não pertencem ao domínio (ex.: existência de email/cpf/cnpj, formato de CEP) e evitar duplicação de lógica entre UseCases.

## Padrões observados
- Validators são componentes (beans) injetados nos UseCases; executam validações e lançam `BusinessException` (ou outra exceção de domínio) quando regra é violada.
- Exemplos: `StoreValidator` (verifica email/cnpj/slug únicos), `AddressValidator` (valida CEP).

## Onde usar
- Chamados no início do fluxo do UseCase, antes da persistência e tipicamente antes da normalização (quando a validação depende do formato original) ou depois (quando depende do formato normalizado) — documentar caso a caso.

## Boas práticas
- Separar validações de formato (Bean Validation no DTO) das validações de negócio (existência, unicidade) no Validator.
- Tratamento consistente de exceções (`BusinessException`) e mapeamento para HTTP via `ControllerAdvice`.

## Testes
- Unit tests cobrindo cenários de validação e mensagens/exceções esperadas.

## Código de referência
- [StoreValidator.java](https://github.com/dacruuz/books-change-api/blob/documentation/issue-59-documentacao-api/src/main/java/br/com/bookschange/api/application/store/services/StoreValidator.java)
- [AddressValidator.java](https://github.com/dacruuz/books-change-api/blob/documentation/issue-59/documentacao-api/src/main/java/br/com/bookschange/api/application/address/services/AddressValidator.java)
