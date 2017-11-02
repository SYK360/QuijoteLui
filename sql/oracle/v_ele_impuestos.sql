CREATE OR REPLACE FORCE VIEW "V_ELE_IMPUESTOS" (
    "ID",
    "CODIGO",
    "NUMERO",
    "CODIGO_IMPUESTO",
    "CODIGO_PORCENTAJE",
    "BASE_IMPONIBLE",
    "TARIFA",
    "VALOR"
) AS
    SELECT DISTINCT
        f.num_factura AS id,
        f.cod_documento AS codigo,
        TO_CHAR(f.num_factura,'fm000000000000000') AS numero,
        CAST('2' AS VARCHAR2(1) ) AS codigo_impuesto,
        DECODE(
            d.porcentaje_iva,
            0,
            '0',
            12,
            '2',
            14,
            '3',
            '-1'
        ) AS codigo_porcentaje,
        DECODE(
            d.porcentaje_iva,
            0,
            f.total_sin_iva,
            round(
                f.total_con_iva - f.descuentos,
                2
            )
        ) AS base_imponible,
        d.porcentaje_iva AS tarifa,
        round(f.iva,2) AS valor
    FROM
        dismemayor.fac_factura_c f
        INNER JOIN dismemayor.fac_factura_d d ON
            f.cod_empresa = d.cod_empresa
        AND
            f.cod_documento = d.cod_documento
        AND
            f.num_factura = d.num_factura
        AND
            nvl(f.estado,'G') <> 'A'
        AND
            f.num_factura > 1001000000000;