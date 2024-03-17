SELECT
    f.nome,
    f.dtnasc,
    d.descricao
FROM
    funcionario f,
    departamento d
WHERE
    f.codigo NOT IN (
        SELECT
            d.codgerente
        FROM
            departamento d
        WHERE
            d.codgerente IS NOT NULL
    )
    AND (f.coddepto = d.codigo)
ORDER BY
    d.codigo;