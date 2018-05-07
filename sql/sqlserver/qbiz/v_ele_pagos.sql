USE [quijotelui]
GO

SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE VIEW [dbo].[v_ele_pagos]
AS
SELECT ROW_NUMBER() OVER(ORDER BY '005' + BDQualityV.dbo.FUN_PUNTO( d.TipoDocumento ) + REPLICATE( '0', 9 - LEN( d.Folio )) +  convert(varchar, Folio) ASC) as id,
'FAC' as codigo,
    '005' + BDQualityV.dbo.FUN_PUNTO( d.TipoDocumento ) + REPLICATE( '0', 9 - LEN( d.Folio )) +  convert(varchar, Folio)  as numero,
	CASE WHEN d.Clasif19 = '' THEN '01'
	 when d.Clasif19 in ('01','16','19','20') then d.Clasif19
     ELSE '20' END as FORMA_PAGO,
	CASE WHEN d.Clasif19 in ('','01') THEN 'SIN UTILIZACION DEL SISTEMA FINANCIERO'
	 when d.Clasif19 = '16' then 'TARJETA DE DÉBITO'
	 when d.Clasif19 = '19' then 'TARJETA DE CRÉDITO'
	 when d.Clasif19 = '20' then 'OTROS CON UTILIZACION DEL SISTEMA FINANCIERO'
     ELSE 'OTROS CON UTILIZACION DEL SISTEMA FINANCIERO' END as FORMA_PAGO_DESCRIPCION,
	 f.Monto AS TOTAL,
	 cast(0 as varchar) AS PLAZO,
    'Dias' AS TIEMPO
FROM
	BDQualityV.dbo.Documento d
INNER JOIN BDQualityV.dbo.DocumentoVal f ON
	d.Empresa = f.Empresa
	AND d.TipoDocumento = f.TipoDocumento
	AND d.Correlativo = f.Correlativo

	and d.Folio > 0
	/*
	and f.Monto > 0
	*/
	and f.nombre in(
		'SUBTOTAL_CEN'
	)
	and d.empresa = 'Activefun'
	and d.TipoDocumento in(
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
		'FACTURA PTO SHOP'
	)
	and d.Estado = 'S'
	and f.Monto > 0

GO
