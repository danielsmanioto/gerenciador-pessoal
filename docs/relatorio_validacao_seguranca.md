# Relatório de Validação de Segurança (via agente `.github/agents/valida_seguranca.agent.md`)

Data: 2026-03-07
Escopo: análise estática do código-fonte (Spring Boot MVC + Spring Security)
Referência: OWASP API Security Top 10 + checklist do agente

## Resumo Executivo

Foram identificados riscos relevantes de autenticação/controle de acesso e exposição de segredos em configuração/documentação.

- Críticos: 0
- Altos: 2
- Médios: 3
- Baixos: 1

---

## Achados

### [SEV-001] Ausência de autorização por função (BFLA)
**Severidade:** 🟠 Alta

**Evidência:**
- Regra global apenas exige usuário autenticado: [src/main/java/com/danielsmanioto/gerenciadorpessoal/config/SecurityConfig.java](../src/main/java/com/danielsmanioto/gerenciadorpessoal/config/SecurityConfig.java#L32)
- Não há restrição por perfil nas rotas de negócio: [src/main/java/com/danielsmanioto/gerenciadorpessoal/controller/CentroCustoController.java](../src/main/java/com/danielsmanioto/gerenciadorpessoal/controller/CentroCustoController.java#L13), [src/main/java/com/danielsmanioto/gerenciadorpessoal/controller/ContasPagarController.java](../src/main/java/com/danielsmanioto/gerenciadorpessoal/controller/ContasPagarController.java#L14)

**Impacto:** qualquer usuário autenticado pode executar operações sensíveis de criação/edição/exclusão.

**Recomendação:** aplicar `hasRole('ADMIN')`/`@PreAuthorize` por endpoint sensível e separar permissões por função.

---

### [SEV-002] Possível BOLA/IDOR em edição e exclusão por ID
**Severidade:** 🟠 Alta

**Evidência:**
- Edição por ID: [src/main/java/com/danielsmanioto/gerenciadorpessoal/controller/ContasPagarController.java](../src/main/java/com/danielsmanioto/gerenciadorpessoal/controller/ContasPagarController.java#L39), [src/main/java/com/danielsmanioto/gerenciadorpessoal/controller/CentroCustoController.java](../src/main/java/com/danielsmanioto/gerenciadorpessoal/controller/CentroCustoController.java#L34)
- Exclusão por ID: [src/main/java/com/danielsmanioto/gerenciadorpessoal/controller/ContasPagarController.java](../src/main/java/com/danielsmanioto/gerenciadorpessoal/controller/ContasPagarController.java#L58), [src/main/java/com/danielsmanioto/gerenciadorpessoal/controller/CentroCustoController.java](../src/main/java/com/danielsmanioto/gerenciadorpessoal/controller/CentroCustoController.java#L49)

**Impacto:** manipulação de IDs pode permitir acesso/alteração de registros não pertencentes ao usuário.

**Recomendação:** validar ownership no serviço/repositório (filtro por usuário autenticado) antes de retornar/alterar/excluir.

---

### [SEV-003] Credenciais e segredo em código/configuração
**Severidade:** 🟡 Média

**Evidência:**
- Senha do banco em texto claro: [src/main/resources/application.properties](../src/main/resources/application.properties#L6)
- Credencial padrão exposta em tela: [src/main/resources/templates/login.html](../src/main/resources/templates/login.html#L36)
- Credencial padrão documentada: [README.md](../README.md#L105), [README.md](../README.md#L108)

**Impacto:** facilita acesso indevido e movimentação lateral em ambiente comprometido.

**Recomendação:** usar variáveis de ambiente/secret manager e remover credenciais padrão de documentação/tela.

---

### [SEV-004] Método HTTP inseguro para exclusão (GET)
**Severidade:** 🟡 Média

**Evidência:**
- Exclusão com `@GetMapping`: [src/main/java/com/danielsmanioto/gerenciadorpessoal/controller/CentroCustoController.java](../src/main/java/com/danielsmanioto/gerenciadorpessoal/controller/CentroCustoController.java#L49), [src/main/java/com/danielsmanioto/gerenciadorpessoal/controller/ContasPagarController.java](../src/main/java/com/danielsmanioto/gerenciadorpessoal/controller/ContasPagarController.java#L58)

**Impacto:** risco de acionamento acidental/cross-site (links, prefetch, crawlers), além de semântica HTTP inadequada.

**Recomendação:** trocar para `POST`/`DELETE` com proteção CSRF e confirmação explícita.

---

### [SEV-005] Risco de Mass Assignment em bind de entidade
**Severidade:** 🟡 Média

**Evidência:**
- Binding direto de entidade em `@ModelAttribute`: [src/main/java/com/danielsmanioto/gerenciadorpessoal/controller/ContasPagarController.java](../src/main/java/com/danielsmanioto/gerenciadorpessoal/controller/ContasPagarController.java#L48), [src/main/java/com/danielsmanioto/gerenciadorpessoal/controller/CentroCustoController.java](../src/main/java/com/danielsmanioto/gerenciadorpessoal/controller/CentroCustoController.java#L41)
- Campos de ID no formulário: [src/main/resources/templates/contas-pagar/form.html](../src/main/resources/templates/contas-pagar/form.html#L31), [src/main/resources/templates/centro-custo/form.html](../src/main/resources/templates/centro-custo/form.html#L31)

**Impacto:** parâmetros extras podem tentar sobrescrever campos internos/relacionamentos.

**Recomendação:** adotar DTOs de entrada + mapeamento explícito de campos permitidos.

---

### [SEV-006] Logging de segurança em nível DEBUG
**Severidade:** 🔵 Baixa

**Evidência:**
- [src/main/resources/application.properties](../src/main/resources/application.properties#L25)

**Impacto:** excesso de detalhes sensíveis em logs quando executado fora de ambiente local.

**Recomendação:** usar `INFO`/`WARN` em produção e mascarar dados sensíveis.

---

## Itens do checklist sem evidência de implementação

- Rate limiting/anti brute-force em login
- Política de lockout progressivo
- Regras explícitas de security headers adicionais (ex.: CSP customizada)

Observação: ausência de evidência no código revisado; recomenda-se teste dinâmico (ZAP/Burp) para confirmação.

## Próximos passos sugeridos

1. Corrigir autorização por perfil e ownership (BFLA/BOLA).
2. Remover credenciais hardcoded e rotacionar segredos.
3. Substituir exclusões `GET` por `POST/DELETE`.
4. Introduzir DTOs e validação defensiva de binding.
5. Implementar rate limiting no fluxo de login.
