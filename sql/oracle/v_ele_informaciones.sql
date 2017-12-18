CREATE OR REPLACE FORCE VIEW V_ELE_INFORMACIONES (
    "ID",
    "DOCUMENTO",
    "NOMBRE",
    "VALOR"
) AS
    WITH data AS (
        SELECT
            d.documento,
            'Dirección' AS nombre,
            d.direccion AS valor
        FROM
            dismemayor.gnr_persona d
        WHERE
            d.direccion IS NOT NULL
        UNION
        SELECT
            d.documento,
            'Teléfono' AS nombre,
            d.telefono AS valor
        FROM
            dismemayor.gnr_persona d
        WHERE
            d.telefono IS NOT NULL
        UNION
        SELECT
            d.documento,
            'Email' AS nombre,
            DECODE(
                substr(
                    d.mail,
                    0,
                    instr(d.mail,',') - 1
                ),
                NULL,
                d.mail,
                substr(
                    d.mail,
                    0,
                    instr(d.mail,',') - 1
                )
            ) AS valor
        FROM
            dismemayor.gnr_persona d
        WHERE
            d.mail IS NOT NULL
        UNION
        SELECT
            p.documento documento,
            'Email' AS nombre,
            substr(
                p.mail,
                instr(
                    p.mail
                     || ',',
                    ','
                ) + 1
            ) valor
        FROM
            dismemayor.gnr_persona p
        WHERE
            substr(
                p.mail,
                instr(
                    p.mail || ',',
                    ','
                ) + 1
            ) IS NOT NULL
    ) SELECT
        ROWNUM AS id,
        documento,
        nombre,
        valor
    FROM
        data
    WHERE
        documento IS NOT NULL;