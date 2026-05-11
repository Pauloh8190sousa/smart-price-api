# Smart Price API

API REST desenvolvida para centralizar, comparar e monitorar preços de produtos em diferentes lojas, permitindo consultas rápidas, histórico de preços e futuras integrações com alertas e monitoramento automatizado.

O projeto foi criado com foco em arquitetura moderna backend Java, boas práticas de desenvolvimento e escalabilidade.

---

# Objetivo do Projeto

O Smart Price API tem como objetivo fornecer uma estrutura robusta para:

- Comparação de preços entre lojas
- Centralização de produtos
- Histórico de preços
- Monitoramento de alterações de preço
- Base para alertas de preço
- Integração futura com crawlers/scrapers
- Estudos de arquitetura backend moderna com Java e Spring

---

# Tecnologias Utilizadas

## Backend

- Java 21
- Spring Boot 3
- Spring Web
- Spring Data JPA
- Spring Security
- Bean Validation
- Hibernate ORM

## Banco de Dados

- PostgreSQL 18
- HikariCP

## Build & Dependências

- Maven

## Ferramentas

- IntelliJ IDEA
- pgAdmin 4
- Postman
- Git & GitHub

## Futuras Implementações

- Flyway
- JWT Authentication
- Swagger/OpenAPI
- Docker
- Testcontainers
- CI/CD
- Redis Cache
- Integração com APIs externas

---

# Arquitetura do Projeto

```text
src/main/java/com/phsousa/smart_price_api
├── config
├── controller
├── dto
│   ├── request
│   └── response
├── entity
├── exception
├── mapper
├── repository
├── security
├── service
│   └── impl
└── util
```

---

# Funcionalidades Planejadas

- Cadastro de produtos
- Cadastro de lojas
- Comparação de preços
- Histórico de preços
- Favoritos
- Alertas de preço
- Paginação e filtros
- Ordenação por menor preço
- Busca textual
- Autenticação JWT
- Documentação Swagger

---

# Como Executar o Projeto

## Pré-requisitos

- Java 21
- Maven
- PostgreSQL

---

## Clonar repositório

```bash
git clone https://github.com/seu-usuario/smart-price-api.git
```

---

## Configurar banco de dados

Criar database:

```sql
CREATE DATABASE smart_price;
```

---

## Configurar application.yml

```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/smart_price
    username: postgres
    password: sua_senha
```

---

## Executar projeto

```bash
mvn spring-boot:run
```

---

# Status do Projeto

🚧 Em desenvolvimento

---

# Autor

Paulo Henrique
```