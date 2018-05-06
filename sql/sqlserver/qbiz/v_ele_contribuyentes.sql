USE [quijotelui]
GO

CREATE VIEW [dbo].[v_ele_contribuyentes]
AS
SELECT CAST(1 AS bigint) as id, REPLACE(RazonSocial, ',', ' ') AS RAZON_SOCIAL,
Nombre AS NOMBRE_COMERCIAL, CodLegal AS RUC, DIRECCION, 'SI' AS OBLIGADO_CONTABILIDAD,
                      cast(null as varchar) AS CONTRIBUYENTE_ESPECIAL
FROM         BDQualityV.dbo.Entidad AS e
WHERE     (TipoEntidad = '(miempresa)')
and (CodLegal = '1792379253001')
GO
