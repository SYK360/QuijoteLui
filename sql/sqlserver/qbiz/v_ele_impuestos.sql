USE [quijotelui]
GO

SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE VIEW [dbo].[v_ele_impuestos]
AS
select ROW_NUMBER() OVER(ORDER BY numero ASC) as id,
codigo, numero,
cast('2' as varchar) AS codigo_impuesto,
cod_porc as codigo_porcentaje,
sum(CAST(ROUND(Cantidad * (Precio_Uni-Descuento), 2) AS numeric(19, 2))) AS base_imponible,
max(porc_iva) AS tarifa,
sum(CAST(ROUND(Cantidad * (Precio_Uni-Descuento) * (porc_iva/100), 2) AS numeric(19, 2))) AS valor
from BDQualityV.dbo.fe_facturadetalle
group by codigo, numero,
BDQualityV.dbo.fe_facturadetalle.cod_porc
GO


