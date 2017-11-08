/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.quijotelui.printer;


import com.quijotelui.printer.factura.Factura;
import com.quijotelui.printer.factura.FacturaReporte;
import com.quijotelui.printer.utilidades.DirectorioConfiguracion;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 *
 * @author jorjoluiso
 */
public class ReportElectronica {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Factura f = xmlToObject();

        FacturaReporte fr = new FacturaReporte(f);
        generarReporte(fr, "1012201418080110024568770011648454187", "10/12/2014 18:08:01");
        xmlToObject();
    }

    static Factura xmlToObject() {
        Factura factura = null;
        try {
            File file = new File("/app/quijotelu/generado/1912201401100245687700110010030000000031234567810.xml");
            JAXBContext jaxbContext = JAXBContext.newInstance(Factura.class);

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            factura = (Factura) jaxbUnmarshaller.unmarshal(file);
            System.out.println(factura);

        } catch (JAXBException ex) {
            Logger.getLogger(ReportElectronica.class.getName()).log(Level.SEVERE, null, ex);
        }
        return factura;

    }

    public static void generarReporte(FacturaReporte xml, String numAut, String fechaAut) {

        try {
            //repUtil.generarReporte("resources/reportes/factura.jasper", xml, numAut, fechaAut);
            generarReporte("./resources/reportes/factura.jasper", xml, numAut, fechaAut);
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ReportElectronica.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void generarReporte(String urlReporte, FacturaReporte fact, String numAut, String fechaAut)
            throws SQLException, ClassNotFoundException {
        FileInputStream is = null;
        try {
            JRDataSource dataSource = new JRBeanCollectionDataSource(fact.getDetallesAdiciones());
            is = new FileInputStream(urlReporte);
            JasperPrint reporte_view = JasperFillManager.fillReport(is, obtenerMapaParametrosReportes(obtenerParametrosInfoTriobutaria(fact.getFactura().getInfoTributaria(), numAut, fechaAut), obtenerInfoFactura(fact.getFactura().getInfoFactura(), fact)), dataSource);
            savePdfReport(reporte_view, fact.getFactura().getInfoTributaria().claveAcceso);
        } catch (FileNotFoundException | JRException ex) {
            Logger.getLogger(ReportElectronica.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(ReportElectronica.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static void savePdfReport(JasperPrint jp, String nombrePDF) {
        DirectorioConfiguracion directorio = new DirectorioConfiguracion();
        try {
            OutputStream output = new FileOutputStream(new File(directorio.getRutaArchivoPDF() + File.separatorChar + nombrePDF + ".pdf"));
            JasperExportManager.exportReportToPdfStream(jp, output);
        } catch (JRException | FileNotFoundException ex) {
            Logger.getLogger(ReportElectronica.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static Map<String, Object> obtenerParametrosInfoTriobutaria(InfoTributaria infoTributaria, String numAut, String fechaAut) throws SQLException, ClassNotFoundException {
        Map param = new HashMap();
        param.put("RUC", infoTributaria.getRuc());
        param.put("CLAVE_ACC", infoTributaria.getClaveAcceso());
        param.put("RAZON_SOCIAL", infoTributaria.getRazonSocial());
        param.put("DIR_MATRIZ", infoTributaria.getDirMatriz());
        try {
            param.put("LOGO", new FileInputStream("resources/images/logo.jpeg"));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ReportElectronica.class.getName()).log(Level.SEVERE, null, ex);
        }
        param.put("SUBREPORT_DIR", "./resources/reportes/");
        if (infoTributaria.tipoEmision.equals("1")) {
            param.put("TIPO_EMISION", "Normal");
        }
        else{
            param.put("TIPO_EMISION", "Indisponibilidad del Sistema");
        }
        param.put("NUM_AUT", numAut);
        param.put("FECHA_AUT", fechaAut);
        param.put("MARCA_AGUA", "");
        param.put("NUM_FACT", infoTributaria.getEstab() + "-" + infoTributaria.getPtoEmi() + "-" + infoTributaria.getSecuencial());
        if(infoTributaria.ambiente.equals("1")){
            param.put("AMBIENTE", "Pruebas");
        }
        else{
            param.put("AMBIENTE", "Producción");
        }
        param.put("NOM_COMERCIAL", infoTributaria.getNombreComercial());
        return param;
    }

    private static Map<String, Object> obtenerInfoFactura(Factura.InfoFactura infoFactura, FacturaReporte fact) {
        Map param = new HashMap();
        param.put("DIR_SUCURSAL", infoFactura.getDirEstablecimiento());
        param.put("CONT_ESPECIAL", infoFactura.getContribuyenteEspecial());
        param.put("LLEVA_CONTABILIDAD", infoFactura.getObligadoContabilidad());
        param.put("RS_COMPRADOR", infoFactura.getRazonSocialComprador());
        param.put("RUC_COMPRADOR", infoFactura.getIdentificacionComprador());
        param.put("FECHA_EMISION", infoFactura.getFechaEmision());
        param.put("GUIA", infoFactura.getGuiaRemision());
        TotalComprobante tc = getTotales(infoFactura);
        param.put("VALOR_TOTAL", infoFactura.getImporteTotal());
        param.put("DESCUENTO", infoFactura.getTotalDescuento());
        param.put("IVA", tc.getIva12());
        param.put("IVA_0", tc.getSubtotal0());
        param.put("IVA_12", tc.getSubtotal12());
        param.put("ICE", tc.getTotalIce());
        param.put("IRBPNR", 0);
        param.put("EXENTO_IVA", "0");
        param.put("NO_OBJETO_IVA", tc.getSubtotalNoSujetoIva());
        param.put("SUBTOTAL", infoFactura.getTotalSinImpuestos().toString());
        if (infoFactura.getPropina() != null) {
            param.put("PROPINA", infoFactura.getPropina().toString());
        }
        param.put("TOTAL_DESCUENTO", calcularDescuento(fact));
        return param;
    }

    private static String calcularDescuento(FacturaReporte fact) {
        BigDecimal descuento = new BigDecimal(0);
        for (DetallesAdicionalesReporte detalle : fact.getDetallesAdiciones()) {
            descuento = descuento.add(new BigDecimal(detalle.getDescuento()));
        }
        return descuento.toString();
    }

    private static Map<String, Object> obtenerMapaParametrosReportes(Map<String, Object> mapa1, Map<String, Object> mapa2) {
        mapa1.putAll(mapa2);
        return mapa1;
    }

    private static TotalComprobante getTotales(Factura.InfoFactura infoFactura) {
        BigDecimal totalIva12 = new BigDecimal(0.0D);
        BigDecimal totalIva0 = new BigDecimal(0.0D);
        BigDecimal iva12 = new BigDecimal(0.0D);
        BigDecimal totalICE = new BigDecimal(0.0D);
        BigDecimal totalSinImpuesto = new BigDecimal(0.0D);
        TotalComprobante tc = new TotalComprobante();
        for (Factura.InfoFactura.TotalConImpuestos.TotalImpuesto ti : infoFactura.getTotalConImpuestos().getTotalImpuesto()) {
            Integer cod = new Integer(ti.getCodigo());
            if ((TipoImpuestoEnum.IVA.getCode() == cod.intValue()) && (TipoImpuestoIvaEnum.IVA_VENTA_12.getCode().equals(ti.getCodigoPorcentaje()))) {
                totalIva12 = totalIva12.add(ti.getBaseImponible());
                iva12 = iva12.add(ti.getValor());
            }
            if ((TipoImpuestoEnum.IVA.getCode() == cod.intValue()) && (TipoImpuestoIvaEnum.IVA_VENTA_0.getCode().equals(ti.getCodigoPorcentaje()))) {
                totalIva0 = totalIva0.add(ti.getBaseImponible());
            }
            if ((TipoImpuestoEnum.IVA.getCode() == cod.intValue()) && (TipoImpuestoIvaEnum.IVA_VENTA_EXCENTO.getCode().equals(ti.getCodigoPorcentaje()))) {
                totalSinImpuesto = totalSinImpuesto.add(ti.getBaseImponible());
            }
            if (TipoImpuestoEnum.ICE.getCode() == cod.intValue()) {
                totalICE = totalICE.add(ti.getValor());
            }
        }
        tc.setIva12(iva12.toString());
        tc.setSubtotal0(totalIva0.toString());
        tc.setSubtotal12(totalIva12.toString());
        tc.setTotalIce(totalICE.toString());
        tc.setSubtotal(totalIva0.add(totalIva12));
        tc.setSubtotalNoSujetoIva(totalSinImpuesto.toString());
        return tc;
    }
}
