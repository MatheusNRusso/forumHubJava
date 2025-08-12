# 📚 FórumHub - Challenge Alura ONE

API REST para gerenciamento de tópicos em um fórum, desenvolvida como parte do **Challenge FórumHub** do programa **Oracle Next Education (ONE)** em parceria com a **Alura**.

O projeto segue as boas práticas do Spring Boot, utilizando autenticação JWT, persistência com JPA/Hibernate e arquitetura limpa para garantir escalabilidade e manutenção.

---

## 🚀 Funcionalidades

- Cadastro e autenticação de usuários via JWT
- CRUD de tópicos com:
  - Título
  - Mensagem
  - Status (`NAO_RESPONDIDO`, `RESPONDIDO`, `FECHADO`)
  - Autor
  - Curso
- Cadastro e listagem de cursos
- Respostas para tópicos, com atualização automática do status para `RESPONDIDO`
- Controle de acesso via Spring Security
- Paginação e ordenação nas listagens
- Atualização de tópicos (mensagem e status)

---

## 🛠 Tecnologias utilizadas

- **Java 21**
- **Spring Boot 3**
- **Spring Data JPA**
- **Spring Security + JWT**
- **Hibernate**
- **MySQL** (pode ser alterado para PostgreSQL/H2)
- **Maven**
- **Lombok**
- **MapStruct** (para mapeamento de DTOs)

---

## 📌 Pré-requisitos

Antes de rodar o projeto, você precisa ter instalado:
- [Java 21+](https://jdk.java.net/)
- [Maven](https://maven.apache.org/)
- [MySQL](https://dev.mysql.com/downloads/mysql/)

---

## ⚙️ Configuração do projeto

1. **Clone o repositório**
   ```bash
   git clone https://github.com/MatheusNRusso/forumHubJava.git
   cd forumhub
````

2. **Configure o banco de dados**

   * Crie um banco no MySQL:

     ```sql
     CREATE DATABASE forumhub;
     ```
   * Ajuste as credenciais no arquivo `application.properties`:

     ```properties
     spring.datasource.url=jdbc:mysql://localhost:3306/forumhub
     spring.datasource.username=root
     spring.datasource.password=senha
     spring.jpa.hibernate.ddl-auto=update
     spring.jpa.show-sql=true
     ```

3. **Instale as dependências**

   ```bash
   mvn clean install
   ```

4. **Execute o projeto**

   ```bash
   mvn spring-boot:run
   ```

---

## 🔐 Fluxo de autenticação

1. **Criar usuário**

   * POST `/usuarios`

     ```json
     {
       "nome": "João da Silva",
       "email": "joao@email.com",
       "senha": "123456"
     }
     ```

2. **Login**

   * POST `/login`

     ```json
     {
       "email": "joao@email.com",
       "senha": "123456"
     }
     ```
   * Resposta:

     ```json
     {
       "token": "JWT_GERADO_AQUI"
     }
     ```

3. **Acessar endpoints protegidos**

   * Enviar o token no header:

     ```
     Authorization: Bearer JWT_GERADO_AQUI
     ```

---

## 📂 Endpoints principais

### **Tópicos**

* `POST /topicos` → cria um novo tópico
* `GET /topicos` → lista todos os tópicos (paginação e ordenação)
* `GET /topicos/{id}` → detalha um tópico (inclui nome do autor e curso)
* `PUT /topicos/{id}` → atualiza mensagem e/ou status
* `DELETE /topicos/{id}` → inativa um tópico

### **Respostas**

* `POST /respostas` → adiciona resposta a um tópico (atualiza status para `RESPONDIDO`)

### **Cursos**

* `POST /cursos` → cadastra curso
* `GET /cursos` → lista cursos

### **Usuários**

* `POST /usuarios` → cria usuário
* `POST /login` → autentica e retorna token JWT

---

## 🗂 Estrutura de pastas

```bash
.
├── src
│   ├── main
│   │   ├── java/com/mylocal/forumhub
│   │   │   ├── config        # Configurações (Segurança, JWT, CORS)
│   │   │   ├── controller    # Endpoints da API
│   │   │   ├── dto           # Objetos de transferência de dados
│   │   │   ├── mapper        # Interfaces MapStruct
│   │   │   ├── model         # Entidades JPA
│   │   │   ├── repository    # Interfaces de acesso ao banco
│   │   │   ├── service       # Regras de negócio
│   │   │   └── ForumhubApplication.java
│   │   └── resources
│   │       ├── application.properties
│   │       └── import.sql    # Dados iniciais (opcional)
│   └── test
│       └── java/com/mylocal/forumhub
│           └── ... testes unitários e de integração
```

---

## ✅ Como rodar os testes

```bash
mvn test
```

---

## 📄 Licença

Este projeto foi desenvolvido como parte do **Challenge Alura ONE** e é de uso livre para fins de estudo.
