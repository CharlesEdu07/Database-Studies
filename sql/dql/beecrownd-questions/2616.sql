SELECT
    id,
    name
FROM
    customers
WHERE
    NOT EXISTS (
        SELECT 1
        FROM locations
        WHERE customers.id = locations.id_customers
    )
ORDER BY
    id;