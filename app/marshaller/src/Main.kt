import comprobantes.InformacionTributaria
import comprobantes.factura.*
import java.io.FileOutputStream
import java.io.OutputStreamWriter
import java.io.StringWriter
import java.math.BigDecimal
import javax.xml.bind.JAXBContext
import javax.xml.bind.Marshaller

fun main(args: Array<String>) {

    val informacionTributaria = InformacionTributaria()
    informacionTributaria.ambiente = "1"
    informacionTributaria.tipoEmision = "1"
    informacionTributaria.razonSocial = "Quiguango Jorge Luis"
    informacionTributaria.nombreComercial = "QuijoteLui"
    informacionTributaria.ruc = "102245612701"
    informacionTributaria.claveAcceso = "0000000000000000000000000000000000000000000"
    informacionTributaria.codDoc = "01"
    informacionTributaria.estab = "001"
    informacionTributaria.ptoEmi = "010"
    informacionTributaria.secuencial = "000000013"
    informacionTributaria.dirMatriz = "Cananvalle Calle Las Uvillas y Calle Cananvalle"


    val informacionFactura = InformacionFactura()
    informacionFactura.fechaEmision = "03/10/2017"
    informacionFactura.dirEstablecimiento = informacionTributaria.dirMatriz
    informacionFactura.obligadoContabilidad = "NO"
    informacionFactura.tipoIdentificacionComprador = "05"
    informacionFactura.razonSocialComprador = "Arenita"
    informacionFactura.identificacionComprador = "1002345644"
    informacionFactura.direccionComprador = "Priorato"
    informacionFactura.totalSinImpuestos = BigDecimal(10).setScale(2, BigDecimal.ROUND_HALF_UP)
    informacionFactura.totalDescuento = BigDecimal(0).setScale(2, BigDecimal.ROUND_HALF_UP)

    val totalImpuesto = TotalImpuesto()
    totalImpuesto.codigo = "2"
    totalImpuesto.codigoPorcentaje = "2"
    totalImpuesto.baseImponible = BigDecimal(10).setScale(2, BigDecimal.ROUND_HALF_UP)
    totalImpuesto.tarifa = BigDecimal(12).setScale(0, BigDecimal.ROUND_HALF_UP)
    totalImpuesto.valor = BigDecimal(1.20).setScale(2, BigDecimal.ROUND_HALF_UP)

    var totalConImpuestos = TotalConImpuestos()
    totalConImpuestos.setTotalImpuesto(totalImpuesto)

    informacionFactura.setTotalConImpuestos(totalConImpuestos)

    informacionFactura.setPropina(BigDecimal(0).setScale(2, BigDecimal.ROUND_HALF_UP))
    informacionFactura.setImporteTotal(BigDecimal(11.20).setScale(2, BigDecimal.ROUND_HALF_UP))
    informacionFactura.setMoneda("DOLAR")

    val pago = Pago()
    pago.formaPago = "20"
    pago.total = BigDecimal(11.20).setScale(2, BigDecimal.ROUND_HALF_UP)

    var pagos = Pagos()
    pagos.setPago(pago)

    informacionFactura.setPagos(pagos)

    val factura = Factura()

    factura.setId(id = "comprobante")
    factura.setVersion(version = "1.0.0")

    factura.setInformacionTributaria(informacionTributaria)
    factura.setInformacionFactura(informacionFactura)




    val jaxbContext = JAXBContext.newInstance(Factura::class.java)
    val marshaller = jaxbContext.createMarshaller()
    marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true)
    marshaller.setProperty("jaxb.encoding", "UTF-8")

    val stringWriter = StringWriter()
    stringWriter.use {
        marshaller.marshal(factura, stringWriter)
    }

    val out = OutputStreamWriter(FileOutputStream("1234.xml"), "UTF-8")
    marshaller.marshal(factura, out)

    println(stringWriter)
}