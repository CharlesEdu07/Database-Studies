CREATE VIEW total_projetos_por_departamento AS
SELECT
    d.codigo AS codigo_departamento,
    COUNT(p.codigo) AS total_projeto
FROM
    departamento d
    LEFT JOIN projeto p ON d.codigo = p.depto
GROUP BY
    d.codigo;

SELECT
    d.descricao AS nome_departamento,
    COALESCE(fg.nome, 'Sem gerente') AS nome_gerente,
    COALESCE(t.total_projeto, 0) AS numero_projetos
FROM
    departamento d
    LEFT JOIN funcionario fg ON d.gerente = fg.codigo
    LEFT JOIN total_projetos_por_departamento t ON d.codigo = t.codigo_departamento;