USE [quijotelui]
GO

SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO



CREATE VIEW [dbo].[v_ele_facturas]
AS
SELECT   ROW_NUMBER() OVER(ORDER BY numero ASC) as id,
CAST(1 AS bigint) as id_contribuyente,
codigo, numero, '01' AS CODIGO_DOCUMENTO, Noserie AS ESTABLECIMIENTO, Noserieemision AS PUNTO_EMISION,
RIGHT('000000000' + CONVERT(VARCHAR, Nosecuencial), 9) AS SECUENCIAL,
cast(fecha as date) AS FECHA,
CASE WHEN CAST(ROUND(Iva, 2) AS numeric(19, 2))
                      = 0 THEN CAST(ROUND(Total_con_Iva, 2) AS numeric(19, 2)) ELSE 0 END AS TOTAL_SIN_IVA,
CASE WHEN CAST(ROUND(Iva, 2) AS numeric(19, 2))
                      = 0 THEN 0 ELSE CAST(ROUND(Total_con_Iva, 2) AS numeric(19, 2)) END AS TOTAL_CON_IVA,
CAST(ROUND(Iva, 2) AS numeric(19, 2)) AS IVA,
                      CAST(ROUND(Descuento, 2) AS numeric(19, 2)) AS DESCUENTOS,
CAST(ROUND(/*Total*/(CASE WHEN CAST(ROUND(Iva, 2) AS numeric(19, 2))
                      = 0 THEN CAST(ROUND(Total_con_Iva, 2) AS numeric(19, 2)) ELSE 0 END)+(CASE WHEN CAST(ROUND(Iva, 2) AS numeric(19, 2))
                      = 0 THEN 0 ELSE CAST(ROUND(Total_con_Iva, 2) AS numeric(19, 2)) END)+ROUND(Iva, 2), 2) AS numeric(19, 2)) AS TOTAL,
Codigo_Cli AS TIPO_DOCUMENTO,
ltrim(rtrim(Documento_cli)) AS DOCUMENTO,
razonSocial AS RAZON_SOCIAL,
CASE WHEN Direccion='' then null else  Direccion end as DIRECCION,
GUIA_REMISION,
'VICTOR HUGO S/N Y MARCOS MONTALVO' AS DIRECCION_ESTABLECIMIENTO
FROM         BDQualityV.dbo.fe_facturamaestro1
GO


