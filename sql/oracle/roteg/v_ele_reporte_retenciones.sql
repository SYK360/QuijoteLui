CREATE OR REPLACE FORCE VIEW V_ELE_REPORTE_RETENCIONES ("ID", "CODIGO", "NUMERO", "ESTABLECIMIENTO", "PUNTO_EMISION", "SECUENCIAL", "FECHA", "DOCUMENTO", "RAZON_SOCIAL", "CORREO_ELECTRONICO", "ESTADO") AS
  SELECT
    r.id,
    r.codigo,
    r.numero,
    r.establecimiento,
    r.punto_emision,
    r.secuencial,
    r.fecha,
    r.documento,
    r.razon_social,
    (
        SELECT
            informacionNotaDebito.valor
        FROM
            v_ele_informaciones informacionNotaDebito
        WHERE
                informacionNotaDebito.nombre = 'Email'
            AND
                informacionNotaDebito.documento = r.DOCUMENTO
            AND
                ROWNUM = 1
    ) correo_electronico,
    nvl((SELECT e.ESTADO FROM ELE_DOCUMENTOS_ELECTRONICOS e
where e.CODIGO = r.codigo
and e.numero = r.numero),'NO ENVIADO') estado
FROM
    v_ele_retenciones r
    order by r.id desc;