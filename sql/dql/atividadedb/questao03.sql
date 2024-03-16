SELECT
	f.nome,
	f.dtnasc
FROM
	funcionario f
WHERE
	(CURRENT_DATE - f.dtnasc) > 21
	AND f.codigo NOT IN (
		SELECT
			codgerente
		FROM
			departamento
		where
			codgerente IS NOT NULL
	);