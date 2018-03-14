DROP FUNCTION fun_direccion_establecimiento;
delimiter //
CREATE FUNCTION fun_direccion_establecimiento() RETURNS varchar(100) CHARSET utf8
BEGIN
	DECLARE vDireccion varchar(100);
    
SELECT 
    direccion
INTO vDireccion FROM
    v_ele_contribuyentes;	
        
RETURN vDireccion;
END//