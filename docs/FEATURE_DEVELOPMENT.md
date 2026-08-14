# Fluxo de desenvolvimento de uma feature — books-change-api

Objetivo
- Padronizar como novas funcionalidades são desenvolvidas de forma consistente com a arquitetura hexagonal/clean do projeto.

Pré-requisitos
- Issue (ou task) criada com escopo, critérios de aceite e cenário(s) de teste.
- Branch base atualizada (ex.: `master` ou `develop`, conforme convenção).

Estrutura de branch e nomeação
- Branch: feature/<issue-number>-<short-description>
    - Ex.: `feature/0033-endereco-crud`
- Commit messages: `<tipo>(<escopo>): <mensagem>`
    - Tipo: feat, fix, refactor, chore, docs, test
    - Ex.: `feat(address): add address normalizer and validator`

Estrutura de feature (padrão do repositório)
- Use os scripts do root (`common-feature-structure.sh` / `.ps1`) para criar pastas iniciais quando aplicável.
- Pastas / arquivos típicos a incluir:
    - `api/domain/<feature>` — entidades, value objects, exceptions, interfaces (portas).
    - `api/application/<feature>` — casos de uso (use-cases/ services) e DTOs.
    - `infrastructure/<feature>` — controllers (REST), repositórios JPA, mappers, configs.
    - `test/...` — testes unitários e de integração correspondentes.

Passo a passo para implementação
1. Preparação
    - Atualizar branch base.
    - Criar branch da feature seguindo convenção.
2. Modelagem do domínio
    - Definir/ajustar entidades, value objects e invariantes em `api.domain`.
    - Escrever testes unitários para regras de negócio (TDD recomendado).
3. Definir portas (interfaces)
    - Criar interfaces de repositório/serviços necessárias (na camada de domínio ou aplicação).
4. Implementar use-cases
    - Em `api.application`, implementar casos de uso que orquestram o domínio e expõem uma API clara.
    - Escrever testes unitários para os use-cases (mock das portas).
5. Adaptadores de infraestrutura
    - Implementar controllers REST em `infrastructure` (mapeamento DTO <-> domain).
    - Implementar persistência (repositórios JPA, migrations se necessário) em `infrastructure`.
    - Implementar mappers entre entidades e DTOs em `api.shared` ou `infrastructure`.
6. Documentação
    - Atualizar README da feature se houver, documentar endpoints e exemplos.
    - Atualizar changelog ou tarefas relacionadas.
7. PR
    - Abrir PR contra branch base com descrição da feature, captura de telas/postman collection (se aplicável) e checklist preenchido.
    - Checklist mínimo:
        - [ ] Issue vinculada
        - [ ] Testes unitários adicionados (cobertura para regras de negócio)
        - [ ] Testes de integração adicionados
        - [ ] Documentação / README atualizados
        - [ ] Logs/monitoring em pontos de orquestração
        - [ ] Nenhum vazamento de entidade de domínio para controller (usar DTOs)
9. Revisão e merge
    - Fazer squash/merge seguindo convenção do repositório (preservar mensagens claras).
    - Após merge, executar pipeline/CI para garantir deploy/build OK.

Padrões e convenções adicionais
- Validações e normalizações (ex.: email, CPF, endereço) devem idealmente existir em `api.shared` como componentes reutilizáveis.
- Use Exceptions específicas de domínio para erros de negócio; traduza para HTTP status codes no controller/adapters.
- Isolar integrações externas por interface para facilitar testes e mocks.

Exemplo de fluxo para "criar endereço"
1. Issue #0033 criada com critérios de aceite.
2. Criar branch `feature/0033-endereco-crud`.
3. Implementar `Address` (value object) em `api.domain`.
4. Implementar porta `AddressRepository` (interface) em `api.domain`/`application`.
5. Implementar use-case `CreateAddressUseCase` em `api.application`.
6. Implementar `AddressController` + DTOs em `infrastructure`.
7. Implementar `JpaAddressRepository` em `infrastructure`.
8. Testes unitários + integração.
9. PR com checklist e documentação do endpoint.
10. Revisão, ajustes e merge.

Notas finais
- Os scripts `common-feature-structure.sh` / `.ps1` no root ajudam a padronizar a criação inicial de arquivos/pastas para cada feature — adote-os como ponto de partida.
- Mantenha a disciplina de separar interfaces e implementações: facilita refatorações e testes.