SELECT
    products.name,
    providers.name
FROM
    products,
    providers,
    categories
WHERE
    products.id_providers = providers.id
    AND products.id_categories = categories.id
    AND categories.id = 6;