# 📘 Documentação de Endpoints - Gerenciador Pessoal

Esta documentação cobre os endpoints HTTP do projeto **gerenciador-pessoal**.

> **Importante:** a aplicação é MVC (Thymeleaf), então as respostas são majoritariamente **HTML** e não JSON.

---

## 🌐 Base URL

- Local: `http://localhost:8080`

---

## 🔐 Autenticação e sessão

- Segurança via **Spring Security** com login de formulário.
- Endpoints protegidos exigem sessão autenticada (`JSESSIONID`).
- Requisições `POST` exigem token **CSRF**.

Credencial padrão (ambiente local):

- Usuário: `admin`
- Senha: `admin123`

---

## 🗂 Catálogo de endpoints

| Método | Endpoint | Protegido | Descrição | Retorno típico |
|---|---|---|---|---|
| GET | `/login` | Não | Tela de login | `200` (HTML) |
| POST | `/login` | Não | Processa autenticação (Spring Security) | `302` redirect |
| POST | `/logout` | Sim | Encerra sessão | `302` redirect |
| GET | `/` | Sim | Home da aplicação | `200` (HTML) |
| GET | `/centro-custo` | Sim | Lista centros de custo | `200` (HTML) |
| GET | `/centro-custo/new` | Sim | Formulário novo centro de custo | `200` (HTML) |
| GET | `/centro-custo/{id}/edit` | Sim | Formulário edição centro de custo | `200` (HTML) |
| POST | `/centro-custo` | Sim | Cria/atualiza centro de custo | `302` redirect |
| GET | `/centro-custo/{id}/delete` | Sim | Remove centro de custo | `302` redirect |
| GET | `/contas-pagar` | Sim | Lista contas a pagar | `200` (HTML) |
| GET | `/contas-pagar/new` | Sim | Formulário nova conta | `200` (HTML) |
| GET | `/contas-pagar/{id}/edit` | Sim | Formulário edição conta | `200` (HTML) |
| POST | `/contas-pagar` | Sim | Cria/atualiza conta | `302` redirect |
| GET | `/contas-pagar/{id}/delete` | Sim | Remove conta | `302` redirect |

---

## 🧪 Fluxo completo com cURL (login + ações)

### 1) Abrir login e capturar cookie + CSRF

```bash
curl -s -c cookies.txt "http://localhost:8080/login" -o login.html
CSRF=$(grep -o 'name="_csrf" value="[^"]*"' login.html | sed 's/.*value="//;s/"$//')
echo "$CSRF"
```

### 2) Autenticar sessão

```bash
curl -i -s -b cookies.txt -c cookies.txt \
  -X POST "http://localhost:8080/login" \
  -H "Content-Type: application/x-www-form-urlencoded" \
  --data-urlencode "username=admin" \
  --data-urlencode "password=admin123" \
  --data-urlencode "_csrf=$CSRF"
```

Se autenticado, você receberá `302` para `/`.

### 3) Acessar home protegida

```bash
curl -i -s -b cookies.txt "http://localhost:8080/"
```

---

## 🧩 Exemplos por recurso

### Centro de Custo

#### Listar

```bash
curl -s -b cookies.txt "http://localhost:8080/centro-custo"
```

#### Criar

```bash
curl -s -b cookies.txt "http://localhost:8080/centro-custo/new" -o centro_new.html
CSRF=$(grep -o 'name="_csrf" value="[^"]*"' centro_new.html | sed 's/.*value="//;s/"$//')

curl -i -s -b cookies.txt -c cookies.txt \
  -X POST "http://localhost:8080/centro-custo" \
  -H "Content-Type: application/x-www-form-urlencoded" \
  --data-urlencode "descricao=Infraestrutura" \
  --data-urlencode "_csrf=$CSRF"
```

#### Atualizar (exemplo ID 1)

```bash
curl -s -b cookies.txt "http://localhost:8080/centro-custo/1/edit" -o centro_edit.html
CSRF=$(grep -o 'name="_csrf" value="[^"]*"' centro_edit.html | sed 's/.*value="//;s/"$//')

curl -i -s -b cookies.txt -c cookies.txt \
  -X POST "http://localhost:8080/centro-custo" \
  -H "Content-Type: application/x-www-form-urlencoded" \
  --data-urlencode "id=1" \
  --data-urlencode "descricao=Infraestrutura Atualizada" \
  --data-urlencode "_csrf=$CSRF"
```

#### Excluir (exemplo ID 1)

```bash
curl -i -s -b cookies.txt "http://localhost:8080/centro-custo/1/delete"
```

---

### Contas a Pagar

#### Listar

```bash
curl -s -b cookies.txt "http://localhost:8080/contas-pagar"
```

#### Criar

> Requer `centroCusto.id` existente.

```bash
curl -s -b cookies.txt "http://localhost:8080/contas-pagar/new" -o conta_new.html
CSRF=$(grep -o 'name="_csrf" value="[^"]*"' conta_new.html | sed 's/.*value="//;s/"$//')

curl -i -s -b cookies.txt -c cookies.txt \
  -X POST "http://localhost:8080/contas-pagar" \
  -H "Content-Type: application/x-www-form-urlencoded" \
  --data-urlencode "centroCusto.id=1" \
  --data-urlencode "dataConta=2026-03-15" \
  --data-urlencode "valorPrevisto=1200.00" \
  --data-urlencode "valorPago=0" \
  --data-urlencode "status=PENDENTE" \
  --data-urlencode "informacaoPagamento=Pagamento fornecedor XPTO" \
  --data-urlencode "_csrf=$CSRF"
```

#### Atualizar (exemplo ID 1)

```bash
curl -s -b cookies.txt "http://localhost:8080/contas-pagar/1/edit" -o conta_edit.html
CSRF=$(grep -o 'name="_csrf" value="[^"]*"' conta_edit.html | sed 's/.*value="//;s/"$//')

curl -i -s -b cookies.txt -c cookies.txt \
  -X POST "http://localhost:8080/contas-pagar" \
  -H "Content-Type: application/x-www-form-urlencoded" \
  --data-urlencode "id=1" \
  --data-urlencode "centroCusto.id=1" \
  --data-urlencode "dataConta=2026-03-16" \
  --data-urlencode "valorPrevisto=1200.00" \
  --data-urlencode "valorPago=1200.00" \
  --data-urlencode "status=PAGO" \
  --data-urlencode "informacaoPagamento=Pago via TED" \
  --data-urlencode "_csrf=$CSRF"
```

#### Excluir (exemplo ID 1)

```bash
curl -i -s -b cookies.txt "http://localhost:8080/contas-pagar/1/delete"
```

---

## ⚠️ Observações importantes

1. **CSRF obrigatório em POST**: sem `_csrf` o Spring Security retorna erro 403.
2. **Endpoints de delete são GET**: funcional no sistema atual, mas não é prática REST recomendada para produção.
3. **Retornos de POST**: geralmente `302` com redirect para listagem.
4. **Conteúdo HTML**: como é MVC, use `curl -i` para inspecionar status e headers.

---

## 📚 Referências rápidas no código

- Controllers: `src/main/java/.../controller`
- Segurança: `src/main/java/.../config/SecurityConfig.java`
- Templates: `src/main/resources/templates`
