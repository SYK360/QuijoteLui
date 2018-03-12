-- DROP FUNCTION fun_modificado;
delimiter //
CREATE FUNCTION fun_modificado(pId char(255)) RETURNS char(20) CHARSET utf8
BEGIN
	DECLARE vModificado char(20);
    
	SELECT 
    t.TICKETID
INTO vModificado FROM
    openbravo.REFUNDS r
        JOIN
    openbravo.TICKETS t ON r.ticket = t.id
WHERE
    r.id = pId;
    
    IF vModificado is null THEN
      set vModificado = '1';
    END IF;
    
    set vModificado = concat(fun_establecimiento(), '-', fun_punto_emision(), '-', lpad(vModificado, 9 , '0'));
    
RETURN vModificado;
END//