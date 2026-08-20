# Guia: criação de arquivos para um endpoint

Este documento descreve, de forma prática e objetiva, o propósito, convenções e requisitos mínimos para cada tipo de arquivo criado ao implementar um endpoint seguindo a arquitetura Hexagonal / Clean adotada no projeto. Não contém exemplos de código — apenas regras, expectativas e checklist.

## Sumário
- Objetivo geral
- Convenções transversais
- DTOs (request / response)
- Controller (adaptador de entrada)
- Ports (in / out)
- UseCase (aplicação / regras de negócio)
- Adapter (integrações externas)
- Repository (persistência relacional)
- Mapper (MapStruct)
- Testes
- Mapeamento de erros e exceções
- Checklist final para PR

## Objetivo geral
- Padronizar criação de artefatos para novos endpoints de forma que cada camada tenha responsabilidade única, facilidades para teste e clareza de contratos entre camadas.

## Convenções transversais
- Organização de pacotes: manter separação por camadas (api.domain, api.application, api.shared, infrastructure).
- Nomeação: [Feature][Tipo] — ex.: BookController, CreateBookUseCase, BookRequest, BookRepositoryPort.
- Injeção: usar constructor injection (com Lombok: @RequiredArgsConstructor) — evitar field injection e setters públicos desnecessários.
- Imutabilidade: preferir objetos imutáveis em DTOs/VOs quando apropriado.
- Documentação: cada arquivo novo deve ter um breve comentário Javadoc/README interno referenciando o comportamento esperado.
- Transações: aplicar @Transactional na camada de aplicação/use-case quando houver operações que alteram o estado persistente.

## DTOs (request e response)
- Propósito:
    - Request DTO: representar o contrato de entrada HTTP; validação de formato e presença de campos.
    - Response DTO: representar o contrato de saída ao cliente; proteger o domínio (não retornar entidades de domínio direto).
- Padrões:
    - Implementar como records (quando possível) para concisão e imutabilidade.
    - Validar via Bean Validation (ex.: @NotBlank, @Size, @Pattern, @DecimalMin).
    - O Controller deve receber @Valid no request.
- Onde ficar:
    - Pacote de application (ex.: api.application.dto) ou pacote específico de feature dentro de api.application.
- O que documentar:
    - Campos obrigatórios, formatos esperados, limites (tamanhos), regras especiais (normalização).
- Observações:
    - Não colocar lógica de negócio nos DTOs; conversões para objetos de domínio devem ser feitas por mappers.

## Controller (adaptador de entrada)
- Propósito:
    - Adaptador que converte requisições HTTP em chamadas para a porta de entrada (port in / use-case) e converte respostas para HTTP.
- Padrões:
    - Estereótipos: @RestController / @Controller + @RequestMapping.
    - Injeção via construtor da porta de entrada (port in).
    - Uso de @Validated/@Valid para acionamento das validações de DTOs.
    - Deve ser _thin_: orquestrar validação, mapeamento request→input, chamar use-case e montar ResponseEntity com códigos apropriados (201, 200, 204, 400, 404, 409).
- Erros e exceções:
    - Não tratar regras de negócio no controller; transformar exceções em respostas HTTP via ControllerAdvice centralizado.
- Observações:
    - Preencher Location header ao criar recursos (201 Created).
    - Não expor entidades de domínio diretamente.

## Ports (in e out)
- Propósito:
    - Ports in (driving ports): interfaces que definem o contrato que a aplicação expõe para adaptadores de entrada (controllers).
    - Ports out (driven ports): interfaces que definem o contrato que a aplicação espera de adaptadores externos (banco, APIs, mensageria).
- Padrões:
    - Colocar as interfaces nas camadas api.application (ou api.domain quando for dependência natural do domínio).
    - Nomeação clara e orientada a ação: ex.: CreateBookPort, BookRepositoryPort.
    - Assinaturas simples e explícitas: usar DTOs de aplicação ou objetos de domínio como parâmetros/retorno; documentar comportamento e exceções esperadas.
- Observações:
    - Ports não devem depender de frameworks; devem ser puras e focadas na finalidade.

## UseCase (aplicação / regras de negócio)
- Propósito:
    - Implementar a orquestração das regras de negócio para um caso de uso específico; coordenar chamadas a ports out.
- Padrões:
    - Colocar em api.application.service (ou similar).
    - Estereótipos: @Service (ou @Component) e @RequiredArgsConstructor.
    - Aplicar @Transactional na camada do use-case quando alterações em banco ocorrerem; documentar razão para transação e seu escopo.
    - Use-cases devem receber objetos de input (DTOs de aplicação ou mappers convertidos) e retornar DTOs de saída ou identificadores.
- Responsabilidades:
    - Validar regras de negócio que não são mera validação de formato.
    - Lançar exceções de domínio específicas que serão mapeadas pelo ControllerAdvice.
    - Não depender de detalhes de persistência ou de HTTP.
- Observações:
    - Evitar lógica que pertença ao domínio nas camadas de infraestrutura; manter domínio e regras no centro.

## Adapter (integrações externas)
- Propósito:
    - Implementar as ports out para conectar-se a sistemas externos (APIs, filas, serviços internos).
- Padrões:
    - Localização: api/application/<feature>/adapters/out.
    - Injeção: constructor injection de clientes/beans de infra.
    - Aspectos não-funcionais: definir políticas de retry, timeouts, bulkheads/circuit-breakers quando aplicável; não replicar lógica de negócio.
- Testabilidade:
    - Projetar para ser facilmente mockável em testes (injetar clientes via interfaces).
- Observações:
    - Registrar e monitorar falhas e latências; mapear exceções de cliente para exceções internas tratáveis.

## Repository (persistência relacional)
- Propósito:
    - Implementar persistência relacional para entidades de domínio.
- Padrões:
    - Preferir interfaces Spring Data (extends JpaRepository) quando possível para operações CRUD básicas.
    - Se for implementação custom (por exemplo, consultas complexas), criar uma implementação em api/application/<feature>/adapters/out/repositories que realize o mapeamento Entity ↔ Domain.
    - Usar @Repository e constructor injection para dependências.
- Transações:
    - A camada de aplicação (use-case) gerencia transações; repository não deve tentar gerenciar transações na maior parte dos casos.
- Observações:
    - Definir constraints e índices no banco (migrations) e refletir regras como unicidade (ex.: ISBN unique) na modelagem.
    - Traduzir exceções de infra (ex.: DataIntegrityViolationException) para exceções de domínio quando for adequado.

## Mapper (MapStruct)
- Propósito:
    - Isolar conversões entre Request DTOs, Domain objects, Entities e Response DTOs.
- Padrões:
    - Usar MapStruct com componentModel = "spring" para permitir injeção automática nos UseCases/Adapters.
    - Definir mapas explícitos para campos com transformações (datas, formatos, normalizações).
    - Manter mappers pequenos e focados por feature (ex.: BookMapper).
- Observações:
    - Documentar transformações especiais (normalização de strings, formatação de datas, cálculo de campos derivados).
    - Para campos que precisam de lógica de conversão complexa, delegar a métodos helper ou serviços específicos e referenciá-los no mapper via @Mapping(expression=...) ou métodos utilitários.
    - MapStruct facilita testes de mapeamento automáticos (testar conversões).

## Testes
- Unitários:
    - UseCase: testar regras de negócio isoladas, mockando ports out.
    - Mappers: testar conversões fundamentais se houver lógica complexa.
- Controller:
    - Testes com MockMvc/WebTestClient para validar mapeamento HTTP ↔ DTOs, validações e respostas.
- Integração:
    - Repositories/Adapters: testes de integração contra banco em memória (H2) com as migrations aplicadas.
    - Para adapters externos (HTTP), usar stubs/mocks (WireMock) em testes de integração.
- Critério:
    - Cobertura mínima nos pontos críticos da lógica de negócio e garantias sobre integridade de dados.

## Mapeamento de erros e exceções
- Regra:
    - Exceções de validação de entrada → 400 Bad Request (automático via Bean Validation).
    - Exceções de negócio (DomainException) → status definido na exceção ou mapeado via ControllerAdvice (ex.: 404, 409, 422).
    - Exceções de infraestrutura (DB, timeout externo) → mapear para 5xx ou traduzir para exceções de domínio quando fizer sentido.
- Centralizar mapeamento em um ControllerAdvice global que converta exceções em payloads previsíveis (body com código, mensagem e detalhes).

## Checklist final (o que deve acompanhar cada PR que adiciona um endpoint)
- [ ] Issue vinculada com escopo e critérios de aceite
- [ ] Branch nomeada conforme convenção feature/<numero>-<descricao>
- [ ] DTOs (request/response) criados e documentados (records + Bean Validation)
- [ ] Port in / Port out definidas e documentadas (assinaturas + contratos)
- [ ] UseCase implementado e marcado @Transactional quando necessário
- [ ] Controller criado como adaptador de entrada, usando constructor injection e @Valid
- [ ] Adapter(s) e Repository implementados em infrastructure (mapeamento Entity ↔ Domain)
- [ ] Mapper MapStruct criado e referenciado (documentar transformações)
- [ ] Migrations criadas/atualizadas (se houver alteração no schema)
- [ ] Testes unitários e de integração adicionados
- [ ] Documentação do endpoint e dos arquivos adicionada em docs/endpoints/
- [ ] Logs/monitoramento e tratamento de erros implementados
- [ ] Nenhum vazamento de entidade de domínio direto para a camada web

## Notas finais / riscos conhecidos
- Validação em records: confirmar a versão do Hibernate Validator usada no projeto para garantir suporte completo a validação sobre records.
- Lombok e JPA: ao usar Lombok em entidades JPA, preferir combinações seguras (construtor protegido, getters/setters controlados) para evitar problemas com proxies do JPA.
- Transações: preferir transações na camada de aplicação e documentar o escopo (evitar aninhar transações em infra).
- MapStruct: quando usar expressões ou métodos auxiliares dentro do mapper, documentar claramente a presença de lógica que precise ser testada.