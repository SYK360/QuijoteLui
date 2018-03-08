select 
cast(t.TICKETID as unsigned Integer) as id,
(select id from v_ele_contribuyentes) as id_contribuyente,
cast('FAC' as char(10)) as codigo,
cast(concat('001', '101', lpad(t.TICKETID, 9, '0')) as char(20)) as numero,
cast('01' as char(10)) as codigo_documento,
cast('001' as char(10)) as establecimiento,
cast('101' as char(10)) as punto_emision,
cast(lpad(t.TICKETID, 9, '0') as char(10)) as secuencial,
cast(r.DATENEW as date) as fecha,
cast(0 as decimal(19, 2)) as total_sin_iva,
cast(0 as decimal(19, 2)) as total_con_iva,
cast(0 as decimal(19, 2)) as iva,
cast(0 as decimal(19, 2)) as descuentos,
cast(0 as decimal(19, 2)) as total,
if(c.POSTAL = 'Consumidor Final', '07', 
	if(c.POSTAL = 'RUC', '04', 
		if(c.POSTAL = 'Cédula', '05', '06'))) as tipo_documento,
c.TAXID as documento,
c.NAME as razon_social,
c.ADDRESS as direccion,
cast(null as char(20)) as guia_remision,
cast('' as char(100)) as direccion_establecimiento

from openbravo.TICKETS t join openbravo.RECEIPTS r
on t.id = r.id
join openbravo.CUSTOMERS c
on c.id = t.CUSTOMER
join openbravo.TICKETLINES tl
on t.ID = tl.TICKET;
