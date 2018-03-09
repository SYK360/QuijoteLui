CREATE VIEW `v_ele_reporte_facturas` AS
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
    (SELECT 
            i.valor
        FROM
            v_ele_informaciones i
        WHERE
            i.nombre = 'Email'
                AND i.documento = f.DOCUMENTO
        LIMIT 1) correo_electronico,
    IFNULL((SELECT 
                    e.ESTADO
                FROM
                    ele_documentos_electronicos e
                WHERE
                    e.CODIGO = f.codigo
                        AND e.numero = f.numero),
            'NO ENVIADO') estado
FROM
    v_ele_facturas f;