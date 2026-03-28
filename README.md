🚀 CadastraUsuario API










📌 Sobre o Projeto

Microserviço responsável pelo gerenciamento de usuários, incluindo cadastro, autenticação, atualização de dados e integração com serviço externo de CEP.

Desenvolvido com Spring Boot, seguindo boas práticas de arquitetura em camadas e preparado para ambientes de microserviços.

📖 Visão Geral

A API fornece funcionalidades completas para gestão de usuários:

👤 Cadastro de usuários
🔐 Autenticação com JWT
🔍 Consulta de usuários por email
✏️ Atualização e remoção de usuários
📍 Gerenciamento de endereços
📞 Gerenciamento de telefones
🌐 Integração com ViaCEP
⚠️ Tratamento global de exceções
🧱 Arquitetura

A aplicação segue uma arquitetura em camadas:

src/main/java/com/dominick/cadastrausuario
│
├── controller
├── business
│   ├── dto
│   ├── converter
│   └── services
│
├── infrastructure
│   ├── clients
│   ├── entity
│   ├── repository
│   ├── security
│   └── exceptions
⚙️ Camadas e Responsabilidades
🎯 Controller
UsuarioController
GlobalExceptionHandler

Responsável por:

Expor endpoints REST
Validar requisições
Retornar respostas HTTP
Tratar exceções
🧠 Business

Services

UsuarioService
ViaCepService

Converter

UsuarioConverter

DTOs

UsuarioDTO
EnderecoDTO
TelefoneDTO

Responsável por:

Regras de negócio
Orquestração
Conversão DTO ↔ Entity
🏗️ Infrastructure
📡 Clients
ViaCepClient
ViaCepDTO

Integração com API externa via OpenFeign.

🗄️ Entity
Usuario
Endereco
Telefone
💾 Repository
UsuarioRepository
EnderecoRepository
TelefoneRepository
🔐 Security
SecurityConfig
JwtUtil
JwtRequestFilter
UserDetailsServiceImpl

Autenticação e autorização com JWT.

⚠️ Exceptions
ConflictException
ResourceNotFoundException
UnauthorizedException
IllegalArgumentException
🚀 Tecnologias
☕ Java 21
🌱 Spring Boot
🔐 Spring Security
🗄️ Spring Data JPA
🌐 OpenFeign
🔑 JWT
📄 Swagger / OpenAPI
⚡ Lombok
🐘 PostgreSQL
🐳 Docker
🔐 Segurança

A aplicação utiliza autenticação baseada em JWT.

Fluxo:
Usuário faz login
Recebe um token
Envia o token no header:
Authorization: Bearer <token>
Token validado a cada requisição
🌐 Integração Externa
📍 ViaCEP

Consulta de endereço a partir do CEP.

Client: ViaCepClient
Service: ViaCepService
🔗 Endpoints
🔐 Autenticação
POST /usuario/login
👤 Usuário
POST   /usuario
GET    /usuario?email={email}
PUT    /usuario
DELETE /usuario/{email}
📍 Endereço
POST /usuario/endereco
PUT  /usuario/endereco?id={id}
GET  /usuario/endereco/{cep}
📞 Telefone
POST /usuario/telefone
PUT  /usuario/telefone?id={id}
📄 Documentação da API

Disponível via Swagger:

/swagger-ui/index.html
▶️ Como Executar
📌 Pré-requisitos
Java 21
Gradle
PostgreSQL
▶️ Rodando localmente
./gradlew build
./gradlew bootRun

ou

java -jar build/libs/*.jar
🐳 Docker
Build da imagem
docker build -t cadastrausuario .
Rodar container
docker run -p 8080:8080 cadastrausuario
🧪 Testes

Atualmente o projeto não possui testes automatizados.

./gradlew test
⚠️ Melhorias Futuras
✅ Testes unitários e integração
✅ Padronização de respostas (Response Pattern)
✅ Separação de autenticação (AuthController)
✅ Melhor uso de status HTTP
✅ Retorno estruturado no login
👨‍💻 Autor

Jhonatan Dominick