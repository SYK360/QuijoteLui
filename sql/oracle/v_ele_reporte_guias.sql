CREATE OR REPLACE FORCE VIEW V_ELE_REPORTE_GUIAS ("ID", "CODIGO", "NUMERO", "ESTABLECIMIENTO", "PUNTO_EMISION", "SECUENCIAL", "FECHA", "RAZON_SOCIAL_TRANSPORTISTA", "DOCUMENTO", "PLACA", "ESTADO") AS
  SELECT
    g.id,
    g.codigo,
    g.numero,
    g.establecimiento,
    g.punto_emision,
    g.secuencial,
    g.fecha,
    g.razon_social_transportista,
    g.documento,
    g.placa,
    nvl((SELECT e.ESTADO FROM ELE_DOCUMENTOS_ELECTRONICOS e
    where e.CODIGO = g.codigo
and e.numero = g.numero),'NO ENVIADO') estado
FROM
    v_ele_guias g
     order by g.id desc;
