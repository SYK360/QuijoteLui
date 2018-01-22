CREATE OR REPLACE FORCE VIEW V_ELE_NOTAS_CREDITO_DETALLE ("ID", "CODIGO", "NUMERO", "CODIGO_INTERNO", "DESCRIPCION", "CANTIDAD", "PRECIO_UNITARIO", "DESCUENTO", "PRECIO_TOTAL_SIN_IMPUESTO", "CODIGO_PORCENTAJE", "PORCENTAJE_IVA", "VALOR_IVA") AS 
  WITH data AS (
    SELECT
        dm.cod_documento AS codigo,
        dm.num_devolucion AS numero,
        a.cod_articulo AS codigo_interno,
        a.nombre_articulo AS descripcion,
        round(dd.cantidad,2) AS cantidad,
        round(dd.precio_unitario,2) AS precio_unitario,
        round(dd.precio_unitario * dd.cantidad,2) - round( ( (dd.precio_unitario * dd.cantidad - ( (nvl(dd.porc_desc_vol,0) / 100) * dd.precio_unitario *
dd.cantidad) ) - (nvl(dd.porc_desc_pago,0) / 100) * (dd.precio_unitario * dd.cantidad - ( (nvl(dd.porc_desc_vol,0) / 100) * dd.precio_unitario * dd
.cantidad) ) ) - ( (nvl(dd.porc_desc_prom,0) / 100) * ( (dd.precio_unitario * dd.cantidad - ( (nvl(dd.porc_desc_vol,0) / 100) * dd.precio_unitario * dd
.cantidad) ) - (nvl(dd.porc_desc_pago,0) / 100) * (dd.precio_unitario * dd.cantidad - ( (nvl(dd.porc_desc_vol,0) / 100) * dd.precio_unitario * dd.cantidad
) ) ) ),2) AS descuento,
        greatest(0,round( (round(dd.cantidad,2) * round(dd.precio_unitario,2) ) - (round(dd.precio_unitario * dd.cantidad,2) - round( ( (dd.precio_unitario
* dd.cantidad - ( (nvl(dd.porc_desc_vol,0) / 100) * dd.precio_unitario * dd.cantidad) ) - (nvl(dd.porc_desc_pago,0) / 100) * (dd.precio_unitario * dd
.cantidad - ( (nvl(dd.porc_desc_vol,0) / 100) * dd.precio_unitario * dd.cantidad) ) ) - ( (nvl(dd.porc_desc_prom,0) / 100) * ( (dd.precio_unitario * dd
.cantidad - ( (nvl(dd.porc_desc_vol,0) / 100) * dd.precio_unitario * dd.cantidad) ) - (nvl(dd.porc_desc_pago,0) / 100) * (dd.precio_unitario * dd.cantidad
- ( (nvl(dd.porc_desc_vol,0) / 100) * dd.precio_unitario * dd.cantidad) ) ) ),2) ),2) ) AS precio_total_sin_impuesto,
        DECODE(dd.porcentaje_iva,14,'3',12,'2',0,'0') AS codigo_porcentaje,
        dd.porcentaje_iva,
        greatest(0,round( (dd.porcentaje_iva / 100) * ( (round(dd.cantidad,2) * round(dd.precio_unitario,2) ) - (round(dd.precio_unitario * dd.cantidad
,2) - round( ( (dd.precio_unitario * dd.cantidad - ( (nvl(dd.porc_desc_vol,0) / 100) * dd.precio_unitario * dd.cantidad) ) - (nvl(dd.porc_desc_pago
,0) / 100) * (dd.precio_unitario * dd.cantidad - ( (nvl(dd.porc_desc_vol,0) / 100) * dd.precio_unitario * dd.cantidad) ) ) - ( (nvl(dd.porc_desc_prom
,0) / 100) * ( (dd.precio_unitario * dd.cantidad - ( (nvl(dd.porc_desc_vol,0) / 100) * dd.precio_unitario * dd.cantidad) ) - (nvl(dd.porc_desc_pago,
0) / 100) * (dd.precio_unitario * dd.cantidad - ( (nvl(dd.porc_desc_vol,0) / 100) * dd.precio_unitario * dd.cantidad) ) ) ),2) ) ),2) ) AS valor_iva
    FROM
        dismemayor.fac_devolucion_c dm
        INNER JOIN dismemayor.fac_devolucion_d dd ON dm.cod_documento = dd.cod_documento
                                                     AND dm.num_devolucion = dd.num_devolucion
        INNER JOIN dismemayor.inv_articulo a ON dd.cod_articulo = a.cod_articulo
    WHERE
        nvl(dm.estado,'G') <> 'A'
    UNION ALL
    SELECT
        nc.cod_documento AS codigo,
        nc.num_abono AS numero,
        to_number(nc.cod_motivo) AS codigo_interno,
        m.descripcion_motivo AS descripcion,
        1 AS cantidad,
        round(dnc.capital / ( (dismemayor.fun_get_porcentaje_iva / 100) + 1),2) AS precio_unitario,
        0 AS descuento,
        round(dnc.capital / ( (dismemayor.fun_get_porcentaje_iva / 100) + 1),2) AS precio_total_sin_impuesto,
        DECODE(dismemayor.fun_get_porcentaje_iva,14,'3',12,'2',0,'0') AS codigo_porcentaje,
        dismemayor.fun_get_porcentaje_iva AS porcentaje_iva,
        round( (dnc.capital * dismemayor.fun_get_porcentaje_iva) / (dismemayor.fun_get_porcentaje_iva + 100),2) AS valor_iva
    FROM
        dismemayor.cxc_abono_c nc
        INNER JOIN dismemayor.cxc_abono_d dnc ON nc.cod_empresa = dnc.cod_empresa
                                                 AND nc.cod_documento = dnc.cod_documento
                                                 AND nc.num_abono = dnc.num_abono
        INNER JOIN dismemayor.cxc_tipo_motivo m ON nc.cod_motivo = m.cod_motivo
    WHERE
        nc.cod_documento = 'NCC'
) SELECT
    ROWNUM AS id,
    codigo,
     TO_CHAR(numero,'fm000000000000000') AS numero,
    to_char(codigo_interno) as codigo_interno,
    descripcion,
    cantidad,
    precio_unitario,
    descuento,
    precio_total_sin_impuesto,
    codigo_porcentaje,
    porcentaje_iva,
    valor_iva
  FROM
    data;
