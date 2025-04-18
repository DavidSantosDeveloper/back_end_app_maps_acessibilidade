
## 🗺️ API REST Mapa de Locais com Acessibilidade

### 📖 Descrição do Projeto
A API REST Mapa de Locais com Acessibilidade é parte de uma iniciativa que visa oferecer uma plataforma mobile dedicada a fornecer informações sobre locais acessíveis na cidade para pessoas com deficiência. O aplicativo móvel exibe um mapa dinâmico da cidade, ou de qualquer localidade do mundo, no qual os usuários podem adicionar locais acessíveis e visualizar informações e avaliações sobre ele.
A API gerencia os dados do mapa, permitindo autenticação de usuários, cadastro e avaliação de locais acessíveis, além da consulta de informaçõe.
Sua arquitetura segue o Domain-Driven Design (DDD), com camadas bem definidas que garantem modularidade e manutenção facilitada. Utiliza DTOs para comunicação entre camadas, tratamento centralizado (e personalizado) de exceções e validação de dado.
A aplicação está contida em um container docker🐳.
---

### ⚙️ Tecnologias Utilizadas

- **Java 17** Linguagem de programação utilizada para o desenvolvimento da aplicação.
- **Spring Boot** Framework que simplifica a criação de aplicações Java baseadas em Sprin.
- **Spring Data JPA** Abstração para persistência de dados com JP.
- **PostgreSQL** Sistema de gerenciamento de banco de dados relacional utilizado para armazenar os dados da aplicação.
- **Docker e Docker Compose** Ferramentas para containerização e orquestração da aplicação e seus serviço.
- **Maven** Gerenciador de dependências e automação de builds para projetos Jav.
- **Arquitetura baseada em DDD** Estruturação do projeto seguindo os princípios do Domain-Driven Desig.

---

### 🚀 Instruções para Executar a Aplicação

1. **Clone o repositório:**

   ```bash
   git clone https://github.com/DavidSantosDeveloper/back_end_app_maps_acessibilidade.git
   cd back_end_app_maps_acessibilidade
   ```

2. **Configure o banco de dados PostgreSQL:**

  - Certifique-se de que o PostgreSQL esteja instalado e em execuço.
  - Crie um banco de dados para a aplicaço.
  - Atualize as configurações de conexão com o banco de dados no arquivo `application.properties`, localizado em `src/main/resources/`, com as informações do seu ambiene:

     ```properties
     spring.datasource.url=jdbc:postgresql://localhost:5432/seu_banco
     spring.datasource.username=seu_usuario
     spring.datasource.password=sua_senha
     ```

3. **Compile e execute a aplicação:**

   Utilize o Maven para compilar e executar a aplicação:

   ```bash
   mvn spring-boot:run
   ```

   A aplicação estará disponível em: `http://localhost:8080/api`

---

### 📘 Endpoints Disponíves

A API oferece os seguintes endpoints para gerenciamento das entidaes:

#### 1. **Usuários**

- **POST /api/usuarios*: Cadastra um novo usuáio.
- **GET /api/usuarios/{id}*: Retorna os detalhes de um usuário específco.
- **PUT /api/usuarios/{id}*: Atualiza as informações de um usuário existete.
- **DELETE /api/usuarios/{id}*: Remove um usuário do sistma.

#### 2. **Locais Acessíveis**

- **POST /api/locais*: Adiciona um novo local acessíel.
- **GET /api/locais*: Lista todos os locais acessívis.
- **GET /api/locais/{id}*: Retorna os detalhes de um local específco.
- **PUT /api/locais/{id}*: Atualiza as informações de um local existete.
- **DELETE /api/locais/{id}*: Remove um local do sistma.

#### 3. **Avaliações**

- **POST /api/avaliacoes*: Adiciona uma nova avaliação para um loal.
- **GET /api/avaliacoes/locais/{id}*: Lista todas as avaliações de um local específco.
- **DELETE /api/avaliacoes/{id}*: Remove uma avaliação do sistma.

---

### 🧱 Arquitetura e Padrões Utilizados

#### 🧩 Domain-Driven Design (DD)

A aplicação segue os princípios do DDD, organizando o código em camadas distintas que representam diferentes responsabiliades:

- **Camada de Domíno**: Contém as entidades principais do negócio, encapsulando a lógica de negócios e regras especíicas.
- **Camada de Aplicaço**: Responsável por orquestrar as operações do sistema, coordenando a interação entre as entidades de domínio e os serviços extrnos.
- **Camada de Infraestrutua**: Implementa detalhes técnicos, como acesso a bancos de dados e integração com serviços extrnos.
- **Camada de Apresentação (AP)**: Exponibiliza os endpoints RESTful para interação com os clientes, manipulando requisições e respostasHTT.

Essa separação promove um design modular, facilitando a manutenção e evolução do sitema.

#### 📦 Data Transfer Objects (TOs)

Os DTOs são utilizados para transferir dados entre as camadas da aplicação, especialmente entre a camada de apresentação e a de aplcção. Eles ajudam a desacoplar as entidades de domínio da representação externa dos dados, permitindo maior flexibilidade e segrana.

Por exemplo, ao criar um novo local acessível, um `LocalDTO` pode ser utilizado para receber os dados da requisição, que são então convertidos em uma entidade de domínio antes de serem procesados.

#### 🛡️ Tratamento de Exeções

A aplicação implementa um mecanismo centralizado para tratamento de exceções, garantindo que erros sejam capturados e respostas apropriadas sejam retornadas aos clientes. 
