CREATE TABLE ele_parametros (
  id bigint(20) NOT NULL,
  nombre varchar(255) DEFAULT NULL,
  valor varchar(255) DEFAULT NULL,
  observacion varchar(255) DEFAULT NULL,
  estado varchar(255) DEFAULT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
