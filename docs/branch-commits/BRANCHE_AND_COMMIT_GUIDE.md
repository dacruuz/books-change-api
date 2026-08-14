# Padrões de Branches e Commits — books-change-api

Objetivo
- Definir convenções claras e separadas para nomes de branches e mensagens de commit, garantindo consistência e rastreabilidade.

Sumário
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

### Boas práticas de branches
- Base: crie a branch a partir de `master` e `develop`, uma para produção e outra para testes.
- Escopo: mantenha a branch focada em uma única responsabilidade/issue.
- Nome legível: prefira nomes curtos e compreensíveis; inclua o número da issue para rastreabilidade. Ex.: # 3943 - <tipo_commit>: <descrição>.
- Sincronização: atualize sua branch com rebase ou merge da base antes de abrir PR.
- Duração: evite branches muito longas — integre frequentemente.

---

## 2) Mensagens de commit

Adotar Conventional Commits (adaptado). Estrutura:

```
<tipo>(<escopo opcional>): <assunto curto>
```

- Corpo (opcional): descrição detalhada e referências.
- Rodapé (opcional): `BREAKING CHANGE: ...` ou `Fixes: #<issue>`.

### Tipos recomendados
- `feat` — nova funcionalidade
- `fix` — correção de bug
- `docs` — documentação
- `style` — formatação (sem alteração de comportamento)
- `refactor` — refatoração (sem mudança de comportamento)
- `test` — adicionar/alterar testes
- `chore` — manutenção (build, deps)
- `ci` — alterações de CI/pipeline
- `build` — alterações no sistema de build
- `revert` — reverte commit anterior

### Exemplos
- `feat(address): add address normalizer and validator`
- `fix(user): handle null cpf when saving`
- `docs(api): update README with endpoints examples`
- `chore(deps): bump spring-boot to 2.7.9`
- `refactor(account): split service responsibilities`

### Regras e recomendações de formatação
- Use o tempo presente/imperativo no subject (curto e claro).
- Limite o subject a ~72 caracteres; corpo com linhas de até 100 caracteres.
- Um commit = uma mudança lógica. Não misture tipos diferentes no mesmo commit.
- Use `Fixes: #<ISSUE>` para vincular/fechar issues automaticamente.
- Para breaking changes, inclua `BREAKING CHANGE:` no corpo (explique impacto e migração).
- Se o time preferir PT-BR, mantenha consistência (mas inglês é recomendado para times abertos).

---

## 3) Pull Request & Merge

- Base do PR: abrir contra `develop` e `master` conforme o fluxo.
- Título de PR sugerido: `feat(0033): endereco CRUD` (ou similar).
- PR deve conter:
    - Descrição do que foi feito.
    - Checklist: issue vinculada, testes, documentação atualizada, exemplos (se aplicável).
    - Comportamentos observados e instruções para testar localmente.
- Merge:
    - Squash merge recomendado para histórico limpo.
    - Preserve mensagens claras se não for squash.

Checklist mínimo para abrir PR
- [ ] Issue vinculada
- [ ] Testes unitários adicionados/ajustados
- [ ] Documentação atualizada (endpoints, README)
- [ ] Logs/monitoring adicionados nos pontos de orquestração

---

## 4) Ferramentas e dicas

- Validação de commits: usar commitlint + Husky (opcional) para impor Conventional Commits.
- PR template: adicionar `.github/pull_request_template.md` com checklist.
- Git hooks: configurar pre-commit para lint, formatação e testes rápidos.
- Scripts: use os scripts do repositório (`common-feature-structure.sh` / `.ps1`) para padronizar estruturas de feature.

---

## 5) Fluxo rápido (comandos)

Exemplo de criação de branch e commits:
1. Criar branch:
    - `git checkout -b feature/0033-endereco-crud`
2. Fazer alterações e commits atômicos:
    - `git add .`
    - `git commit -m "#<ISSUE> feat(address): add address normalizer"`
3. Push:
    - `git push origin feature/0033-endereco-crud`
4. Abrir PR e preencher checklist
5. Após aprovação, merge (squash).

---