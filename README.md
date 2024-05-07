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

- Java 21
- Maven
- Spring Boot
- JWT para autenticação de usuários
- Banco de dados - Supabase

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

## Configuração

1. Clone o repositório para sua máquina local.
2. Certifique-se de ter as ferramentas e dependências necessárias instaladas.
3. Configure as credenciais de acesso ao banco de dados Supabase no arquivo `application.properties`.
4. Execute a aplicação utilizando Maven ou sua IDE preferida.

## Contribuição

Contribuições são bem-vindas! Se você deseja contribuir com a aplicação, por favor, siga estas etapas:

1. Faça um fork do repositório.
2. Crie uma branch para sua nova funcionalidade (`git checkout -b feature/nova-funcionalidade`).
3. Faça o commit de suas alterações (`git commit -am 'Adicionando nova funcionalidade'`).
4. Faça o push para a branch (`git push origin feature/nova-funcionalidade`).
5. Crie um novo Pull Request.


---

Para mais informações sobre como utilizar a API backend da Copa do Morro, consulte a documentação ou entre em contato com a equipe de desenvolvimento.
