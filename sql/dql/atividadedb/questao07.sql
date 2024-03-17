CREATE VIEW total_funcionarios_por_departamento AS
SELECT
	d.codigo AS codigo_departamento,
	COUNT(f.codigo) AS total_funcionario
FROM
	departamento d
	LEFT JOIN funcionario f ON d.codigo = f.codDepto
GROUP BY
	d.codigo;

SELECT
	d.descricao AS nome_departamento,
	COALESCE(fg.nome, 'Sem gerente') AS nome_gerente,
	COALESCE(t.total_funcionario, 0) AS numero_funcionarios
FROM
	departamento d
	LEFT JOIN funcionario fg ON d.codGerente = fg.codigo
	LEFT JOIN total_funcionarios_por_departamento t ON d.codigo = t.codigo_departamento;