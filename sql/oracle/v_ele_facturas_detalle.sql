CREATE OR REPLACE FORCE VIEW V_ELE_FACTURAS_DETALLE ("ID", "CODIGO", "NUMERO", "CODIGO_PRINCIPAL", "DESCRIPCION", "CANTIDAD", "PRECIO_UNITARIO", "CODIGO_PORCENTAJE", "PORCENTAJE_IVA", "VALOR_IVA", "DESCUENTO", "PRECIO_TOTAL_SIN_IMPUESTO") AS 
  with data as(
SELECT f.NUM_FACTURA as id,f.COD_DOCUMENTO AS CODIGO,
  TO_CHAR(f.NUM_FACTURA,'fm000000000000000')        AS NUMERO,
  a.COD_ARTICULO AS Codigo_Principal,
  a.NOMBRE_ARTICULO AS Descripcion,
  fd.CANTIDAD AS CANTIDAD,
  round((fd.PRECIO_UNITARIO),6) as PRECIO_UNITARIO,
  decode(fd.PORCENTAJE_IVA,14,'3',12,'2',0,'0') as Codigo_Porcentaje,
  fd.PORCENTAJE_IVA,
  round((((fd.CANTIDAD*(fd.PRECIO_UNITARIO))-((fd.precio_unitario*fd.CANTIDAD)-(((fd.precio_unitario*fd.CANTIDAD-((NVL(fd.PORC_DESC_VOL,0)/100)*fd.precio_unitario*fd.CANTIDAD))-(NVL(fd.PORC_DESC_PAGO,0)/100)*(fd.precio_unitario*fd.CANTIDAD-((NVL(fd.PORC_DESC_VOL,0)/100)*fd.precio_unitario*fd.CANTIDAD)))-((NVL(fd.PORC_DESC_PROM,0)/100)*((fd.precio_unitario*fd.CANTIDAD-((NVL(fd.PORC_DESC_VOL,0)/100)*fd.precio_unitario*fd.CANTIDAD))-(NVL(fd.PORC_DESC_PAGO,0)/100)*(fd.precio_unitario*fd.CANTIDAD-((NVL(fd.PORC_DESC_VOL,0)/100)*fd.precio_unitario*fd.CANTIDAD)))))))*fd.PORCENTAJE_IVA/100),2) as VALOR_IVA,
  round((fd.precio_unitario*fd.CANTIDAD)-(((fd.precio_unitario*fd.CANTIDAD-((NVL(fd.PORC_DESC_VOL,0)/100)*fd.precio_unitario*fd.CANTIDAD))-(NVL(fd.PORC_DESC_PAGO,0)/100)*(fd.precio_unitario*fd.CANTIDAD-((NVL(fd.PORC_DESC_VOL,0)/100)*fd.precio_unitario*fd.CANTIDAD)))-((NVL(fd.PORC_DESC_PROM,0)/100)*((fd.precio_unitario*fd.CANTIDAD-((NVL(fd.PORC_DESC_VOL,0)/100)*fd.precio_unitario*fd.CANTIDAD))-(NVL(fd.PORC_DESC_PAGO,0)/100)*(fd.precio_unitario*fd.CANTIDAD-((NVL(fd.PORC_DESC_VOL,0)/100)*fd.precio_unitario*fd.CANTIDAD))))),2) descuento,
  round((fd.precio_unitario*fd.CANTIDAD)-((fd.precio_unitario*fd.CANTIDAD)-(((fd.precio_unitario*fd.CANTIDAD-((NVL(fd.PORC_DESC_VOL,0)/100)*fd.precio_unitario*fd.CANTIDAD))-(NVL(fd.PORC_DESC_PAGO,0)/100)*(fd.precio_unitario*fd.CANTIDAD-((NVL(fd.PORC_DESC_VOL,0)/100)*fd.precio_unitario*fd.CANTIDAD)))-((NVL(fd.PORC_DESC_PROM,0)/100)*((fd.precio_unitario*fd.CANTIDAD-((NVL(fd.PORC_DESC_VOL,0)/100)*fd.precio_unitario*fd.CANTIDAD))-(NVL(fd.PORC_DESC_PAGO,0)/100)*(fd.precio_unitario*fd.CANTIDAD-((NVL(fd.PORC_DESC_VOL,0)/100)*fd.precio_unitario*fd.CANTIDAD)))))),2) as Precio_Total_Sin_Impuesto
FROM DISMEMAYOR.FAC_FACTURA_C f
INNER JOIN DISMEMAYOR.FAC_FACTURA_D fd
ON f.COD_EMPRESA    = fd.COD_EMPRESA
AND f.COD_DOCUMENTO = fd.COD_DOCUMENTO
AND f.NUM_FACTURA   = fd.NUM_FACTURA
INNER JOIN DISMEMAYOR.INV_ARTICULO a
ON fd.COD_ARTICULO = a.COD_ARTICULO
where f.NUM_FACTURA>1001000000000
and f.COD_DOCUMENTO='FAC'
union
  SELECT f.NUM_FACTURA as id,f.COD_DOCUMENTO AS CODIGO,
  TO_CHAR(f.NUM_FACTURA,'fm000000000000000')        AS NUMERO,
  to_number(a.COD_PRODUCTO) AS Codigo_Principal,
  a.DESC_PRODUCTO AS Descripcion,
  fd.CANTIDAD AS CANTIDAD,
  round((fd.PRECIO_UNITARIO),6) as PRECIO_UNITARIO,
  decode(fd.PORCENTAJE_IVA,14,'3',12,'2',0,'0') as Codigo_Porcentaje,
  fd.PORCENTAJE_IVA,
  round((((fd.CANTIDAD*(fd.PRECIO_UNITARIO))-((fd.precio_unitario*fd.CANTIDAD)-(((fd.precio_unitario*fd.CANTIDAD-((NVL(fd.PORC_DESC_VOL,0)/100)*fd.precio_unitario*fd.CANTIDAD))-(NVL(fd.PORC_DESC_PAGO,0)/100)*(fd.precio_unitario*fd.CANTIDAD-((NVL(fd.PORC_DESC_VOL,0)/100)*fd.precio_unitario*fd.CANTIDAD)))-((NVL(fd.PORC_DESC_PROM,0)/100)*((fd.precio_unitario*fd.CANTIDAD-((NVL(fd.PORC_DESC_VOL,0)/100)*fd.precio_unitario*fd.CANTIDAD))-(NVL(fd.PORC_DESC_PAGO,0)/100)*(fd.precio_unitario*fd.CANTIDAD-((NVL(fd.PORC_DESC_VOL,0)/100)*fd.precio_unitario*fd.CANTIDAD)))))))*fd.PORCENTAJE_IVA/100),2) as VALOR_IVA,
  round((fd.precio_unitario*fd.CANTIDAD)-(((fd.precio_unitario*fd.CANTIDAD-((NVL(fd.PORC_DESC_VOL,0)/100)*fd.precio_unitario*fd.CANTIDAD))-(NVL(fd.PORC_DESC_PAGO,0)/100)*(fd.precio_unitario*fd.CANTIDAD-((NVL(fd.PORC_DESC_VOL,0)/100)*fd.precio_unitario*fd.CANTIDAD)))-((NVL(fd.PORC_DESC_PROM,0)/100)*((fd.precio_unitario*fd.CANTIDAD-((NVL(fd.PORC_DESC_VOL,0)/100)*fd.precio_unitario*fd.CANTIDAD))-(NVL(fd.PORC_DESC_PAGO,0)/100)*(fd.precio_unitario*fd.CANTIDAD-((NVL(fd.PORC_DESC_VOL,0)/100)*fd.precio_unitario*fd.CANTIDAD))))),2) descuento,
  round((fd.precio_unitario*fd.CANTIDAD)-((fd.precio_unitario*fd.CANTIDAD)-(((fd.precio_unitario*fd.CANTIDAD-((NVL(fd.PORC_DESC_VOL,0)/100)*fd.precio_unitario*fd.CANTIDAD))-(NVL(fd.PORC_DESC_PAGO,0)/100)*(fd.precio_unitario*fd.CANTIDAD-((NVL(fd.PORC_DESC_VOL,0)/100)*fd.precio_unitario*fd.CANTIDAD)))-((NVL(fd.PORC_DESC_PROM,0)/100)*((fd.precio_unitario*fd.CANTIDAD-((NVL(fd.PORC_DESC_VOL,0)/100)*fd.precio_unitario*fd.CANTIDAD))-(NVL(fd.PORC_DESC_PAGO,0)/100)*(fd.precio_unitario*fd.CANTIDAD-((NVL(fd.PORC_DESC_VOL,0)/100)*fd.precio_unitario*fd.CANTIDAD)))))),2) as Precio_Total_Sin_Impuesto
FROM DISMEMAYOR.FAC_FACTURA_C f
INNER JOIN DISMEMAYOR.FAC_FACTURA_D fd
ON f.COD_EMPRESA    = fd.COD_EMPRESA
AND f.COD_DOCUMENTO = fd.COD_DOCUMENTO
AND f.NUM_FACTURA   = fd.NUM_FACTURA
INNER JOIN DISMEMAYOR.INV_PRODUCTOS a
ON fd.COD_ARTICULO = a.COD_PRODUCTO
where f.NUM_FACTURA>1001000000000
and f.COD_DOCUMENTO='FAC'
)
select  ID,CODIGO,
  NUMERO,
   Codigo_Principal,
  Descripcion,
  CANTIDAD,
  PRECIO_UNITARIO,
  Codigo_Porcentaje,
  PORCENTAJE_IVA,
   VALOR_IVA,
   descuento,
  Precio_Total_Sin_Impuesto
  from data;
