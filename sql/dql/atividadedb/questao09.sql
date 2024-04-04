CREATE
OR REPLACE VIEW salario_gerente_departamento AS
SELECT
    d.codigo AS codigo_departamento,
    f.codigo AS codigo_gerente,
    f.salario AS salario_gerente
FROM
    departamento d
    JOIN funcionario f ON d.gerente = f.codigo;

SELECT
    f.nome AS nome_funcionario,
    COUNT(p.codigo) AS num_projetos_responsavel,
    f.salario AS salario
FROM
    funcionario f
    JOIN projeto p ON f.codigo = p.responsavel
    JOIN departamento d ON f.depto = d.codigo
    JOIN salario_gerente_departamento sgd ON d.codigo = sgd.codigo_departamento
WHERE
    f.salario > sgd.salario_gerente
GROUP BY
    f.codigo,
    f.nome,
    f.salario
ORDER BY
    f.nome;