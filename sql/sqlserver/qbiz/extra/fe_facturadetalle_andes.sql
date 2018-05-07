USE [BDQualityV]
GO

SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

ALTER VIEW [dbo].[fe_facturadetalle]
AS
SELECT     'FAC' AS codigo,
'005' + dbo.FUN_PUNTO( d.TipoDocumento ) + REPLICATE( '0', 9 - LEN( d.Folio )) +  convert(varchar, Folio) as numero,
t.Item AS codigo_principal, i.Descripcion,
                      t.Cantidad, t.Precio AS Precio_Uni,
dbo.FUN_GET_IVA() AS porc_iva,
					    dbo.FUN_GET_CODIGO_IVA(dbo.FUN_GET_IVA()) AS cod_porc,
					   d.Impuesto, t.Precio * t.PorcentajeDR / 100 * - 1 AS Descuento,
                      t.Precio - t.Precio * t.PorcentajeDR / 100 * - 1 AS Precio_total_sin_imp
FROM         dbo.Documento AS d INNER JOIN
                      dbo.DocumentoDet AS t ON d.Empresa = t.Empresa AND d.TipoDocumento = t.TipoDocumento AND d.Correlativo = t.Correlativo INNER JOIN
                      dbo.Item AS i ON t.Empresa = i.Empresa AND t.Item = i.Item AND t.TipoItem = i.TipoItem
WHERE     (d.Empresa = 'Activefun')
AND (d.TipoDocumento IN (
'FACTURA PTO VENTA 1',
'FACTURA PTO VENTA 2',
'FACTURA PTO VENTA 3',
'FACTURA PTO VENTA 4',
'FACTURA PTO VENTA 5',
'FACTURA PTO VENTA 6',
'FACTURA VENTA SALIDA',
'FACTURA PTO CAFET',
'FACT. PTO CAFET 2',
'FACTURA CUMPLEANOS',
'FACTURA PTO SHOP')) AND (d.Estado = 'S')

GO
