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

ORM (Object-Relational Mapping) é uma técnica utilizada para mapear objetos de um sistema de orientação a objetos para estruturas de dados em um banco de dados relacional, permitindo assim uma integração mais transparente entre o código da aplicação e o banco de dados.

Na linguagem Java, o ORM é frequentemente utilizado para simplificar o acesso e manipulação de dados em bancos de dados relacionais, eliminando a necessidade de escrever queries SQL manualmente e tornando o desenvolvimento mais eficiente e menos propenso a erros.

## Spring Data JPA

O Spring Data JPA é uma ferramenta do Spring Framework que facilita o desenvolvimento de aplicações Java que utilizam JPA (Java Persistence API), que é a API padrão do Java para mapeamento objeto-relacional. Spring Data JPA simplifica a implementação de camadas de acesso a dados, permitindo que os desenvolvedores se concentrem mais na lógica de negócios da aplicação.

### Características e Funcionalidades

- **Repositórios:** O Spring Data JPA fornece uma abstração de repositório que elimina a necessidade de escrever código boilerplate para acessar o banco de dados. Os repositórios permitem que os desenvolvedores definam consultas usando convenções de nomenclatura, reduzindo a quantidade de código necessário.

- **Mapeamento de Entidades:** Spring Data JPA simplifica o mapeamento de entidades JPA para tabelas de banco de dados, permitindo que os desenvolvedores usem anotações simples para definir o relacionamento entre classes Java e tabelas de banco de dados.

- **Consultas Dinâmicas:** O Spring Data JPA permite a criação de consultas dinâmicas usando o critério de consulta, que é uma API de construção de consultas baseada em objetos. Isso oferece flexibilidade aos desenvolvedores para construir consultas complexas de forma programática.

- **Suporte a Transações:** O Spring Data JPA integra-se perfeitamente com o mecanismo de transações do Spring Framework, permitindo que as operações de banco de dados sejam realizadas dentro do contexto de uma transação gerenciada pelo Spring.

Em resumo, o Spring Data JPA simplifica o desenvolvimento de aplicações Java que interagem com bancos de dados relacionais, fornecendo uma camada de abstração poderosa sobre a API JPA padrão e facilitando tarefas comuns, como acesso a dados, mapeamento de entidades e criação de consultas. Isso permite que os desenvolvedores se concentrem mais na lógica de negócios da aplicação e menos na interação com o banco de dados.

## Exemplo de uso básico com Spring Data JPA:

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
