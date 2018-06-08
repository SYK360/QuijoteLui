CREATE OR REPLACE VIEW `v_ele_impuestos` AS
    SELECT 
        CAST(CONCAT(t.TICKETID, tl.LINE) AS UNSIGNED) AS id,
        CAST(if(t.tickettype = 0, 'FV', 'NCV') AS CHAR (10) ) AS codigo,
        CAST(CONCAT(FUN_ESTABLECIMIENTO(),
                    FUN_PUNTO_EMISION(),
                    LPAD(t.TICKETID, 9, '0'))
            AS CHAR (20) ) AS numero,
        CAST('2' AS CHAR (10) ) AS codigo_impuesto,
        CAST(IF(((tx.RATE * 100) = 12), 2, 0) AS CHAR (10) ) AS codigo_porcentaje,
        SUM(CAST(abs((tl.UNITS * tl.PRICE)) AS DECIMAL (19 , 2 ))) AS base_imponible,
        SUM(CAST((tx.RATE * 100) AS DECIMAL (19 , 2 ))) AS tarifa,
        SUM(CAST(if(tx.RATE = 0, 0,abs(((tl.UNITS * tl.PRICE) * (tx.RATE))))
            AS DECIMAL (19 , 2 ))) AS valor
    FROM
        ((openbravo.TICKETS t
        JOIN openbravo.TICKETLINES tl ON ((t.ID = tl.TICKET)))
        JOIN openbravo.TAXES tx ON ((tx.CATEGORY = tl.TAXID)))

    GROUP BY CAST(if(t.tickettype = 0, 'FV', 'NCV') AS CHAR (10) ) , CAST(CONCAT(FUN_ESTABLECIMIENTO(),
                FUN_PUNTO_EMISION(),
                LPAD(t.TICKETID, 9, '0'))
        AS CHAR (20) ) , CAST('2' AS CHAR (10) ) , CAST(IF(((tx.RATE * 100) = 12), 2, 0) AS CHAR (10) );
