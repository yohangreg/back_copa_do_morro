# Copa do Morro - Aplicação Backend

Este repositório contém o código fonte da aplicação backend para a plataforma Copa do Morro, uma aplicação web voltada para gerenciamento de usuário e torneios esportivos em comunidades locais.

## Descrição

A aplicação backend foi desenvolvida em Java 21 utilizando o framework Spring Boot. Ela fornece os endpoints necessários para a integração com a aplicação frontend da Copa do Morro, permitindo o gerenciamento de usuários, torneios, participantes e demais funcionalidades relacionadas.

## Funcionalidades

- Autenticação de usuários utilizando JSON Web Token (JWT)
- Gerenciamento de usuários (cadastro, login, perfil)
- Cadastro de jogadores e partidas
- Integração com banco de dados no Supabase
- Arquitetura em camadas para uma organização modular do código
- Implementação de uma API RESTful para comunicação com o frontend

## Requisitos

- **Java 21**: Certifique-se de ter o JDK 21 instalado.
- **Maven**: Instale o Maven para gerenciar as dependências do projeto.
- **PostgreSQL**: Banco de dados usado pela aplicação.
- **Git**: Para clonar o repositório.

## Tecnologias Utilizadas

- Spring Boot 3.2.5
- Spring Data JPA 3.2.4
- Spring Security 3.2.5
- PostgreSQL 42.7.2
- Hibernate 6.5.0
- Lombok
- JWT 0.12.5
- Apache POI 5.2.5

## Estrutura do Projeto

O projeto segue a seguinte estrutura de diretórios:

- `src/main/java`: Contém o código fonte da aplicação Java.
    - `com.copadomorro.backend`: Pacote principal da aplicação.
        - `security`: Configurações da aplicação, como configuração do Spring Security e JWT.
        - `controller`: Controladores REST para cada recurso da aplicação (Usuários, Documentos, etc.).
        - `model`: Modelos de dados da aplicação.
        - `repository`: Interfaces dos repositórios para acesso aos dados.
        - `service`: Lógica de negócio da aplicação.
- `src/main/resources`: Contém os recursos da aplicação, como arquivos de configuração.

## Configuração do Banco de Dados

1. Instale o PostgreSQL e crie um banco de dados.
2. Atualize o arquivo `application.properties` ou `application.yml` com as configurações do seu banco de dados:

   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/seu_banco_de_dados
   spring.datasource.username=seu_usuario
   spring.datasource.password=sua_senha
   spring.jpa.hibernate.ddl-auto=update
   spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
   ```
   
## Passo a Passo para Rodar a Aplicação

1. **Clone o repositório:**

   ```sh
   git clone https://github.com/CopaDoMorro/copadomorro-backend.git
   cd copadomorro-backend
   ```


2. **Compile e instale as dependências do projeto:**

   ```sh
   mvn clean install
   ```

3. **Configure as variáveis de ambiente (se necessário):**

    - `JAVA_HOME`: Caminho para o JDK 21.
    - `MAVEN_HOME`: Caminho para a instalação do Maven.

4. **Execute a aplicação:**

   ```sh
   mvn spring-boot:run
   ```

5. **Acesse a aplicação:**

    - A aplicação estará disponível em `http://localhost:8080`.

## Contribuição

1. Faça um fork do projeto.
2. Crie uma branch para sua feature (`git checkout -b feature/nova-feature`).
3. Commit suas mudanças (`git commit -m 'Adiciona nova feature'`).
4. Faça o push para a branch (`git push origin feature/nova-feature`).
5. Abra um Pull Request.

## Licença

Este projeto está licenciado sob a Licença MIT - veja o arquivo [LICENSE](LICENSE) para mais detalhes.