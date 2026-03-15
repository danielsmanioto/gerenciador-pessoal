
# Projetos – Gerenciador Pessoal de Contas a Pagar

Este repositório reúne projetos relacionados a um **sistema de gerenciamento pessoal de contas a pagar**, com foco em organização financeira mensal e integração via **Apache Kafka**.

A solução é composta por uma aplicação principal (monolítica) e serviços auxiliares responsáveis pela comunicação assíncrona entre sistemas.

---

## Projetos Relacionados

| Projeto | Repositório |
|---|---|
| Gerenciador Pessoal | [danielsmanioto/gerenciador-pessoal](https://github.com/danielsmanioto/gerenciador-pessoal) |
| Kafka Producer – Contas a Pagar | [danielsmanioto/kafka-producer-contas-pagar](https://github.com/danielsmanioto/kafka-producer-contas-pagar) |
| Kafka Consumer – Contas a Pagar | [danielsmanioto/kafka-consumer-contas-pagar](https://github.com/danielsmanioto/kafka-consumer-contas-pagar) |

---

## Visão Geral da Arquitetura

A arquitetura é dividida em três projetos principais:

- **Aplicação Web Monolítica** para gerenciamento de contas a pagar
- **Producer Kafka em Python** para publicação de eventos
- **Consumer Kafka em Java** para consumo e persistência dos dados

A comunicação entre os sistemas ocorre de forma assíncrona utilizando **Apache Kafka**, garantindo desacoplamento e escalabilidade.

---

## Projetos

### 1️⃣ Gerenciador Pessoal de Contas a Pagar

Aplicação **monolítica** com interface web, responsável pelo cadastro e gerenciamento de contas a pagar do mês.

**Características:**
- Interface Web
- Cadastro de contas a pagar mensais
- Persistência em **banco de dados relacional**
- Arquitetura monolítica, simples e objetiva

**Objetivo:**
Facilitar o controle financeiro pessoal, permitindo visualizar, cadastrar e gerenciar despesas mensais de forma centralizada.

---

### 2️⃣ Kafka Producer – Contas a Pagar

Serviço desenvolvido em **Python**, responsável por **produzir mensagens** no Apache Kafka relacionadas às contas a pagar.

**Responsabilidades:**
- Publicar eventos de contas a pagar no Kafka
- Garantir padronização das mensagens
- Atuar como ponto de integração assíncrona entre sistemas

**Tecnologias:**
- Python
- Apache Kafka

---

### 3️⃣ Kafka Consumer – Persistência de Dados

Serviço desenvolvido em **Java**, responsável por **consumir mensagens** do Kafka e persisti-las no banco de dados relacional.

**Responsabilidades:**
- Consumir eventos de contas a pagar
- Processar e validar mensagens
- Inserir dados no banco de dados
- Garantir consistência e confiabilidade no consumo

**Tecnologias:**
- Java
- Apache Kafka
- Banco de dados relacional

---

## Tecnologias Utilizadas

- **Apache Kafka**
- **Python**
- **Java**
- **Banco de Dados Relacional**
- **Interface Web**

---

## Objetivo do Repositório

Este repositório tem como objetivo:
- Estudar e praticar **integração assíncrona com Kafka**
- Aplicar conceitos de **arquitetura de sistemas**
- Simular um cenário real de comunicação entre serviços
- Servir como base de aprendizado e evolução futura

---

## Observações

Este projeto está em constante evolução e pode receber melhorias como:
- Containerização com Docker
- Orquestração com Docker Compose
- Observabilidade e monitoramento
- Separação futura em microserviços

## Desenho
<img width="1522" height="727" alt="image" src="https://github.com/user-attachments/assets/9178fb39-6070-4565-a55a-b884d8ea737a" />

