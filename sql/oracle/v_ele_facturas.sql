  CREATE OR REPLACE FORCE VIEW "JORGE"."V_ELE_FACTURAS" ("ID", "CODIGO", "NUMERO", "CODIGO_DOCUMENTO", "ESTABLECIMIENTO", "PUNTO_EMISION", "SECUENCIAL", "FECHA", "TOTAL_SIN_IVA", "TOTAL_CON_IVA", "IVA", "DESCUENTOS", "TOTAL", "TIPO_DOCUMENTO", "DOCUMENTO", "RAZON_SOCIAL", "GUIA_REMISION") AS
  SELECT
    f.NUM_FACTURA as id,
    f.COD_DOCUMENTO AS CODIGO,
    TO_CHAR(f.NUM_FACTURA,'fm000000000000000')        AS NUMERO,
    CAST( '01' AS varchar2(2) ) codigo_documento,
    SUBSTR(TO_CHAR(f.NUM_FACTURA,'fm000000000000000'),1,3) establecimiento,
    SUBSTR(TO_CHAR(f.NUM_FACTURA,'fm000000000000000'),4,3) punto_emision,
    to_char(SUBSTR(TO_CHAR(f.NUM_FACTURA,'fm000000000000000'),7,9)) secuencial,
    trunc(f.FECHA_FACTURA) as FECHA,
    round(f.TOTAL_SIN_IVA,2) as TOTAL_SIN_IVA,
    round(f.TOTAL_CON_IVA,2)-f.DESCUENTOS as TOTAL_CON_IVA,
    round(f.IVA,2) as IVA,
    round(f.DESCUENTOS,2) as DESCUENTOS,
    round(f.TOTAL_FACTURA,2) as TOTAL,
    decode(c.DOCUMENTO,'9999999999999','07',decode(length(c.DOCUMENTO),13,'04',10,'05','06')) as tipo_documento,
    c.DOCUMENTO,
    c.RAZON_SOCIAL,
    DISMEMAYOR.PKG_INFO_FACTURA.FUN_NUMERO_GUIA_REMISION(f.cod_documento,f.num_factura) as GUIA_REMISION
  FROM DISMEMAYOR.FAC_FACTURA_C f
  INNER JOIN DISMEMAYOR.V_CLIENTE c
  ON f.COD_CLIENTE = c.COD_CLIENTE
  where nvl(f.estado,'G')<>'A'
  and f.NUM_FACTURA>1001000000000;
