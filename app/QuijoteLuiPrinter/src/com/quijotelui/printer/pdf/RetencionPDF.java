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


import com.quijotelui.printer.parametros.Parametros;
import com.quijotelui.printer.retencion.ComprobanteRetencion;
import com.quijotelui.printer.retencion.ComprobanteRetencionReporte;
import com.quijotelui.printer.utilidades.DirectorioConfiguracion;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
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
public class RetencionPDF {

    String rutaArchivo;

    public RetencionPDF(String RutaArchivo) {
        this.rutaArchivo = RutaArchivo;
    }

    public void genera(String numeroAutorizacion, String fechaAutorizacion, String urlLogoJpeg) {
        ComprobanteRetencion f = xmlToObject();

        ComprobanteRetencionReporte fr = new ComprobanteRetencionReporte(f);
        generarReporte(fr, numeroAutorizacion, fechaAutorizacion, urlLogoJpeg);
        xmlToObject();
    }

    private ComprobanteRetencion xmlToObject() {
        ComprobanteRetencion factura = null;
        try {
            File file = new File(rutaArchivo);
            JAXBContext jaxbContext = JAXBContext.newInstance(ComprobanteRetencion.class);

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            factura = (ComprobanteRetencion) jaxbUnmarshaller.unmarshal(file);
            System.out.println(factura);

        } catch (JAXBException ex) {
            Logger.getLogger(RetencionPDF.class.getName()).log(Level.SEVERE, null, ex);
        }
        return factura;

    }

    public void generarReporte(ComprobanteRetencionReporte xml, String numAut, String fechaAut, String urlLogoJpeg) {

        generarReporte("./resources/reportes/comprobanteRetencion.jasper", xml, numAut, fechaAut, urlLogoJpeg);
    }

    public void generarReporte(String urlReporte, ComprobanteRetencionReporte rep, String numAut, String fechaAut, String urlLogoJpeg) {
        FileInputStream is = null;
        Parametros p = new Parametros(urlLogoJpeg);
        try {
            JRDataSource dataSource = new JRBeanCollectionDataSource(rep.getDetallesAdiciones());
            is = new FileInputStream(urlReporte);
            JasperPrint reporte_view = JasperFillManager.fillReport(is, obtenerMapaParametrosReportes(p.obtenerParametrosInfoTriobutaria(rep.getComprobanteRetencion().getInfoTributaria(), numAut, fechaAut), obtenerInfoCompRetencion(rep.getComprobanteRetencion().getInfoCompRetencion())), dataSource);

            savePdfReport(reporte_view, rep.getComprobanteRetencion().getInfoTributaria().claveAcceso);
        } catch (FileNotFoundException | JRException ex) {
            Logger.getLogger(RetencionPDF.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(RetencionPDF.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(RetencionPDF.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(RetencionPDF.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private Map<String, Object> obtenerMapaParametrosReportes(Map<String, Object> mapa1, Map<String, Object> mapa2) {
        mapa1.putAll(mapa2);
        return mapa1;
    }

    private Map<String, Object> obtenerInfoCompRetencion(ComprobanteRetencion.InfoCompRetencion infoComp) {
        Map param = new HashMap();
        param.put("DIR_SUCURSAL", infoComp.getDirEstablecimiento());
        param.put("RS_COMPRADOR", infoComp.getRazonSocialSujetoRetenido());
        param.put("RUC_COMPRADOR", infoComp.getIdentificacionSujetoRetenido());
        param.put("FECHA_EMISION", infoComp.getFechaEmision());
        param.put("CONT_ESPECIAL", infoComp.getContribuyenteEspecial());
        param.put("LLEVA_CONTABILIDAD", infoComp.getObligadoContabilidad());
        param.put("EJERCICIO_FISCAL", infoComp.getPeriodoFiscal());
        return param;
    }

}
