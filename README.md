# 🚀 CadastraUsuario - Microserviço

## 📌 Visão Geral

O **CadastraUsuario** é um microserviço responsável pelo gerenciamento completo de usuários, incluindo:

* Cadastro
* Autenticação
* Consulta por email
* Atualização e remoção
* Gerenciamento de endereços
* Gerenciamento de telefones
* Integração com API externa de CEP

A aplicação utiliza autenticação baseada em **JWT** e atua como serviço central de identidade dentro da arquitetura de microserviços.

---

## 🧱 Tecnologias Utilizadas

* Java 21
* Spring Boot
* Spring Web
* Spring Security
* Spring Data JPA
* OpenFeign
* JWT
* Lombok
* PostgreSQL
* Swagger / OpenAPI
* Docker

---

## 🗂️ Estrutura do Projeto

```id="p9v1xk"
com.dominick.cadastrausuario
├── controller
│   ├── UsuarioController
│   └── GlobalExceptionHandler
├── business
│   ├── dto
│   ├── converter
│   └── services
│       ├── UsuarioService
│       └── ViaCepService
├── infrastructure
│   ├── clients
│   │   └── ViaCepClient
│   ├── entity
│   │   ├── Usuario
│   │   ├── Endereco
│   │   └── Telefone
│   ├── repository
│   │   ├── UsuarioRepository
│   │   ├── EnderecoRepository
│   │   └── TelefoneRepository
│   ├── security
│   │   ├── SecurityConfig
│   │   ├── JwtUtil
│   │   ├── JwtRequestFilter
│   │   └── UserDetailsServiceImpl
│   └── exceptions
│       ├── ConflictException
│       ├── ResourceNotFoundException
│       ├── UnauthorizedException
│       └── IllegalArgumentException
```

---

## 🔐 Segurança (Implementação Real)

A aplicação utiliza autenticação baseada em **JWT** com configuração completa no Spring Security.

### 🔹 Componentes implementados

**SecurityConfig**

* Define `SecurityFilterChain`
* Configura aplicação como stateless
* Desabilita CSRF
* Controla acesso aos endpoints

**JwtRequestFilter**

* Intercepta requisições
* Extrai token do header `Authorization`
* Valida o token
* Autentica o usuário no contexto do Spring

**JwtUtil**

* Geração de token
* Validação
* Extração de informações (email)

**UserDetailsServiceImpl**

* Carrega usuário para autenticação

---

### 🔹 Fluxo de autenticação

1. Usuário realiza login (`/usuario/login`)
2. Recebe um token JWT
3. Envia o token nas próximas requisições:

```
Authorization: Bearer <token>
```

4. Token é validado a cada requisição

---

### ⚠️ Observações reais do código

* Não há controle por roles/perfis
* Autorização baseada apenas em usuário autenticado
* Segurança centralizada neste serviço

---

## 🌐 Integração Externa

### 📍 ViaCEP

A aplicação integra com API externa para consulta de endereço a partir do CEP.

**Client:**

```id="o8zv3k"
ViaCepClient
```

**Service:**

```id="m1u7t9"
ViaCepService
```

---

## 🗃️ Banco de Dados

* Banco: **PostgreSQL**
* Persistência via **Spring Data JPA**

### Entidades

* Usuario
* Endereco
* Telefone

### Repositórios

* UsuarioRepository
* EnderecoRepository
* TelefoneRepository

---

## 📬 Endpoints

### 🔐 Autenticação

```id="k3d1mz"
POST /usuario/login
```

---

### 👤 Usuário

```id="t8f2ra"
POST   /usuario
GET    /usuario?email={email}
PUT    /usuario
DELETE /usuario/{email}
```

---

### 📍 Endereço

```id="n4y6wu"
POST /usuario/endereco
PUT  /usuario/endereco?id={id}
GET  /usuario/endereco/{cep}
```

---

### 📞 Telefone

```id="c2p9sl"
POST /usuario/telefone
PUT  /usuario/telefone?id={id}
```

---

## 📖 Swagger / OpenAPI

Disponível em:

```id="b7x4qe"
/swagger-ui/index.html
```

### ✔️ O que o projeto possui

* Documentação automática dos endpoints
* Schemas definidos
* Interface interativa

### ⚠️ Limitações observadas

* Não há customização com `@Operation` ou `@ApiResponse`
* Respostas de erro não são padronizadas

---

## ⚠️ Tratamento de Exceções

Implementado via:

```java id="q9m5dz"
GlobalExceptionHandler
```

### Exceções existentes

* ConflictException
* ResourceNotFoundException
* UnauthorizedException
* IllegalArgumentException

### Comportamento

* Tratamento centralizado
* Retorno simples (não estruturado)

---

## ⚙️ Regras de Negócio

* Cadastro de usuário com validação de duplicidade
* Autenticação via email e senha
* Integração com CEP para preenchimento de endereço
* Relacionamento entre usuário, endereços e telefones
* Atualização parcial de dados

---

## ▶️ Como Executar

```bash id="y5k2rn"
git clone <repo>
cd cadastrausuario
./gradlew bootRun
```

---

## ⚙️ Configuração

Configurações esperadas:

* Banco PostgreSQL ativo
* Configuração de conexão no `application.properties`

---

## 🐳 Docker

O projeto possui suporte a Docker.

### Build

```bash id="x2j7fq"
docker build -t cadastrausuario .
```

### Execução

```bash id="w6s1lv"
docker run -p 8080:8080 cadastrausuario
```

---

## 🧪 Testes

O projeto **não possui testes automatizados implementados**.

---

## 🚀 Melhorias Sugeridas (Baseadas no Código Real)

1. **Implementar testes automatizados**

    * Unitários (services)
    * Integração (controllers + banco)

2. **Padronizar respostas de erro**

    * Criar modelo estruturado (JSON)
    * Melhorar `GlobalExceptionHandler`

3. **Aprimorar documentação Swagger**

    * Adicionar `@Operation` e `@ApiResponse`

4. **Evoluir segurança**

    * Implementar controle por roles/perfis

---

## 👨‍💻 Autor

**Jhonatan Dominick**
