CREATE VIEW `v_ele_reporte_notas_credito` AS
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
    (SELECT
            i.valor
        FROM
            quijotelui.v_ele_informaciones i
        WHERE
            ((i.nombre = 'Email')
                AND (i.documento = nc.documento))
        LIMIT 1) AS correo_electronico,
    IFNULL((SELECT
                    e.estado
                FROM
                    quijotelui.ele_documentos_electronicos e
                WHERE
                    ((e.codigo = nc.codigo)
                        AND (e.numero = nc.numero))),
            'NO ENVIADO') AS estado
FROM
    v_ele_notas_credito nc
ORDER BY nc.id DESC;