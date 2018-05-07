USE [quijotelui]
GO

SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO


CREATE VIEW [dbo].[v_ele_informaciones]
AS

with informaciones as (
select
CodLegal as documento, 'Email' as nombre, lower(email) as valor from BDQualityV.dbo.Entidad
where email like '%@%'
union all
select
CodLegal as documento, 'Dirección' as nombre, Direccion as valor from BDQualityV.dbo.Entidad
union all
select
CodLegal as documento, 'Teléfono' as nombre, Fono as valor from BDQualityV.dbo.Entidad
where Fono not like '%[^0-9]%')
select ROW_NUMBER() OVER(ORDER BY documento ASC) as id, documento, nombre, valor from informaciones

GO
