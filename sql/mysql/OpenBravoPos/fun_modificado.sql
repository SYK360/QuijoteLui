-- DROP FUNCTION fun_modificado;
delimiter //
CREATE FUNCTION fun_modificado(pId varchar(255)) RETURNS varchar(20) CHARSET utf8
BEGIN
	DECLARE vModificado varchar(20);
    
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