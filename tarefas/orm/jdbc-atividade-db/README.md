# Projeto de Conexão com Banco de Dados utilizando JDBC

Este projeto consiste em uma aplicação Java que demonstra a utilização do JDBC (Java Database Connectivity) para se conectar a um banco de dados PostgreSQL.

## Configuração do Projeto

### Pré-requisitos

Para executar este projeto, é necessário ter o seguinte instalado/configurado:

- JDK (Java Development Kit)
- PostgreSQL
- Driver JDBC do PostgreSQL

### Configuração do Banco de Dados

Antes de executar a aplicação, certifique-se de criar um banco de dados PostgreSQL e uma tabela para utilizar com este projeto. Você pode fazer isso executando os seguintes comandos presentes neste link: [Criação e Inserts](https://github.com/tacianosilva/bsi-tasks/tree/master/database/scripts/AtividadesBD/postgres).

### Configuração do Driver JDBC

Baixe o driver JDBC do PostgreSQL [aqui](https://jdbc.postgresql.org/download/) e adicione-o ao classpath do projeto.

## Executando o Projeto

Após configurar o banco de dados e o driver JDBC, você pode executar a aplicação. Certifique-se de que o banco de dados PostgreSQL esteja em execução.

Para executar o projeto, siga estes passos:

1. Clone este repositório para o seu ambiente local.
2. Compile os arquivos Java, se necessário.
3. Execute o arquivo principal `Main.java`.

```bash
javac Main.java
java Main
```

## Funcionalidades

Este projeto inclui as seguintes funcionalidades:

- Inserção de atividades no banco de dados.
- Alteração do responsável por um projeto no banco de dados.
- Busca de projetos e suas atividades no banco de dados.

## Licença

Este projeto está licenciado sob a [Licença MIT](link_para_licenca).
