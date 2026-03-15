# 💰 Gerenciador Pessoal e Financeiro

> Sistema monolítico em Java para gerenciamento pessoal e financeiro com controle de contas a pagar.

[![Java](https://img.shields.io/badge/Java-25-007396?logo=openjdk&logoColor=white)](https://openjdk.org/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5.7-6DB33F?logo=springboot&logoColor=white)](https://spring.io/projects/spring-boot)
[![PostgreSQL](https://img.shields.io/badge/PostgreSQL-16-336791?logo=postgresql&logoColor=white)](https://www.postgresql.org/)
[![Maven](https://img.shields.io/badge/Maven-3.6%2B-C71A36?logo=apachemaven&logoColor=white)](https://maven.apache.org/)
[![Docker](https://img.shields.io/badge/Docker-Compose-2496ED?logo=docker&logoColor=white)](https://docs.docker.com/compose/)
[![Kafka](https://img.shields.io/badge/Apache-Kafka-231F20?logo=apachekafka&logoColor=white)](https://kafka.apache.org/)
[![Architecture](https://img.shields.io/badge/Architecture-Monolith%20%2B%20Async-blueviolet)](#)

---

## 📑 Sumário

- [Arquitetura](#-arquitetura)
- [Visão Geral do Ecossistema](#-visão-geral-do-ecossistema)
- [Objetivo do Repositório](#-objetivo-do-repositório)
- [Projetos Relacionados](#-projetos-relacionados)
- [Tecnologias](#-tecnologias)
- [Funcionalidades](#-funcionalidades)
- [Modelo de Dados](#-modelo-de-dados)
- [Pré-requisitos](#-pré-requisitos)
- [Instalação e Execução](#-instalação-e-execução)
- [Credenciais Padrão](#-credenciais-padrão)
- [Estrutura do Projeto](#-estrutura-do-projeto)
- [Dados de Exemplo](#-dados-de-exemplo)
- [Comandos Úteis](#-comandos-úteis)
- [Screenshots](#-screenshots)
- [Configuração](#-configuração)
- [Scripts de Teste](#-scripts-de-teste)

---

## 🏗 Arquitetura

<img width="1181" height="141" alt="Desenho de arquitetura" src="https://github.com/user-attachments/assets/3acab026-c660-42dc-aa68-f42fcca0adf1" />

---

## 🌐 Visão Geral do Ecossistema

A solução é composta por quatro frentes principais que se integram para suportar o gerenciamento financeiro e a troca assíncrona de dados:

- **Aplicação Web Monolítica** para cadastro e gestão de contas a pagar
- **Producer Kafka em Python** para publicação de eventos
- **Consumer Kafka em Java** para consumo e persistência dos dados
- **Infraestrutura Centralizada** para Kafka, Zookeeper e bancos PostgreSQL

A comunicação entre os sistemas ocorre de forma assíncrona com **Apache Kafka**, promovendo desacoplamento e escalabilidade.

### Desenho complementar

<img width="1522" height="727" alt="Desenho complementar da solução" src="https://github.com/user-attachments/assets/9178fb39-6070-4565-a55a-b884d8ea737a" />

---

## 🎯 Objetivo do Repositório

Este repositório tem como objetivo:

- Estudar e praticar **integração assíncrona com Kafka**
- Aplicar conceitos de **arquitetura de sistemas**
- Simular um cenário real de comunicação entre serviços
- Servir como base de aprendizado e evolução futura

Também há espaço para evoluções futuras, como:

- Evolução da infraestrutura compartilhada
- Orquestração com Docker Compose centralizado
- Observabilidade e monitoramento
- Separação futura em microserviços

---

## 🔗 Projetos Relacionados

| Projeto | Repositório |
|---|---|
| Gerenciador Pessoal | [danielsmanioto/gerenciador-pessoal](https://github.com/danielsmanioto/gerenciador-pessoal) |
| Kafka Producer – Contas a Pagar | [danielsmanioto/kafka-producer-contas-pagar](https://github.com/danielsmanioto/kafka-producer-contas-pagar) |
| Kafka Consumer – Contas a Pagar | [danielsmanioto/kafka-consumer-contas-pagar](https://github.com/danielsmanioto/kafka-consumer-contas-pagar) |
| Infraestrutura Centralizada | [danielsmanioto/infra-gerenciador-pessoal](https://github.com/danielsmanioto/infra-gerenciador-pessoal) |

---

## 🛠 Tecnologias

| Tecnologia | Descrição |
|---|---|
| **Java 25** | Linguagem principal |
| **Spring Boot** | Framework base da aplicação |
| **Spring Security** | Autenticação e Autorização |
| **Spring Data JPA** | Camada de persistência |
| **PostgreSQL** | Banco de dados relacional |
| **Flyway** | Migração e versionamento do banco de dados |
| **Thymeleaf** | Template engine para frontend |
| **Docker / Docker Compose** | Execução da infraestrutura local centralizada |
| **Maven** | Gerenciamento de dependências e build |

---

## ✅ Funcionalidades

- ✅ Autenticação e autorização de usuários
- ✅ Gerenciamento de Centros de Custo
- ✅ Gerenciamento de Contas a Pagar com:
  - Centro de custo
  - Valor previsto e valor pago
  - Data da conta
  - Status (`PENDENTE`, `PAGO`, `VENCIDO`, `CANCELADO`)
  - Informações de pagamento
- ✅ Interface web responsiva e moderna

---

## 🗄 Modelo de Dados

### Usuário
| Campo | Tipo | Observação |
|---|---|---|
| ID | Long | Chave primária |
| Username | String | Único |
| Password | String | Criptografada com BCrypt |
| Nome | String | — |
| Ativo | boolean | — |
| Roles | Set | `ROLE_ADMIN`, etc. |

### Centro de Custo
| Campo | Tipo | Observação |
|---|---|---|
| ID | Long | Chave primária |
| Descrição | String | — |

### Contas a Pagar
| Campo | Tipo | Observação |
|---|---|---|
| ID | Long | Chave primária |
| Centro de Custo | FK | — |
| Valor Previsto | BigDecimal | — |
| Valor Pago | BigDecimal | — |
| Data da Conta | LocalDate | — |
| Informação de Pagamento | String | — |
| Status | Enum | `PENDENTE`, `PAGO`, `VENCIDO`, `CANCELADO` |
| Usuário | FK | Opcional |

---

## 📋 Pré-requisitos

- Java 17 ou superior
- Maven 3.6+
- Docker e Docker Compose

---

## 🚀 Instalação e Execução

### 1. Clone os repositórios necessários

```bash
git clone https://github.com/danielsmanioto/gerenciador-pessoal.git
git clone https://github.com/danielsmanioto/infra-gerenciador-pessoal.git
```

### 2. Inicie a infraestrutura centralizada

```bash
cd infra-gerenciador-pessoal
docker compose up -d
```

> Este comando irá:
> - Subir o Zookeeper na porta `2181`
> - Subir o Kafka na porta `9092`
> - Criar o banco `gerenciador_pessoal` na porta `5432`
> - Criar o banco `contaspagar` na porta `5433`

### 3. Compile o projeto

```bash
cd ../gerenciador-pessoal
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

---

## 🔑 Credenciais Padrão

| Campo | Valor |
|---|---|
| **Usuário** | `admin` |
| **Senha** | `admin123` |

> ⚠️ **Atenção:** Altere as credenciais padrão antes de utilizar em ambiente de produção.

---

## 📂 Estrutura do Projeto

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

---

## 🌱 Dados de Exemplo

O sistema é inicializado com os seguintes centros de custo:

| # | Centro de Custo |
|---|---|
| 1 | Alimentação |
| 2 | Transporte |
| 3 | Moradia |
| 4 | Saúde |
| 5 | Educação |
| 6 | Lazer |
| 7 | Investimentos |
| 8 | Outros |

---

## 🧰 Comandos Úteis

| Ação | Comando |
|---|---|
| Parar a infraestrutura | `cd ../infra-gerenciador-pessoal && docker compose down` |
| Resetar a infraestrutura | `cd ../infra-gerenciador-pessoal && docker compose down -v` |
| Ver logs do PostgreSQL principal | `cd ../infra-gerenciador-pessoal && docker compose logs -f postgres-gerenciador` |
| Ver logs do Kafka | `cd ../infra-gerenciador-pessoal && docker compose logs -f kafka` |
| Recompilar sem executar testes | `mvn clean package -DskipTests` |

---

## 🖼 Screenshots

### Tela de Login
![Login](https://github.com/user-attachments/assets/33a14dfa-e41c-46db-80ca-44b1e5d9ec18)

### Dashboard
![Dashboard](https://github.com/user-attachments/assets/491a1a71-31a7-4e73-9668-cf3f85019b91)

### Centros de Custo
![Centros de Custo](https://github.com/user-attachments/assets/d5c06dfe-4477-4079-8dcd-c2ec67230b8f)

### Formulário de Contas a Pagar
![Contas a Pagar](https://github.com/user-attachments/assets/c674fe4d-e000-44ae-af1a-2f140463540e)

---

## ⚙️ Configuração

As configurações principais estão em `src/main/resources/application.properties`:

```properties
# Porta do servidor
server.port=8080

# Configuração do banco de dados
spring.datasource.url=${GERENCIADOR_DB_URL:jdbc:postgresql://localhost:5432/gerenciador_pessoal}
spring.datasource.username=${GERENCIADOR_DB_USERNAME:gerenciador}
spring.datasource.password=${GERENCIADOR_DB_PASSWORD:gerenciador123}
```

> A infraestrutura local é mantida no repositório [danielsmanioto/infra-gerenciador-pessoal](https://github.com/danielsmanioto/infra-gerenciador-pessoal).

---

## 🧪 Scripts de Teste

### Inserindo 50.000 usuários de teste no PostgreSQL

```sql
INSERT INTO usuarios (username, password, nome, ativo)
SELECT
    'lalateste' || gs AS username,
    '$2b$12$kqAST3ZXicZkhnfGd9NwtujojsTZrBg9oJW0gduAMIAWqu4kVtI7K' AS password,
    'Administrador' AS nome,
    true AS ativo
FROM generate_series(1, 50000) AS gs;
```
