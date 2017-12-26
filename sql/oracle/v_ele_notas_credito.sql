CREATE OR REPLACE FORCE VIEW V_ELE_NOTAS_CREDITO ("ID", "ID_CONTRIBUYENTE", "CODIGO", "NUMERO", "CODIGO_DOCUMENTO", "ESTABLECIMIENTO", "PUNTO_EMISION", "SECUENCIAL", "FECHA", "TIPO_DOCUMENTO", "DOCUMENTO", "RAZON_SOCIAL", "DOCUMENTO_MODIFICADO", "MODIFICADO", "FECHA_MODIFICADO", "TOTAL_SIN_IMPUESTOS", "TOTAL_MODIFICADO", "TOTAL_SIN_IVA", "TOTAL_CON_IVA", "IVA", "MOTIVO", "DIRECCION_ESTABLECIMIENTO") AS 
  WITH data AS (
    SELECT
        d.cod_documento AS codigo,
        d.num_devolucion AS numero,
        '04' codigo_documento,
        substr(TO_CHAR(d.num_devolucion,'fm000000000000000'),1,3) establecimiento,
        substr(TO_CHAR(d.num_devolucion,'fm000000000000000'),4,3) punto_emision,
        TO_CHAR(substr(TO_CHAR(d.num_devolucion,'fm000000000000000'),7,9) ) secuencial,
        trunc(d.fecha_devolucion) AS fecha,
        DECODE(c.documento,'9999999999999','07',DECODE(length(c.documento),13,'04',10,'05','06') ) AS tipo_documento,
        c.razon_social,
        c.documento,
        '01' AS documento_modificado,
        dismemayor.pkg_info_nota_credito.fun_numero_comprobante(d.cod_documento,d.num_devolucion) AS modificado,
        dismemayor.pkg_info_nota_credito.fun_fecha_comprobante(d.cod_documento,d.num_devolucion) AS fecha_modificado,
        round(d.total_sin_iva + d.total_con_iva - d.descuentos,2) AS total_sin_impuestos,
        round(d.total_devolucion,2) AS total_modificado,
        round(d.total_sin_iva,2) total_sin_iva,
        round(d.total_con_iva - d.descuentos,2) total_con_iva,
        round(d.iva,2) iva,
        d.detalle AS motivo
    FROM
        dismemayor.fac_devolucion_c d
        INNER JOIN dismemayor.v_cliente c ON d.cod_cliente = c.cod_cliente
    WHERE
        d.num_devolucion > 1001000000000
        AND   nvl(d.estado,'G') <> 'A'
    UNION ALL
    SELECT
        nc.cod_documento AS codigo,
        nc.num_abono AS numero,
        '04' codigo_documento,
        substr(TO_CHAR(nc.num_abono,'fm000000000000000'),1,3) establecimiento,
        substr(TO_CHAR(nc.num_abono,'fm000000000000000'),4,3) punto_emision,
        TO_CHAR(substr(TO_CHAR(nc.num_abono,'fm000000000000000'),7,9) ) secuencial,
        trunc(nc.fecha_abono) AS fecha,
        DECODE(c.documento,'9999999999999','07',DECODE(length(c.documento),13,'04',10,'05','06') ) AS tipo_documento,
        c.razon_social,
        c.documento,
        '01' AS documento_modificado,
        dismemayor.pkg_info_nota_credito.fun_numero_comprobante(nc.cod_documento,nc.num_abono) AS modificado,
        dismemayor.pkg_info_nota_credito.fun_fecha_comprobante(nc.cod_documento,nc.num_abono) AS fecha_modificado,
        round(nc.total_capital / ( (dismemayor.fun_get_porcentaje_iva / 100) + 1),2) AS total_sin_impuestos,
        ( nc.total_capital ) AS total_modificado,
        0 AS total_sin_iva,
        round(nc.total_capital / ( (dismemayor.fun_get_porcentaje_iva / 100) + 1),2) AS total_con_iva,
        round( (nc.total_capital * dismemayor.fun_get_porcentaje_iva) / (dismemayor.fun_get_porcentaje_iva + 100),2) AS iva,
        nc.detalle AS motivo
    FROM
        dismemayor.v_cliente c
        INNER JOIN dismemayor.cxc_abono_c nc ON nc.cod_cliente = c.cod_cliente
    WHERE
        nc.cod_documento = 'NCC'
        AND   nc.num_abono > 1001000000000
        AND   nvl(nc.estado,'G') <> 'A'
) SELECT
    numero AS id,
    1 as id_contribuyente,
    codigo,
    TO_CHAR(numero,'fm000000000000000') AS numero,
    CAST(codigo_documento AS VARCHAR2(2) ) AS codigo_documento,
    establecimiento,
    punto_emision,
    secuencial,
    fecha,
    tipo_documento,
    documento,
    razon_social,
    cast(documento_modificado as varchar2(2)) as documento_modificado,
    modificado,
    fecha_modificado,
    total_sin_impuestos,
    total_modificado,
    total_sin_iva,
    total_con_iva,
    iva,
    motivo,
    (SELECT DIRECCION FROM V_ELE_CONTRIBUYENTES) as direccion_establecimiento
  FROM
    data;