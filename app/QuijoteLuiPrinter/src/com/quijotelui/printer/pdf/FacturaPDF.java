/*
 * Copyright (C) 2014 jorjoluiso
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.quijotelui.printer.pdf;


import com.quijotelui.printer.DetallesAdicionalesReporte;
import com.quijotelui.printer.TipoImpuestoEnum;
import com.quijotelui.printer.TipoImpuestoIvaEnum;
import com.quijotelui.printer.TotalComprobante;
import com.quijotelui.printer.factura.Factura;
import com.quijotelui.printer.factura.FacturaReporte;
import com.quijotelui.printer.parametros.Parametros;
import com.quijotelui.printer.utilidades.DirectorioConfiguracion;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
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
public class FacturaPDF {

    String rutaArchivo;//"/app/quijotelu/generado/1912201401100245687700110010030000000031234567810.xml"  

    public FacturaPDF(String RutaArchivo) {
        this.rutaArchivo = RutaArchivo;
    }

    public void genera(String numeroAutorizacion, String fechaAutorizacion) {
        Factura f = xmlToObject();

        FacturaReporte fr = new FacturaReporte(f);
        generarReporte(fr, numeroAutorizacion, fechaAutorizacion);
        xmlToObject();
    }

    private Factura xmlToObject() {
        Factura factura = null;
        try {
            File file = new File(rutaArchivo);
            JAXBContext jaxbContext = JAXBContext.newInstance(Factura.class);

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            factura = (Factura) jaxbUnmarshaller.unmarshal(file);
            System.out.println(factura);

        } catch (JAXBException ex) {
            Logger.getLogger(FacturaPDF.class.getName()).log(Level.SEVERE, null, ex);
        }
        return factura;

    }

    private void generarReporte(FacturaReporte xml, String numAut, String fechaAut) {

        generarReporte("./resources/reportes/factura.jasper", xml, numAut, fechaAut);

    }

    private void generarReporte(String urlReporte, FacturaReporte fact, String numAut, String fechaAut) {
        Parametros p = new Parametros();
        FileInputStream is = null;
        try {
            JRDataSource dataSource = new JRBeanCollectionDataSource(fact.getDetallesAdiciones());
            is = new FileInputStream(urlReporte);
            JasperPrint reporte_view = JasperFillManager.fillReport(is, obtenerMapaParametrosReportes(p.obtenerParametrosInfoTriobutaria(fact.getFactura().getInfoTributaria(), numAut, fechaAut), obtenerInfoFactura(fact.getFactura().getInfoFactura(), fact)), dataSource);
            savePdfReport(reporte_view, fact.getFactura().getInfoTributaria().claveAcceso);
        } catch (FileNotFoundException | JRException ex) {
            Logger.getLogger(FacturaPDF.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(FacturaPDF.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void savePdfReport(JasperPrint jp, String nombrePDF) {
        DirectorioConfiguracion directorio = new DirectorioConfiguracion();
        try {
            OutputStream output = new FileOutputStream(new File(directorio.getRutaArchivoPDF() + File.separatorChar + nombrePDF + ".pdf"));
            JasperExportManager.exportReportToPdfStream(jp, output);
            output.close();
        } catch (JRException | FileNotFoundException ex) {
            Logger.getLogger(FacturaPDF.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FacturaPDF.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private Map<String, Object> obtenerInfoFactura(Factura.InfoFactura infoFactura, FacturaReporte fact) {
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

    private String calcularDescuento(FacturaReporte fact) {
        BigDecimal descuento = new BigDecimal(0);
        for (DetallesAdicionalesReporte detalle : fact.getDetallesAdiciones()) {
            descuento = descuento.add(new BigDecimal(detalle.getDescuento()));
        }
        return descuento.toString();
    }

    private Map<String, Object> obtenerMapaParametrosReportes(Map<String, Object> mapa1, Map<String, Object> mapa2) {
        mapa1.putAll(mapa2);
        return mapa1;
    }

    private TotalComprobante getTotales(Factura.InfoFactura infoFactura) {
        BigDecimal totalIva12 = new BigDecimal(0.0D);
        BigDecimal totalIva0 = new BigDecimal(0.0D);
        BigDecimal iva12 = new BigDecimal(0.0D);
        BigDecimal totalICE = new BigDecimal(0.0D);
        BigDecimal totalSinImpuesto = new BigDecimal(0.0D);
        TotalComprobante tc = new TotalComprobante();
        for (Factura.InfoFactura.TotalConImpuestos.TotalImpuesto ti : infoFactura.getTotalConImpuestos().getTotalImpuesto()) {
            Integer cod = new Integer(ti.getCodigo());
            /*
                Modificado para IVA 12% y 14%
             */
            if ((TipoImpuestoEnum.IVA.getCode() == cod.intValue()) && ((TipoImpuestoIvaEnum.IVA_VENTA_12.getCode().equals(ti.getCodigoPorcentaje())) || (TipoImpuestoIvaEnum.IVA_VENTA_14.getCode().equals(ti.getCodigoPorcentaje())))) {
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
