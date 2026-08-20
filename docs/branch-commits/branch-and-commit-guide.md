<a name="branch-and-commit-guide"></a>
# Padrões de Branches e Commits — books-change-api

## Objetivo
- Definir convenções claras e separadas para nomes de branches e mensagens de commit, garantindo consistência e rastreabilidade.

## Sumário
- Branches
- Convenções de commit
- Pull Request & Merge
- Recomendações e ferramentas
- Fluxo rápido (comandos)

---

## 1) Branches

### Branches principais
- `master` — branch estável, pronta para produção.
- `develop` — branch de integração (usada para testes).

### Tipos de branches de trabalho e formato
- feature: `feature/<ISSUE>-<descrição-curta>`
    - Uso: novas funcionalidades.
    - Ex.: `feature/0033-endereco-crud`
- bug: `bug/<ISSUE>-<descrição-curta>`
    - Uso: correções de bugs relacionadas a uma issue.
    - Ex.: `bug/0015-cpf-unique`
- hotfix: `hotfix/<descrição-curta>`
    - Uso: correções urgentes diretamente em produção; partir de `master`.
    - Ex.: `hotfix/nullpointer-store`
- release: `release/<versão>`
    - Uso: preparação de release (ex.: `release/1.2.0`).
- chore: `chore/<ISSUE>-<descrição>`
    - Uso: tarefas de manutenção (dependências, build).
    - Ex.: `chore/update-deps`
- docs: `docs/<ISSUE>-<descrição>`
    - Uso: alterações somente em documentação.
    - Ex.: `docs/add-architecture-md`
- test: `test/<ISSUE>-<descrição>`
    - Uso: mudanças relacionadas a testes.
- refactor: `refactor/<ISSUE>-<descrição>`
    - Uso: mudanças arquitetural ou escrita que não alteração o fluxo.

---

