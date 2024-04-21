# Links para projetos de:

- [Conexão com banco de dados utilizando JDBC](./jdbc-atividade-db)
- [ORM (Object-Relational Mapping) em Java](./orm)

# JDBC (Java Database Connectivity)

JDBC é uma API (Application Programming Interface) do Java que permite que aplicativos Java acessem e manipulem bancos de dados relacionais. Ele fornece métodos para conectar-se a um banco de dados, enviar consultas SQL e processar os resultados.

## Principais componentes do JDBC:

### DriverManager:

- É a classe central do JDBC.
- Ajuda na obtenção de uma conexão com um banco de dados específico.
- A responsabilidade do DriverManager é carregar os drivers de banco de dados necessários.

### Connection:

- Representa uma conexão com um banco de dados.
- Pode ser obtida usando o `DriverManager.getConnection()`.
- Responsável por fornecer métodos para executar consultas SQL e gerenciar transações.

### Statement:

- Utilizado para enviar consultas SQL ao banco de dados.
- Existem três tipos de objetos `Statement`: `Statement`, `PreparedStatement` e `CallableStatement`.

### ResultSet:

- Armazena os resultados de uma consulta SQL.
- Permite iterar sobre os registros retornados e acessar os valores das colunas.

## Exemplo de uso básico:

```java
import java.sql.*;

public class JDBCExample {
    public static void main(String[] args) {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            // 1. Registrar o driver JDBC
            Class.forName("com.mysql.jdbc.Driver");

            // 2. Estabelecer a conexão
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/meubanco", "usuario", "senha");

            // 3. Criar uma instrução SQL
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM tabela");

            // 4. Processar o resultado
            while (resultSet.next()) {
                System.out.println(resultSet.getString("coluna"));
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            // 5. Fechar os recursos
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
```

# ORM (Object-Relational Mapping) em Java

ORM é uma técnica de programação que mapeia objetos Java para tabelas em um banco de dados relacional. Isso permite que os desenvolvedores manipulem dados do banco de dados usando objetos Java, em vez de escrever consultas SQL manualmente. Na linguagem Java, existem várias bibliotecas ORM populares, incluindo Hibernate, JPA (Java Persistence API) e EclipseLink.

## Principais conceitos e componentes do ORM em Java:

### Entidades:

- Representam objetos Java que são mapeados para tabelas no banco de dados.
- Cada entidade corresponde a uma tabela e cada campo da entidade corresponde a uma coluna na tabela.

### Mapeamento Objeto-Relacional (ORM):

- É a configuração que define como as classes Java são mapeadas para as tabelas do banco de dados e vice-versa.
- Pode ser feito de forma anotada (usando anotações Java) ou por meio de arquivos de mapeamento XML.

### Session / EntityManager:

- É a interface principal para interagir com o banco de dados por meio do ORM.
- Permite executar operações de CRUD (Create, Read, Update, Delete) em objetos Java, que são traduzidos automaticamente em consultas SQL pelo ORM.

### Consultas JPQL (Java Persistence Query Language):

- Uma linguagem de consulta orientada a objetos semelhante ao SQL, mas que opera em entidades e atributos em vez de tabelas e colunas.
- Permite consultas mais flexíveis e orientadas a objetos em comparação com o SQL padrão.

### Transações:

- São operações que envolvem uma ou mais operações de banco de dados que devem ser tratadas de forma atômica (tudo ou nada).
- As transações garantem a consistência dos dados, revertendo as alterações em caso de falha.

## Exemplo de uso básico com Hibernate:

```java
import javax.persistence.*;

@Entity
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private double preco;

    // Getters e Setters
}

// Em outro lugar do código...
EntityManagerFactory emf = Persistence.createEntityManagerFactory("nome-da-unidade-de-persistencia");
EntityManager em = emf.createEntityManager();

em.getTransaction().begin();

// Criar e persistir um novo produto
Produto produto = new Produto();
produto.setNome("Produto A");
produto.setPreco(100.0);
em.persist(produto);

em.getTransaction().commit();

// Fechar o EntityManager e a fábrica de EntityManager
em.close();
emf.close();
```
