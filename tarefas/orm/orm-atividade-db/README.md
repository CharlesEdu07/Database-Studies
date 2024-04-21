# Guia para executar o projeto Spring Boot

Este é um guia simples para ajudar a executar o projeto Spring Boot "atividadedb" utilizando ORM Hibernate e JPA.

## Pré-requisitos
Certifique-se de ter os seguintes requisitos instalados em sua máquina:

- Java Development Kit (JDK) versão 17
- Maven

## Executando o projeto

1. **Clonar o repositório:**
git clone <URL_DO_REPOSITORIO>


2. **Navegue até o diretório do projeto:**
cd atividadedb

3. **Compilar o projeto:**
mvn clean install

4. **Executar o aplicativo:**
mvn spring-boot:run

Agora o projeto deve estar em execução e você poderá acessá-lo em `http://localhost:8080`.

## Informações adicionais

- **Testes ORM:**
- Os testes da ORM estão sendo feitos no pacote `config` na pasta `src/test/java/br/com/charlesedu/atividadedb/config`.

- **Entidades:**
- As entidades estão localizadas na pasta `model` em `src/main/java/br/com/charlesedu/atividadedb/model`.

- **Repositórios:**
- Os repositórios estão na pasta `repository` em `src/main/java/br/com/charlesedu/atividadedb/repository`.

Certifique-se de explorar esses diretórios para entender melhor a estrutura e funcionamento do projeto.

Se você tiver alguma dúvida ou encontrar problemas, sinta-se à vontade para entrar em contato