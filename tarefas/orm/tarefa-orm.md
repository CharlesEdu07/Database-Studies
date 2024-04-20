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
