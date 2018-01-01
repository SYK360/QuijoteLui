CREATE OR REPLACE FORCE VIEW V_ELE_IMPUESTOS ("ID", "CODIGO", "NUMERO", "CODIGO_IMPUESTO", "CODIGO_PORCENTAJE", "BASE_IMPONIBLE", "TARIFA", "VALOR") AS
  WITH data as(
SELECT DISTINCT
    f.cod_documento AS codigo,
    TO_CHAR(f.NUM_FACTURA,'fm000000000000000') AS numero,
    cast('2' as varchar2(1)) AS codigo_impuesto,
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
    decode(d.porcentaje_iva,0,f.total_sin_iva,round(
        f.total_con_iva - f.descuentos,
        2
    )) AS base_imponible,
    d.porcentaje_iva AS tarifa,
    round(f.iva,2) AS valor
FROM
    DISMEMAYOR.fac_factura_c f
    INNER JOIN DISMEMAYOR.FAC_FACTURA_D d ON
        f.cod_empresa = d.cod_empresa
    AND
        f.cod_documento = d.cod_documento
    AND
        f.num_factura = d.num_factura
    AND
        nvl(f.estado,'G') <> 'A'
    AND
        f.num_factura > 1001000000000
union all
SELECT
    distinct
    d.cod_documento AS codigo,
    TO_CHAR(d.num_devolucion,'fm000000000000000') AS numero,
    CAST('2' AS VARCHAR2(1) ) AS codigo_impuesto,
        DECODE(
        dd.porcentaje_iva,
        0,
        '0',
        12,
        '2',
        14,
        '3',
        '-1'
    ) AS codigo_porcentaje,
    decode(dd.porcentaje_iva,0,d.total_sin_iva,round(
        d.total_con_iva - d.descuentos,
        2
    )) AS base_imponible,
    dd.porcentaje_iva AS tarifa,
    round(d.iva,2) AS valor
FROM
    dismemayor.fac_devolucion_c d
    INNER JOIN dismemayor.fac_devolucion_d dd ON d.cod_empresa = dd.cod_empresa
                                      AND d.cod_documento = dd.cod_documento
                                      AND d.num_devolucion = dd.num_devolucion
    where d.num_devolucion >    1001000000000
union all
SELECT
        nc.cod_documento AS codigo,
        TO_CHAR(nc.num_abono,'fm000000000000000') AS numero,
        DECODE(dismemayor.fun_get_porcentaje_iva,14,'3',12,'2',0,'0') AS codigo_impuesto,
        CAST('2' AS VARCHAR2(1) ) as codigo_porcentaje,
        round(nc.total_capital / ( (dismemayor.fun_get_porcentaje_iva / 100) + 1),2) as base_imponible,
        dismemayor.fun_get_porcentaje_iva AS tarifa,
        round( (dnc.capital * dismemayor.fun_get_porcentaje_iva) / (dismemayor.fun_get_porcentaje_iva + 100),2) AS valor
    FROM
        dismemayor.cxc_abono_c nc
        INNER JOIN dismemayor.cxc_abono_d dnc ON nc.cod_empresa = dnc.cod_empresa
                                                 AND nc.cod_documento = dnc.cod_documento
                                                 AND nc.num_abono = dnc.num_abono
        INNER JOIN dismemayor.cxc_tipo_motivo m ON nc.cod_motivo = m.cod_motivo
    WHERE
        nc.cod_documento = 'NCC'
)
SELECT rownum as id, codigo, numero, codigo_impuesto, codigo_porcentaje, base_imponible, tarifa, valor
from data;
