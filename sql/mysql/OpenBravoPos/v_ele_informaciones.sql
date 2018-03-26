CREATE OR REPLACE VIEW `v_ele_informaciones` AS
SELECT
    FUN_NUMROW() AS id,
    TAXID AS documento,
    'Email' AS nombre,
    EMAIL AS valor
FROM
    openbravo.customers
WHERE
    EMAIL IS NOT NULL AND EMAIL != ''
UNION SELECT
    FUN_NUMROW() AS id,
    TAXID AS documento,
    'Dirección' AS nombre,
    ADDRESS AS valor
FROM
    openbravo.customers
WHERE
    ADDRESS IS NOT NULL AND ADDRESS != ''
UNION SELECT
    FUN_NUMROW() AS id,
    TAXID AS documento,
    'Teléfono' AS nombre,
    PHONE AS valor
FROM
    openbravo.customers
WHERE
    PHONE IS NOT NULL AND PHONE != '';