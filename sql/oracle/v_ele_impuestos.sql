
  CREATE OR REPLACE FORCE VIEW "V_ELE_IMPUESTOS" ("CODIGO", "NUMERO", "CODIGO_IMPUESTO", "CODIGO_PORCENTAJE", "BASE_IMPONIBLE", "TARIFA", "VALOR") AS
  SELECT
    f.cod_documento AS codigo,
    f.num_factura AS numero,
    '2' AS codigo_impuesto,
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
    round(
        f.total_con_iva - f.descuentos,
        2
    ) AS base_imponible,
    d.porcentaje_iva AS tarifa,
    round(f.iva,2) AS valor
FROM
    dismemayor.fac_factura_c f
    INNER JOIN DISMEMAYOR.FAC_FACTURA_D d ON
        f.cod_empresa = d.cod_empresa
    AND
        f.cod_documento = d.cod_documento
    AND
        f.num_factura = d.num_factura
    AND
        nvl(f.estado,'G') <> 'A'
    AND
        f.num_factura > 1001000000000;
