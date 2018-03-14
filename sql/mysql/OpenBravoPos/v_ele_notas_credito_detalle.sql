CREATE OR REPLACE VIEW `v_ele_notas_credito_detalle` AS
    SELECT 
        CAST(CONCAT(t.TICKETID, tl.LINE) AS UNSIGNED INTEGER) AS id,
        CAST('NCV' AS CHAR (10)) AS codigo,
        CAST(CONCAT(FUN_ESTABLECIMIENTO(),
                    FUN_PUNTO_EMISION(),
                    LPAD(t.TICKETID, 9, '0'))
            AS CHAR (20)) AS numero,
        p.REFERENCE AS codigo_interno,
        p.name AS descripcion,
        CAST(ABS(tl.UNITS) AS DECIMAL (19 , 2 )) AS cantidad,
        CAST(tl.PRICE AS DECIMAL (19 , 2 )) AS precio_unitario,
        CAST(0 AS DECIMAL (19 , 2 )) AS descuento,
        CAST(ABS(tl.UNITS * tl.PRICE) AS DECIMAL (19 , 2 )) AS precio_total_sin_impuesto,
        CAST(IF((tx.RATE * 100) = 12, 2, 0) AS CHAR (10)) AS codigo_porcentaje,
        CAST((tx.RATE * 100) AS DECIMAL (19 , 2 )) AS porcentaje_iva,
        CAST(ABS(tl.UNITS * tl.PRICE * tx.RATE) AS DECIMAL (19 , 2 )) AS valor_iva
    FROM
        openbravo.TICKETS t
            JOIN
        openbravo.TICKETLINES tl ON t.ID = tl.TICKET
            JOIN
        openbravo.TAXES tx ON tx.category = tl.taxid
            JOIN
        openbravo.PRODUCTS p ON p.id = tl.product
    WHERE
        t.tickettype = 1;