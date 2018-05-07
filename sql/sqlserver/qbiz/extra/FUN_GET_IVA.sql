USE [BDQualityV]
GO

SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO


ALTER FUNCTION [dbo].[FUN_GET_IVA]()
	RETURNS numeric(2)
AS
BEGIN
	DECLARE @v_IVA numeric(2);

	SELECT @v_IVA = Porcentaje
	FROM Impuesto
	where Empresa ='Activefun'
	and Impuesto='I601';
RETURN @v_IVA;

END;

GO
