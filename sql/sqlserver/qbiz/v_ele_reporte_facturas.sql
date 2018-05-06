USE [quijotelui]
GO

/****** Object:  View [dbo].[v_ele_reporte_facturas]    Script Date: 6/5/2018 9:13:58 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE VIEW [dbo].[v_ele_reporte_facturas]
AS
SELECT        id, codigo, numero, ESTABLECIMIENTO, PUNTO_EMISION, SECUENCIAL, FECHA, TOTAL_SIN_IVA, TOTAL_CON_IVA, IVA, DESCUENTOS, TOTAL, DOCUMENTO, RAZON_SOCIAL,
                             (SELECT        TOP (1) valor
                               FROM            dbo.v_ele_informaciones AS i
                               WHERE        (nombre = 'Email') AND (documento = f.DOCUMENTO)) AS correo_electronico, ISNULL
                             ((SELECT        estado
                                 FROM            dbo.ele_documentos_electronicos AS e
                                 WHERE        (codigo = f.codigo) AND (numero = f.numero)), 'NO ENVIADO') AS estado
FROM            dbo.v_ele_facturas AS f
GO
