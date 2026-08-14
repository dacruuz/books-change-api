# Application Service compartilhado: StoreDeletionService

## Propósito
Encapsular regras de negócio reutilizáveis relacionadas à exclusão de uma `Store` que são invocadas por múltiplos UseCases (por ex.: `DeleteStoreUseCase` e fluxo de exclusão de usuário).

## Comportamento observado
- Responsável por orquestrar a exclusão de recursos relacionados a uma `Store`: deletar endereço associado, atualizar tipo do proprietário (owner) e delegar exclusão persistente via port out.
- Executa operações em transação (`@Transactional` no service ou no UseCase que o chama — conferir o local).

## Vantagens do padrão
- Reuso de lógica complexa que envolve múltiplas operações e ports out.
- Reduz duplicação e facilita testes isolados deste fluxo.

## Convenções / responsabilidades
- Serviço é stateless e injetável (bean); comporta-se como um domínio/facade de negócio.
- Deve delegar persistência e deleção concreta a ports out (não chamar repositórios diretamente se houver uma camada de ports).
- Deve documentar efeitos colaterais esperados (ex.: alteração do tipo de usuário, remoção de endereço).

## Boas práticas
- Manter as operações idempotentes quando possível (facilita replays e tolerância a falhas).
- Registrar logs informativos (início, passos, sucesso/erro).
- Testar cenários com store sem endereço, sem owner, etc.
