# books-change-api

API para conectar pessoas e lojas (sebos, livrarias, etc.) interessadas em trocar livros de forma segura e centralizada. Usuários podem propor trocas, combinar livros e combinar pontos de encontro sugeridos — permitindo que pessoas e estabelecimentos se encontrem com mais facilidade.

Status
- Em estágio inicial (MVP em progresso). Muitas funcionalidades estão planejadas para versões futuras.
- Implementação atual: Java + Spring Boot (Maven). Estrutura do projeto segue princípios Hexagonal / Clean Architecture de forma pragmática.

Visão
- Centralizar e facilitar trocas de livros entre usuários e lojas.
- Prover segurança e confiança por meio de avaliações, pontos de encontro recomendados e controles de verificação.
- Facilitar integrações com serviços externos (mapas, notificações) no futuro.

Stack principal
- Linguagem: Java (11/17+ conforme pom.xml)
- Framework: Spring Boot
- Build: Maven (mvnw incluído)
- Organização do código: Hexagonal + Clean Architecture (pacotes: `api.domain`, `api.application`, `api.shared`, `infrastructure`)
- Scripts úteis no repo: `common-feature-structure.sh` e `common-feature-structure.ps1`

Funcionalidades esperadas (MVP / roadmap inicial)
- Registro / autenticação de usuários
- Cadastro de lojas (sebos, livrarias)
- CRUD de livros (usuários e lojas podem publicar livros para troca)
- Criação e gerenciamento de propostas de troca (trade requests)
- Ponto de encontro sugerido e mensagens entre partes
- Avaliações básicas (feedback de troca)
- Testes unitários e de integração

Arquitetura (resumo)
- Domínio no centro (entidades, regras, portas/interfaces) em `api.domain`.
- Casos de uso e serviços de aplicação em `api.application`.
- Adaptadores e implementações (controllers REST, repositórios, JPA) em `infrastructure`.
- Componentes utilitários e validadores em `api.shared`.
- Fluxo: Controller -> Use Case -> Domínio -> Porta -> Implementação Infra -> Use Case -> Controller.

Exemplos de endpoints (modelo/expectativa)
- POST /api/v1/users — criar usuário
- POST /api/v1/stores — cadastrar loja
- GET /api/v1/books — listar livros disponíveis
- POST /api/v1/books — publicar livro para troca
- POST /api/v1/trades — propor troca entre usuários/loja
- GET /api/v1/trades/{id} — consultar proposta de troca

Getting started — desenvolvimento local

Pré-requisitos
- Java 17+ (ou versão compatível definida no `pom.xml`)
- Maven (ou usar o wrapper `./mvnw`)
- Banco de dados (ex.: PostgreSQL) — configure via `application.properties` / `application.yml`
- (Opcional) Docker & Docker Compose para ambientes rápidos

Clone
git clone https://github.com/<owner>/books-change-api.git
cd books-change-api

Build
- Com Maven wrapper:
    - Unix/macOS: ./mvnw clean package
    - Windows: mvnw.cmd clean package
- Ou com Maven local:
    - mvn clean package

Rodar localmente
- Usando Spring Boot:
    - ./mvnw spring-boot:run
- Ou executar o jar:
    - java -jar target/books-change-api-<versão>.jar

Configuração (exemplo)
- Arquivo: `src/main/resources/application.yml` ou `application.properties`
- Variáveis/props típicas:
    - spring.datasource.url=jdbc:postgresql://localhost:5432/bookschange
    - spring.datasource.username=postgres
    - spring.datasource.password=secret
    - spring.jpa.hibernate.ddl-auto=validate (ou update para dev)
    - server.port=8080
- Para ambientes locais, crie um arquivo `application-local.yml` e ative o perfil `-Dspring.profiles.active=local`.

Banco de dados e migrations
- Recomendado: Flyway ou Liquibase para migrations.
- Padrão para dev:
    - Use um container PostgreSQL com Docker Compose ou instância local.
    - Execute as migrations automaticamente no startup (configurar Flyway).
- Exemplo rápido com Docker Compose:
    - docker-compose.yml (exemplo)
      version: '3.8'
      services:
      db:
      image: postgres:15
      environment:
      POSTGRES_DB: bookschange
      POSTGRES_USER: postgres
      POSTGRES_PASSWORD: secret
      ports:
      - "5432:5432"
      volumes:
      - db-data:/var/lib/postgresql/data
      volumes:
      db-data:

Testes
- Unitários e de integração com JUnit + Mockito (ou libs do Spring).
- Rodar:
    - ./mvnw test
- Recomendações:
    - Use profiles separados para testes (ex.: `test`) e banco em memória (H2) para testes rápidos.
    - Testes de integração contra container PostgreSQL (Testcontainers) para validar SQL/JPA.

Documentação da API
- Recomenda-se usar OpenAPI/Swagger (springdoc-openapi) para gerar docs automáticas:
    - Dependência: springdoc-openapi-ui
    - URL típica: http://localhost:8080/swagger-ui.html ou /swagger-ui/index.html
- Mantenha exemplos de request/response em README ou Postman collection.

Logs & Monitoramento
- Logging via SLF4J + Logback por padrão.
- Centralizar logs de orquestração nos use-cases para melhor rastreabilidade.
- Futuro: integrar com ferramentas de APM (Prometheus/Grafana, Elastic, Sentry).

Security (visão inicial)
- Autenticação/Autorização: JWT ou OAuth2 (planejado).
- Sanitização e validação de inputs: importante evitar vazamento de dados e injeções.
- Ponto de encontro sugerido: redigir regras de privacidade e recomendações de segurança para encontros.

Contribuindo
- Leia o arquivo BRANCH_AND_COMMIT_GUIDELINES.md para convenções de branches e commits.
- Workflow sugerido:
    1. Criar issue descrevendo a feature/bug (se ainda não existir).
    2. Criar branch: `feature/<ISSUE>-<descrição-curta>`.
    3. Implementar seguindo Clean/Hexagonal; escrever testes.
    4. Abrir PR com descrição, checklist e exemplos de teste.
- Checklist mínimo no PR:
    - [ ] Issue vinculada
    - [ ] Testes unitários adicionados
    - [ ] Testes de integração quando aplicável
    - [ ] Documentação atualizada (endpoints/README)

Roadmap / Ideias futuras
- Verificação de usuário (e-mail / telefone)
- Integração com mapas para sugerir pontos de encontro seguros
- Sistema de reputação / avaliações
- Notificações (e-mail, push)
- Suporte a catálogo público de lojas / parcerias
- Mobile app / frontend público

Arquivos úteis no repositório
- `common-feature-structure.sh` e `common-feature-structure.ps1` — scripts para iniciar a estrutura de uma nova feature.
- `BRANCH_AND_COMMIT_GUIDELINES.md` — convenções de branches e commits (veja para padrões de contribuição).

Licença
- (Escolha a licença que preferir, ex.: MIT, Apache-2.0). Coloque um arquivo `LICENSE` no repositório.

Contato
- Autor / Maintainer: @dacruuz (GitHub)
- Para dúvidas, abra uma issue ou PR no repositório.