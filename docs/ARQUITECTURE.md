# Arquitetura — books-change-api

Resumo
- Estilo aplicado: Hexagonal + Clean Architecture (adotado de forma pragmática, não estritamente rígida).
- Linguagem / plataforma: Java (Maven), projeto Spring Boot (classe `BookschangeApplication.java`).
- Estrutura observada: pacotes principais em `src/main/java/br/com/bookschange/`:
    - `api.application` — casos de uso / services de aplicação
    - `api.domain` — entidades, regras de negócio e portas (interfaces)
    - `api.shared` — utilitários e componentes compartilhados
    - `infrastructure` — adaptadores de entrada/saída (DB, web, integracoes, etc.)

Princípios e responsabilidades
- Domínio no centro: tudo relacionado às regras de negócio (entidades, agregados, value objects, validações) vive em `api.domain`.
- Casos de uso / Application Services: coordenam fluxos de negócio e orquestram portas do domínio; ficam em `api.application`.
- Portas e adaptadores (Hexagonal):
    - Portas (interfaces) definidas no domínio ou na camada de aplicação.
    - Adaptadores de saída (implementações de repositórios, clientes externos) e de entrada (controllers, mappers) ficam em `infrastructure` ou em `api.application` quando fazem orquestração de camada.
- Separação de responsabilidades:
    - Camadas não dependem de implementações concretas — dependem de interfaces.
    - Infraestrutura depende de interfaces do domínio/aplicação, não o contrário.

Mapeamento para o repositório
- `BookschangeApplication.java` — bootstrap do Spring Boot.
- `src/main/java/br/com/bookschange/api/domain` — modelos de domínio, port-interfaces, validações.
- `src/main/java/br/com/bookschange/api/application` — casos de uso / services / DTOs de entrada/saída.
- `src/main/java/br/com/bookschange/api/shared` — utilitários (ex.: normalizadores, validações comuns, mapeadores).
- `src/main/java/br/com/bookschange/infrastructure` — repositórios JPA, controllers/rest adapters, configurações de infra.

Fluxo de requisição (exemplo de leitura de recurso)
1. Request HTTP chega ao controller (infrastructure.adapter.web).
2. Controller valida/parsing e monta DTO de entrada.
3. Controller chama um Use Case em `api.application`.
4. Use Case aplica regras orquestrando entidades do `api.domain` e chamando portas (ex.: Repositório).
5. Repositório é uma interface; implementação concreta (infraestrutura) faz a persistência e retorna entidades.
6. Use Case monta DTO de saída e retorna para controller.
7. Controller retorna resposta HTTP.

Boas práticas recomendadas
- Interfaces de porta em `api.domain` ou `api.application`; implementações em `infrastructure`.
- Use DTOs entre controller e application para evitar vazamento de camadas.
- Testes:
    - Testes de unidade para use-cases e entidades (mock de portas).
    - Testes de integração para adaptadores (DB, web).
- Logging/monitoring: concentrar logs de orquestração nos use-cases para rastreabilidade.
- Versionamento de API: colocar versão nas rotas (ex.: /api/v1/...).

Observações específicas do repositório
- Há scripts no root ligados à estrutura de features: `common-feature-structure.sh` e `common-feature-structure.ps1` — use-os como padrão para criar a estrutura inicial de uma nova feature.
- Maven (`pom.xml`) e wrapper (`mvnw`) já configurados — siga o padrão de build do projeto.

Diagrama rápido (camadas)
[Client] -> Controller (infrastructure) -> Use Case (application) -> Domain (entities, rules) -> Repository (port) -> Repository (infra implementation / DB)