# Links para projetos de:

- [Conexão com banco de dados utilizando JDBC](./jdbc-atividade-db)
- [ORM (Object-Relational Mapping) em Java](./orm-atividade-db)

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

## Exemplo de uso básico com Hibernate e JPA-Repository:

### Entidade Produto

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
```

### Repositório Produto

```java
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
```

### Serviço Produto

```java
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {
    private final ProdutoRepository produtoRepository;

    @Autowired
    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public List<Produto> buscarTodosProdutos() {
        return produtoRepository.findAll();
    }

    public Produto buscarProdutoPorId(Long id) {
        return produtoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado com o ID: " + id));
    }

    public Produto salvarProduto(Produto produto) {
        return produtoRepository.save(produto);
    }

    public void deletarProduto(Long id) {
        produtoRepository.deleteById(id);
    }
}
```

### Testando Produto

```java
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class Application {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(Application.class, args);

        // Obtendo o bean do ProdutoRepository
        ProdutoRepository produtoRepository = context.getBean(ProdutoRepository.class);

        // Criando alguns produtos
        Produto produto1 = new Produto();
        produto1.setNome("Produto 1");
        produto1.setPreco(10.99);

        Produto produto2 = new Produto();
        produto2.setNome("Produto 2");
        produto2.setPreco(20.49);

        // Salvando os produtos no banco de dados
        produtoRepository.save(produto1);
        produtoRepository.save(produto2);

        // Imprimindo os produtos salvos
        System.out.println("Todos os produtos no banco de dados:");
        produtoRepository.findAll().forEach(System.out::println);
    }
}
```
