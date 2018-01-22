CREATE OR REPLACE FORCE VIEW V_ELE_REPORTE_NOTAS_CREDITO ("ID", "CODIGO", "NUMERO", "ESTABLECIMIENTO", "PUNTO_EMISION", "SECUENCIAL", "FECHA", "DOCUMENTO", "RAZON_SOCIAL", "DOCUMENTO_MODIFICADO", "TOTAL_SIN_IMPUESTOS", "TOTAL_SIN_IVA", "TOTAL_CON_IVA", "IVA", "CORREO_ELECTRONICO", "ESTADO") AS
  SELECT
    nc.id,
    nc.codigo,
    nc.numero,
    nc.establecimiento,
    nc.punto_emision,
    nc.secuencial,
    nc.fecha,
    nc.documento,
    nc.razon_social,
    nc.documento_modificado,
    nc.total_sin_impuestos,
    nc.total_sin_iva,
    nc.total_con_iva,
    nc.iva,
    (
        SELECT
            i.valor
        FROM
            v_ele_informaciones i
        WHERE
                i.nombre = 'Email'
            AND
                i.documento = nc.DOCUMENTO
            AND
                ROWNUM = 1
    ) correo_electronico,
    nvl((SELECT e.ESTADO FROM ELE_DOCUMENTOS_ELECTRONICOS e
    where e.CODIGO = nc.codigo
and e.numero = nc.numero),'NO ENVIADO') estado
FROM
    v_ele_notas_credito nc
     order by nc.id desc;
