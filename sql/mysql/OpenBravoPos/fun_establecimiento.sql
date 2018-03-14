-- DROP FUNCTION fun_establecimiento;
delimiter //
CREATE FUNCTION fun_establecimiento() RETURNS varchar(10) CHARSET utf8
BEGIN
	DECLARE establecimiento varchar(10);
	select valor 
    into establecimiento
    from ele_parametros
	where nombre = 'Establecimiento'
	and estado = 'Activo';
    
RETURN ifnull(establecimiento, '001');
END//