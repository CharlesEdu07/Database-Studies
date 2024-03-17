/*Problemas de ter valores nulos nos dados:
 
 Inconsistência nos cálculos: Valores nulos podem causar problemas em cálculos e operações matemáticas, pois
 muitas operações com nulos resultam em nulo. Isso pode levar a resultados inesperados ou inconsistentes.
 
 Dificuldade na comparação: Comparar valores nulos pode ser complicado, pois o resultado de uma comparação com
 nulo é sempre desconhecido (não verdadeiro, nem falso). Isso pode exigir tratamento especial nas consultas SQL.
 
 Complexidade na lógica de negócios: A presença de valores nulos pode aumentar a complexidade da lógica de
 negócios, exigindo tratamento especial para lidar com eles.
 
 Funcionamento do Right e do Left Join:
 
 Left Join (ou Left Outer Join): Retorna todos os registros da tabela à esquerda (tabela do lado esquerdo da
 junção) e os registros correspondentes da tabela à direita (tabela do lado direito da junção). Se não houver
 correspondência na tabela à direita, os valores NULL são retornados.
 
 Right Join (ou Right Outer Join): Similar ao Left Join, mas retorna todos os registros da tabela à direita e
 os registros correspondentes da tabela à esquerda. Se não houver correspondência na tabela à esquerda, os
 valores NULL são retornados.
 
 Funcionamento do Full Outer Join e como pode ser feito no MySQL ou MariaDB que não tem mais o comando Full:
 
 Full Outer Join: Retorna todos os registros de ambas as tabelas, combinando registros quando houver
 correspondência e preenchendo com valores nulos quando não houver correspondência.
 
 Como fazer no MySQL ou MariaDB:
 Como MySQL e MariaDB não têm um operador FULL OUTER JOIN, você pode simular esse comportamento usando
 uma combinação de LEFT JOIN, RIGHT JOIN e UNION ALL: */
SELECT
    *
FROM
    tabela1
    LEFT JOIN tabela2 ON tabela1.id = tabela2.id
UNION
ALL
SELECT
    *
FROM
    tabela1
    RIGHT JOIN tabela2 ON tabela1.id = tabela2.id
WHERE
    tabela1.id IS NULL;

/* Neste exemplo, você primeiro faz um LEFT JOIN entre as tabelas, em seguida, um RIGHT JOIN, e finalmente, 
 uma cláusula WHERE para selecionar as linhas onde a tabela1.id é nula, garantindo que todos os registros
 sejam incluídos. */