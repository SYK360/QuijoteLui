/*
 * Copyright (C) 2015 jorjoluiso
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
import com.quijotelui.printer.notacredito.NotaCredito;
import com.quijotelui.printer.notacredito.NotaCreditoReporte;
import com.quijotelui.printer.notacredito.TotalConImpuestos;
import com.quijotelui.printer.parametros.Parametros;
import com.quijotelui.printer.utilidades.DirectorioConfiguracion;
import com.quijotelui.printer.utilidades.StringUtil;
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
public class NotaCreditoPDF {

    String rutaArchivo;

    public NotaCreditoPDF(String rutaArchivo) {
        this.rutaArchivo = rutaArchivo;
    }

    public void genera(String numeroAutorizacion, String fechaAutorizacion) {
        NotaCredito nc = xmlToObject();

        NotaCreditoReporte fr = new NotaCreditoReporte(nc);
        generarReporte(fr, numeroAutorizacion, fechaAutorizacion);
        xmlToObject();
    }

    private NotaCredito xmlToObject() {
        NotaCredito notaCredito = null;
        try {
            File file = new File(rutaArchivo);
            JAXBContext jaxbContext = JAXBContext.newInstance(NotaCredito.class);

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            notaCredito = (NotaCredito) jaxbUnmarshaller.unmarshal(file);
            System.out.println(notaCredito);

        } catch (JAXBException ex) {
            Logger.getLogger(NotaCreditoPDF.class.getName()).log(Level.SEVERE, null, ex);
        }
        return notaCredito;

    }

    public void generarReporte(NotaCreditoReporte xml, String numAut, String fechaAut) {

        generarReporte("./resources/reportes/notaCredito.jasper", xml, numAut, fechaAut);
    }

    public void generarReporte(String urlReporte, NotaCreditoReporte rep, String numAut, String fechaAut) {
        FileInputStream is = null;
        Parametros p = new Parametros();
        try {
            JRDataSource dataSource = new JRBeanCollectionDataSource(rep.getDetallesAdiciones());
            is = new FileInputStream(urlReporte);
            JasperPrint reporte_view = JasperFillManager.fillReport(is, obtenerMapaParametrosReportes(p.obtenerParametrosInfoTriobutaria(rep.getNotaCredito().getInfoTributaria(), numAut, fechaAut), obtenerInfoNC(rep.getNotaCredito().getInfoNotaCredito(), rep)), dataSource);

            savePdfReport(reporte_view, rep.getNotaCredito().getInfoTributaria().claveAcceso);
        } catch (FileNotFoundException | JRException ex) {
            Logger.getLogger(NotaCreditoPDF.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(NotaCreditoPDF.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(NotaCreditoPDF.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(NotaCreditoPDF.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private Map<String, Object> obtenerMapaParametrosReportes(Map<String, Object> mapa1, Map<String, Object> mapa2) {
        mapa1.putAll(mapa2);
        return mapa1;
    }

    private Map<String, Object> obtenerInfoNC(NotaCredito.InfoNotaCredito infoNC, NotaCreditoReporte nc) {
        Map param = new HashMap();
        param.put("DIR_SUCURSAL", infoNC.getDirEstablecimiento());
        param.put("CONT_ESPECIAL", infoNC.getContribuyenteEspecial());
        param.put("LLEVA_CONTABILIDAD", infoNC.getObligadoContabilidad());
        param.put("RS_COMPRADOR", infoNC.getRazonSocialComprador());
        param.put("RUC_COMPRADOR", infoNC.getIdentificacionComprador());
        param.put("FECHA_EMISION", infoNC.getFechaEmision());
        TotalComprobante tc = getTotalesNC(infoNC);
        param.put("IVA_0", tc.getSubtotal0());
        param.put("IVA_12", tc.getSubtotal12());
        param.put("ICE", tc.getTotalIce());
        param.put("VALOR_TOTAL", infoNC.getValorModificacion());
        param.put("IVA", tc.getIva12());
        param.put("SUBTOTAL", infoNC.getTotalSinImpuestos().toString());
        param.put("NO_OBJETO_IVA", tc.getSubtotalNoSujetoIva());
        param.put("EXENTO_IVA", BigDecimal.ZERO);
        param.put("NUM_DOC_MODIFICADO", infoNC.getNumDocModificado());
        param.put("FECHA_EMISION_DOC_SUSTENTO", infoNC.getFechaEmisionDocSustento());
        param.put("DOC_MODIFICADO", StringUtil.obtenerDocumentoModificado(infoNC.getCodDocModificado()));
        param.put("TOTAL_DESCUENTO", obtenerTotalDescuento(nc));
        param.put("RAZON_MODIF", infoNC.getMotivo());
        param.put("IRBPNR", BigDecimal.ZERO);
        return param;
    }

    private String obtenerTotalDescuento(NotaCreditoReporte nc) {
        BigDecimal descuento = new BigDecimal(0);
        for (DetallesAdicionalesReporte detalle : nc.getDetallesAdiciones()) {
            descuento = descuento.add(new BigDecimal(detalle.getDescuento()));
        }
        return descuento.toString();
    }

    private TotalComprobante getTotalesNC(NotaCredito.InfoNotaCredito infoNc) {
        BigDecimal totalIva12 = new BigDecimal(0.0D);
        BigDecimal totalIva0 = new BigDecimal(0.0D);
        BigDecimal iva12 = new BigDecimal(0.0D);
        BigDecimal totalICE = new BigDecimal(0.0D);
        BigDecimal totalSinImpuesto = new BigDecimal(0.0D);
        TotalComprobante tc = new TotalComprobante();
        for (TotalConImpuestos.TotalImpuesto ti : infoNc.getTotalConImpuestos().getTotalImpuesto()) {
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
