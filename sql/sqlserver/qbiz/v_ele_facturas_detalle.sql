USE [quijotelui]
GO

SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO


CREATE VIEW [dbo].[v_ele_facturas_detalle]
AS
AS
SELECT ROW_NUMBER() OVER(ORDER BY numero ASC) as id, codigo, numero, codigo_principal,
Descripcion, CAST(ROUND(Cantidad, 2) AS numeric(19, 2)) AS CANTIDAD,
CAST(ROUND(Precio_Uni, 2) AS numeric(19,2)) AS PRECIO_UNITARIO,
cod_porc AS CODIGO_PORCENTAJE,
porc_iva PORCENTAJE_IVA,
CAST(ROUND((Cantidad * (Precio_Uni-Descuento))*(CASE WHEN Impuesto = 0 then 0 else porc_iva/100 end), 2) AS numeric(19, 2)) AS VALOR_IVA,
CAST(ROUND((Cantidad * Precio_Uni)-(Cantidad * (Precio_Uni-Descuento)), 2) AS numeric(19, 2)) AS DESCUENTO,
CAST(ROUND(Cantidad * (Precio_Uni-Descuento), 2) AS numeric(19, 2)) AS PRECIO_TOTAL_SIN_IMPUESTO
FROM BDQualityV.dbo.fe_facturadetalle
GO
