delimiter //
CREATE FUNCTION fun_establecimiento() RETURNS char(10) CHARSET utf8
BEGIN
	DECLARE establecimiento char(10);
	select valor 
    into establecimiento
    from ele_parametros
	where nombre = 'Establecimiento'
	and estado = 'Activo';
    
RETURN ifnull(establecimiento, '001');
END//