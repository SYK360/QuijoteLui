CREATE OR REPLACE FORCE VIEW V_ELE_PAGOS ("ID", "CODIGO", "NUMERO", "FORMA_PAGO", "FORMA_PAGO_DESCRIPCION", "TOTAL", "PLAZO", "TIEMPO") AS
  SELECT
num_pago as id,
COD_DOCUMENTO AS CODIGO,
    TO_CHAR(num_pago,'fm000000000000000')        AS NUMERO,
    cast('01' as varchar2(2))                                     AS forma_pago,
    cast('SIN UTILIZACION DEL SISTEMA FINANCIERO' as varchar2(100)) AS forma_pago_descripcion,
    NVL(EFECTIVO,0)                          AS total,
    null PLAZO,
    null TIEMPO
  FROM
    DISMEMAYOR.CXC_PAGO_CONTADO
  WHERE
    COD_DOCUMENTO    ='FAC'
  AND NVL(EFECTIVO,0)>0
   and num_pago>1001000000000
  UNION
  SELECT
  num_pago,
COD_DOCUMENTO,
    TO_CHAR(num_pago,'fm000000000000000'),
    '19',
    'TARJETA DE CRÉDITO',
    NVL(tarjeta,0),
    null,
    null
  FROM
    DISMEMAYOR.CXC_PAGO_CONTADO
  WHERE
    COD_DOCUMENTO   ='FAC'
  AND NVL(tarjeta,0)>0
  and num_pago>1001000000000
  UNION
  SELECT
  num_pago,
COD_DOCUMENTO,
    TO_CHAR(num_pago,'fm000000000000000'),
    '20',
    'OTROS CON UTILIZACION DEL SISTEMA FINANCIERO',
    NVL(CHEQUES,0),
    null,
    null
  FROM
    DISMEMAYOR.CXC_PAGO_CONTADO
  WHERE
    COD_DOCUMENTO   ='FAC'
  AND NVL(CHEQUES,0)>0
  and num_pago>1001000000000
  UNION
  SELECT
  num_pago,
COD_DOCUMENTO,
    TO_CHAR(num_pago,'fm000000000000000'),
    '20',
    'OTROS CON UTILIZACION DEL SISTEMA FINANCIERO',
    NVL(DEPOSITO,0),
    null,
    null
  FROM
    DISMEMAYOR.CXC_PAGO_CONTADO
  WHERE
    COD_DOCUMENTO    ='FAC'
  AND NVL(DEPOSITO,0)>0
  and num_pago>1001000000000
  UNION
  SELECT
   num_pago,
COD_DOCUMENTO,
    TO_CHAR(num_pago,'fm000000000000000'),
    '20',
    'OTROS CON UTILIZACION DEL SISTEMA FINANCIERO',
    NVL(f.credito,0),
  cast((
      SELECT
        max(cxc.DIAS_PLAZO)
      FROM
        DISMEMAYOR.CXC_DOC_COBRAR cxc
      WHERE
        cxc.COD_DOCUMENTO  ='FAC'
      AND cxc.NUM_DOCUMENTO=f.num_pago
    ) as VARCHAR2(5)),
    'Días'
  FROM
    DISMEMAYOR.CXC_PAGO_CONTADO f
  WHERE
    f.COD_DOCUMENTO   ='FAC'
  AND NVL(f.credito,0)>0
  and num_pago>1001000000000
  UNION
  SELECT
  num_pago,
COD_DOCUMENTO,
    TO_CHAR(num_pago,'fm000000000000000'),
    '20',
    'OTROS CON UTILIZACION DEL SISTEMA FINANCIERO',
    NVL(f.OTROS,0),
    null,
    null
  FROM
    DISMEMAYOR.CXC_PAGO_CONTADO f
  WHERE
    f.COD_DOCUMENTO   ='FAC'
  AND NVL(f.OTROS,0)>0
  and num_pago>1001000000000;
