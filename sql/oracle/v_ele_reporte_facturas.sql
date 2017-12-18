CREATE OR REPLACE FORCE VIEW V_ELE_REPORTE_FACTURAS ("ID", "CODIGO", "NUMERO", "ESTABLECIMIENTO", "PUNTO_EMISION", "SECUENCIAL", "FECHA", "TOTAL_SIN_IVA", "TOTAL_CON_IVA", "IVA", "DESCUENTOS", "TOTAL", "DOCUMENTO", "RAZON_SOCIAL", "CORREO_ELECTRONICO", "ESTADO") AS
  SELECT
    f.id,
    f.codigo,
    f.numero,
    f.establecimiento,
    f.punto_emision,
    f.secuencial,
    f.fecha,
    f.total_sin_iva,
    f.total_con_iva,
    f.iva,
    f.descuentos,
    f.total,
    f.documento,
    f.razon_social,
    (
        SELECT
            i.valor
        FROM
            v_ele_informaciones i
        WHERE
                i.nombre = 'Email'
            AND
                i.documento = f.DOCUMENTO
            AND
                ROWNUM = 1
    ) correo_electronico,
    nvl((SELECT e.ESTADO FROM ELE_DOCUMENTOS_ELECTRONICOS e
where e.CODIGO = f.codigo
and e.numero = f.numero),'NO AUTORIZADO') estado
FROM
    v_ele_facturas f;
