USE [quijotelui]
GO

SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO


CREATE VIEW [dbo].[v_ele_facturas_detalle]
AS
SELECT ROW_NUMBER() OVER(ORDER BY numero ASC) as id, codigo, numero, codigo_principal,
Descripcion, CAST(ROUND(Cantidad, 2) AS numeric(19, 2)) AS CANTIDAD,
CAST(ROUND(Precio_Uni, 2) AS numeric(19,2)) AS PRECIO_UNITARIO,
CASE WHEN Impuesto = 0 then cod_porc else BDQualityV.dbo.FUN_GET_CODIGO_IVA(BDQualityV.dbo.FUN_GET_IVA(codigo,numero)) end AS CODIGO_PORCENTAJE,
CASE WHEN Impuesto = 0 then porc_iva else CAST(BDQualityV.dbo.FUN_GET_IVA(codigo,numero) as numeric(19, 2)) end AS PORCENTAJE_IVA,
CAST(ROUND((Cantidad * (Precio_Uni-Descuento))*(CASE WHEN Impuesto = 0 then 0 else BDQualityV.dbo.FUN_GET_IVA(codigo,numero)/100 end), 2) AS numeric(19, 2)) AS VALOR_IVA,
CAST(ROUND((Cantidad * Precio_Uni)-(Cantidad * (Precio_Uni-Descuento)), 2) AS numeric(19, 2)) AS DESCUENTO,
CAST(ROUND(Cantidad * (Precio_Uni-Descuento), 2) AS numeric(19, 2)) AS PRECIO_TOTAL_SIN_IMPUESTO
FROM BDQualityV.dbo.fe_facturadetalle
GO
