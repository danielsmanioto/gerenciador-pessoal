# Gerenciador Pessoal e Financeiro

Sistema monolítico em Java para gerenciamento pessoal e financeiro com controle de contas a pagar.

## Tecnologias

- **Java 17**
- **Spring Boot 3.2.1**
- **Spring Security** (Autenticação e Autorização)
- **Spring Data JPA** (Persistência)
- **PostgreSQL** (Banco de dados)
- **Flyway** (Migração de banco de dados)
- **Thymeleaf** (Template engine para frontend)
- **Docker Compose** (Containerização do PostgreSQL)
- **Maven** (Gerenciamento de dependências)

## Funcionalidades

- ✅ Autenticação e autorização de usuários
- ✅ Gerenciamento de Centros de Custo
- ✅ Gerenciamento de Contas a Pagar com:
  - Centro de custo
  - Valor previsto e valor pago
  - Data da conta
  - Status (PENDENTE, PAGO, VENCIDO, CANCELADO)
  - Informações de pagamento
- ✅ Interface web responsiva e moderna

## Modelo de Dados

### Usuário
- ID
- Username (único)
- Password (criptografada com BCrypt)
- Nome
- Ativo (boolean)
- Roles (ROLE_ADMIN, etc.)

### Centro de Custo
- ID
- Descrição

### Contas a Pagar
- ID
- Centro de Custo (FK)
- Valor Previsto
- Valor Pago
- Data da Conta
- Informação de Pagamento
- Status
- Usuário (FK, opcional)

## Pré-requisitos

- Java 17 ou superior
- Maven 3.6+
- Docker e Docker Compose

## Instalação e Execução

### 1. Clone o repositório

```bash
git clone https://github.com/danielsmanioto/gerenciador-pessoal.git
cd gerenciador-pessoal
```

### 2. Inicie o banco de dados PostgreSQL

```bash
docker compose up -d
```

Este comando irá:
- Baixar a imagem do PostgreSQL 16
- Criar um container com o banco de dados
- Configurar o banco `gerenciador_pessoal` com usuário `gerenciador` e senha `gerenciador123`
- Expor a porta 5432

### 3. Compile o projeto

```bash
mvn clean package
```

### 4. Execute a aplicação

```bash
java -jar target/gerenciador-pessoal-1.0.0-SNAPSHOT.jar
```

Ou usando Maven:

```bash
mvn spring-boot:run
```

### 5. Acesse a aplicação

Abra seu navegador e acesse: [http://localhost:8080](http://localhost:8080)

## Credenciais Padrão

- **Usuário:** admin
- **Senha:** admin123

## Estrutura do Projeto

```
src/
├── main/
│   ├── java/com/danielsmanioto/gerenciadorpessoal/
│   │   ├── config/          # Configurações (Spring Security)
│   │   ├── controller/      # Controllers MVC
│   │   ├── model/           # Entidades JPA
│   │   ├── repository/      # Repositórios Spring Data
│   │   ├── security/        # Serviços de segurança
│   │   └── service/         # Camada de serviços
│   └── resources/
│       ├── db/migration/    # Scripts Flyway
│       ├── static/          # Arquivos estáticos (CSS, JS)
│       ├── templates/       # Templates Thymeleaf
│       └── application.properties
└── test/                    # Testes
```

## Dados de Exemplo

O sistema é inicializado com os seguintes centros de custo:
- Alimentação
- Transporte
- Moradia
- Saúde
- Educação
- Lazer
- Investimentos
- Outros

## Comandos Úteis

### Parar o banco de dados
```bash
docker compose down
```

### Parar e remover volumes (reset completo)
```bash
docker compose down -v
```

### Verificar logs do banco de dados
```bash
docker compose logs -f postgres
```

### Recompilar sem executar testes
```bash
mvn clean package -DskipTests
```

## Screenshots

### Tela de Login
![Login](https://github.com/user-attachments/assets/33a14dfa-e41c-46db-80ca-44b1e5d9ec18)

### Dashboard
![Dashboard](https://github.com/user-attachments/assets/491a1a71-31a7-4e73-9668-cf3f85019b91)

### Centros de Custo
![Centros de Custo](https://github.com/user-attachments/assets/d5c06dfe-4477-4079-8dcd-c2ec67230b8f)

### Formulário de Contas a Pagar
![Contas a Pagar](https://github.com/user-attachments/assets/c674fe4d-e000-44ae-af1a-2f140463540e)

## Configuração

As configurações principais estão em `src/main/resources/application.properties`:

```properties
# Porta do servidor
server.port=8080

# Configuração do banco de dados
spring.datasource.url=jdbc:postgresql://localhost:5432/gerenciador_pessoal
spring.datasource.username=gerenciador
spring.datasource.password=gerenciador123
```

## Licença

Este projeto está sob a licença MIT.
