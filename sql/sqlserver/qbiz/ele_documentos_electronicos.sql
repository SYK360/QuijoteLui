USE [quijotelui]
GO

SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[ele_documentos_electronicos](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[codigo] [varchar](255) NOT NULL,
	[numero] [varchar](255) NOT NULL,
	[numero_autorizacion] [varchar](255) NULL,
	[fecha_autorizacion] [datetime2](7) NULL,
	[observacion] [varchar](255) NULL,
	[estado] [varchar](255) NULL,
PRIMARY KEY CLUSTERED
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
