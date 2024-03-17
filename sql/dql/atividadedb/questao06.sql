SELECT
    f.nome,
    f.salario,
    COALESCE(d.descricao, 'Sem departamento') AS descricao_departamento
FROM
    funcionario f
    LEFT JOIN departamento d ON f.codDepto = d.codigo;