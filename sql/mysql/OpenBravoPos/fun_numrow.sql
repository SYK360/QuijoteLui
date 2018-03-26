delimiter //
CREATE FUNCTION `fun_numrow`() RETURNS int
    NO SQL
    NOT DETERMINISTIC
     begin
      SET @var := ifnull(@var, 0) + 1;
      return @var;
     end
     //
delimiter ;