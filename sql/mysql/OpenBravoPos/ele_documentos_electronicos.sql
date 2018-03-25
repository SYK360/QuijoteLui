CREATE TABLE ele_documentos_electronicos (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  codigo varchar(20) NOT NULL,
  numero varchar(20) NOT NULL,
  numero_autorizacion varchar(100) DEFAULT NULL,
  fecha_autorizacion datetime DEFAULT NULL,
  observacion varchar(4000) DEFAULT NULL,
  estado varchar(20) DEFAULT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;