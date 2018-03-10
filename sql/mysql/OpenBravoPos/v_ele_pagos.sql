CREATE OR REPLACE VIEW `v_ele_pagos` AS
    SELECT 
        CAST(CONCAT(t.TICKETID, RAND() * 100) AS UNSIGNED INTEGER) AS id,
        CAST('FAC' AS CHAR (10)) AS codigo,
        CAST(CONCAT('001', '101', LPAD(t.TICKETID, 9, '0'))
            AS CHAR (20)) AS numero,
        CAST(IF(p.PAYMENT = 'cash', '01', '20') AS CHAR (10)) AS forma_pago,
        CAST(IF(p.PAYMENT = 'cash',
                'SIN UTILIZACION DEL SISTEMA FINANCIERO',
                'OTROS CON UTILIZACION DEL SISTEMA
                            FINANCIERO')
            AS CHAR (100)) AS forma_pago_descripcion,
        CAST(p.TOTAL AS DECIMAL (19 , 2 )) AS total,
        CAST(NULL AS CHAR (20)) AS plazo,
        CAST(NULL AS CHAR (20)) AS tiempo
    FROM
        openbravo.TICKETS t
            JOIN
        openbravo.RECEIPTS r ON t.id = r.id
            JOIN
        openbravo.PAYMENTS p ON r.id = p.receipt
    WHERE
        t.tickettype = 0;
