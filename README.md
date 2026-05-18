# Smart Price API

API REST desenvolvida para centralizar, comparar e monitorar preços de produtos em diferentes lojas, permitindo consultas rápidas, histórico de preços, favoritos e alertas personalizados.

O projeto foi criado com foco em arquitetura backend moderna utilizando Java e Spring Boot, aplicando boas práticas de desenvolvimento, segurança, escalabilidade e organização em camadas.

---

# Objetivo do Projeto

O Smart Price API tem como objetivo fornecer uma estrutura robusta para:

* Comparação de preços entre lojas
* Centralização de produtos
* Histórico de preços
* Monitoramento de alterações de preço
* Alertas de preço
* Favoritos de produtos
* Base para integração com crawlers/scrapers
* Estudos avançados de arquitetura backend moderna com Java e Spring

---

# Tecnologias Utilizadas

## Backend

* Java 21
* Spring Boot 3
* Spring Web
* Spring Data JPA
* Spring Security
* Bean Validation
* Hibernate ORM
* JWT Authentication

## Banco de Dados

* PostgreSQL 18
* HikariCP
* Flyway Migration

## Documentação

* Swagger / OpenAPI (SpringDoc)

## Testes

* JUnit 5
* Mockito
* AssertJ

## Build & Dependências

* Maven

## Ferramentas

* IntelliJ IDEA
* pgAdmin 4
* Postman
* Git & GitHub

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
├── specification
└── util
```

---

# Funcionalidades Implementadas

## Segurança

* Autenticação com JWT
* Spring Security
* Controle de permissões e roles
* Endpoint de login
* Endpoint de registro de usuários

## Produtos

* Cadastro de produtos
* Atualização de produtos
* Exclusão de produtos
* Busca por ID
* Listagem de produtos

## Lojas

* Cadastro de lojas
* Atualização de lojas
* Exclusão de lojas
* Listagem de lojas

## Histórico de Preços

* Registro de preços
* Histórico de alterações
* Associação entre produtos e lojas

## Favoritos

* Favoritar produtos
* Remover favoritos
* Listagem de favoritos

## Alertas de Preço

* Cadastro de alertas
* Controle de preço desejado

## Infraestrutura

* Versionamento de banco com Flyway
* Profiles de ambiente (dev)
* Tratamento global de exceções
* DTOs para request/response
* Mappers dedicados
* Documentação Swagger/OpenAPI
* Testes unitários com Mockito

---

# Funcionalidades Futuras

* Paginação e filtros avançados
* Ordenação por menor preço
* Busca textual
* Docker
* Docker Compose
* Testcontainers
* Redis Cache
* Integração com APIs externas
* Web Scraping/Crawlers
* Sistema automatizado de monitoramento de preços
* CI/CD
* Observabilidade e métricas

---

# Como Executar o Projeto

## Pré-requisitos

* Java 21
* Maven
* PostgreSQL

---

# Clonar Repositório

```bash
git clone https://github.com/seu-usuario/smart-price-api.git
```

---

# Configurar Banco de Dados

Criar database:

```sql
CREATE DATABASE smart_price_db;
```

---

# Configurar Ambiente

O projeto utiliza profiles do Spring Boot.

Arquivo base:

```text
application.yaml
```

Arquivo de desenvolvimento:

```text
application-dev.yaml
```

---

# Configurar Datasource

```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/smart-price-db
    username: postgres
    password: postgres
```

---

# Executar Projeto

```bash
mvn spring-boot:run
```

---

# Swagger/OpenAPI

Após iniciar a aplicação:

```text
http://localhost:8080/swagger-ui.html
```

ou

```text
http://localhost:8080/swagger-ui/index.html
```

---

# Estrutura de Segurança

O projeto utiliza autenticação JWT.

Fluxo:

1. Registrar usuário
2. Realizar login
3. Receber token JWT
4. Enviar token no header Authorization

Exemplo:

```http
Authorization: Bearer seu_token
```

---

# Testes

Executar testes:

```bash
mvn test
```

---

# Status do Projeto

🚧 Em desenvolvimento ativo

---

# Autor

Paulo Henrique

* Backend Java Developer
* Java • Spring Boot • PostgreSQL
