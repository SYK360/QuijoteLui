CREATE OR REPLACE FORCE VIEW V_ELE_RETENCIONES ("ID", "ID_CONTRIBUYENTE", "CODIGO", "NUMERO", "CODIGO_DOCUMENTO", "ESTABLECIMIENTO", "PUNTO_EMISION", "SECUENCIAL", "FECHA", "TIPO_DOCUMENTO", "DOCUMENTO", "RAZON_SOCIAL", "PERIODO_FISCAL") AS 
  SELECT r.NUM_RETENCION AS ID,
  1 as id_contribuyente,
  CAST( 'RET' AS varchar2(3) ) AS codigo,
  TO_CHAR(r.NUM_RETENCION,'fm000000000000000') AS numero,
  CAST( '07' AS varchar2(2) ) codigo_documento,
  SUBSTR(TO_CHAR(r.NUM_RETENCION, 'fm000000000000000'), 1, 3) establecimiento,
  SUBSTR(TO_CHAR(r.NUM_RETENCION, 'fm000000000000000'), 4, 3) punto_emision,
  TO_CHAR(SUBSTR(TO_CHAR(r.NUM_RETENCION, 'fm000000000000000'), 7, 9)) secuencial,
  trunc(r.FECHA_RETENCION) AS FECHA,
  decode(p.DOCUMENTO,'9999999999999','07',decode(length(p.DOCUMENTO),13,'04',10,'05','06')) as tipo_documento,
  p.DOCUMENTO,
  p.RAZON_SOCIAL,
  TO_CHAR(r.FECHA_RETENCION, 'mm/rrrr') AS PERIODO_FISCAL
FROM DISMEMAYOR.BAN_RETENCION_C r
INNER JOIN DISMEMAYOR.V_PROVEEDOR p
ON r.COD_PROVEEDOR = p.COD_PROVEEDOR
where r.NUM_RETENCION > 1000000000000;
