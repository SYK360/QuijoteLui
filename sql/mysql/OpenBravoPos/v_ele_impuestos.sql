CREATE or replace VIEW `v_ele_impuestos` AS
SELECT
    CAST((t.TICKETID + tl.LINE) AS UNSIGNED INTEGER) AS id,
    CAST('FAC' AS CHAR (10)) AS codigo,
    CAST(CONCAT('001', '101', LPAD(t.TICKETID, 9, '0'))
        AS CHAR (20)) AS numero,
	cast('2' as char(10)) as codigo_impuesto,
    cast(IF((tx.RATE * 100) = 12, 2, 0) as char(10)) AS codigo_porcentaje,
    sum(cast((tl.UNITS * tl.PRICE) as DECIMAL (19 , 2 ))) AS base_imponible,    
    sum(CAST((tx.RATE * 100) AS DECIMAL (19 , 2 ))) AS tarifa,
    sum(CAST((tl.UNITS * tl.PRICE) * (tx.RATE + 1) AS DECIMAL (19 , 2 ))) AS valor
FROM
    openbravo.TICKETS t
        JOIN
    openbravo.TICKETLINES tl ON t.ID = tl.TICKET
        JOIN
    openbravo.TAXES tx ON tx.category = tl.taxid
    group by CAST((t.TICKETID + tl.LINE) AS UNSIGNED INTEGER),
    CAST('FAC' AS CHAR (10)),
    CAST(CONCAT('001', '101', LPAD(t.TICKETID, 9, '0'))
        AS CHAR (20)),
	cast('2' as char(10)),
    cast(IF((tx.RATE * 100) = 12, 2, 0) as char(10));