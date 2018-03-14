-- DROP FUNCTION fun_fecha_modificado;
delimiter //
CREATE FUNCTION fun_fecha_modificado(pId varchar(255)) RETURNS varchar(20) CHARSET utf8
BEGIN
	DECLARE vFechaModificado varchar(20);
    
	SELECT 
    DATE_FORMAT(re.DATENEW, '%d/%m/%Y')
INTO vFechaModificado FROM
    openbravo.REFUNDS r
        JOIN
    openbravo.TICKETS t ON r.ticket = t.id
        JOIN
    openbravo.RECEIPTS re ON t.id = re.id
WHERE
    r.id = pId;
    
    IF vFechaModificado is null THEN
      -- set vFechaModificado = DATE_FORMAT(now(), '%d/%m/%Y');
      set vFechaModificado = '01/01/2001';
    END IF;
        
RETURN vFechaModificado;
END//