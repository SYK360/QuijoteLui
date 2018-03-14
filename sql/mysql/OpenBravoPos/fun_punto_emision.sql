-- drop function fun_punto_emision;
delimiter //
CREATE FUNCTION fun_punto_emision() RETURNS varchar(10) CHARSET utf8
BEGIN
	DECLARE punto_emision varchar(10);
	select valor 
    into punto_emision
    from ele_parametros
	where nombre = 'Punto Emisión'
	and estado = 'Activo';
    
RETURN ifnull(punto_emision, '001');
END//