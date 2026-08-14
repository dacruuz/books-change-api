# Padrão Normalizer (TextNormalizer + Normalizers por feature)

## Propósito
Garantir consistência e padronização dos dados persistidos (trim, uppercase/lowercase, normalização de CPF/CNPJ/telefone/CEP, slug, etc.).

## Elementos
- `TextNormalizer`: serviço central com métodos reutilizáveis (`normalizeToUpperCase`, `normalizeCpf`, `normalizePhone`, `normalizeZipCode`, `normalizeToLowerCase`, `capitalize` etc.).
- Normalizers por feature: componentes por domínio (ex.: `BookNormalizer`, `StoreNormalizer`, `AddressNormalizer`) que recebem o domain object e aplicam as chamadas do `TextNormalizer` nos campos relevantes.

## Onde aplicar
- Executar no UseCase, após mapeamento do request para entidade/domain e antes de persistir.
- Não fazer normalização nos DTOs de entrada — DTOs cuidam de validação de formato, não de normalização.

## Regras e responsabilidades
- `Normalizer`: apenas transformar dados (não validar regras complexas).
- `Validator`: validar (por exemplo, comprimento de CEP) antes da normalização quando necessário.
- Normalização deve ser idempotente e bem definida (documentar quais campos são normalizados e como).

## Boas práticas / atenção
- Evitar normalizações que percam informação sem justificativa (ex.: truncamento de campos).
- Logar alterações relevantes quando a normalização alterar dados críticos.
- Testar normalizer isoladamente com variações de entrada.

## Testes recomendados
- Unit tests do `TextNormalizer` cobrindo todas transformações.
- Unit tests dos Normalizers por feature garantindo que os campos do domain object foram normalizados corretamente.
- Integration test que verifica que os dados persistidos no DB seguem o padrão esperado após o UseCase.
