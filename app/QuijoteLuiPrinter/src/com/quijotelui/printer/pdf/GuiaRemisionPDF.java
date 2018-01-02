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

import com.quijotelui.printer.adicional.InformacionAdicional;
import com.quijotelui.printer.guia.GuiaRemision;
import com.quijotelui.printer.guia.GuiaRemisionReporte;
import com.quijotelui.printer.parametros.Parametros;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
public class GuiaRemisionPDF {

    String rutaArchivo;
    String directorioReportes;
    String directorioLogo;
    String directorioDestino;

    public GuiaRemisionPDF(String directorioReportes, String directorioLogo, String directorioDestino) {
        this.directorioReportes = directorioReportes;
        this.directorioLogo = directorioLogo;
        this.directorioDestino = directorioDestino;
    }



    public void genera(String rutaArchivo, String numeroAutorizacion, String fechaAutorizacion) {
        this.rutaArchivo = rutaArchivo;
        
        GuiaRemision f = xmlToObject();

        GuiaRemisionReporte fr = new GuiaRemisionReporte(f);
        generarReporte(fr, numeroAutorizacion, fechaAutorizacion, f);
    }

    private GuiaRemision xmlToObject() {
        GuiaRemision guiaRemision = null;
        try {
            File file = new File(rutaArchivo);
            JAXBContext jaxbContext = JAXBContext.newInstance(GuiaRemision.class);

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            guiaRemision = (GuiaRemision) jaxbUnmarshaller.unmarshal(file);
//            System.out.println(guiaRemision);

        } catch (JAXBException ex) {
            Logger.getLogger(GuiaRemisionPDF.class.getName()).log(Level.SEVERE, null, ex);
        }
        return guiaRemision;

    }

    public void generarReporte(GuiaRemisionReporte xml, String numAut, String fechaAut, GuiaRemision gr) {        
        generarReporte(this.directorioReportes + File.separator + "guiaRemisionFinal.jasper", xml, numAut, fechaAut, gr);

    }

    public void generarReporte(String urlReporte, GuiaRemisionReporte rep, String numAut, String fechaAut, GuiaRemision guiaRemision) {
        FileInputStream is = null;
        Parametros p = new Parametros(this.directorioReportes, this.directorioLogo);
        try {
            JRDataSource dataSource = new JRBeanCollectionDataSource(rep.getGuiaRemisionList());
            is = new FileInputStream(urlReporte);
            JasperPrint reporte_view = JasperFillManager.fillReport(is, obtenerMapaParametrosReportes(p.obtenerParametrosInfoTriobutaria(rep.getGuiaRemision().getInfoTributaria(), numAut, fechaAut), obtenerInfoGR(rep.getGuiaRemision().getInfoGuiaRemision(), guiaRemision)), dataSource);

            savePdfReport(reporte_view, rep.getGuiaRemision().getInfoTributaria().claveAcceso);
        } catch (FileNotFoundException | JRException ex) {
            Logger.getLogger(GuiaRemisionPDF.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(GuiaRemisionPDF.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void savePdfReport(JasperPrint jp, String nombrePDF) {
        try {
            OutputStream output = new FileOutputStream(new File(this.directorioDestino + File.separatorChar + nombrePDF + ".pdf"));
            JasperExportManager.exportReportToPdfStream(jp, output);
            //JasperExportManager.exportReportToHtmlFile(jp, "D:\\app\\quijotelu\\pdf\\test.html");
            output.close();
        } catch (JRException | FileNotFoundException ex) {
            Logger.getLogger(GuiaRemisionPDF.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(GuiaRemisionPDF.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private Map<String, Object> obtenerInfoGR(GuiaRemision.InfoGuiaRemision igr, GuiaRemision guiaRemision) {
        Map param = new HashMap();
        param.put("DIR_SUCURSAL", igr.getDirEstablecimiento());
        param.put("CONT_ESPECIAL", igr.getContribuyenteEspecial());
        param.put("LLEVA_CONTABILIDAD", igr.getObligadoContabilidad());
        param.put("FECHA_INI_TRANSPORTE", igr.getFechaIniTransporte());
        param.put("FECHA_FIN_TRANSPORTE", igr.getFechaFinTransporte());
        param.put("RUC_TRANSPORTISTA", igr.getRucTransportista());
        param.put("RS_TRANSPORTISTA", igr.getRazonSocialTransportista());
        param.put("PLACA", igr.getPlaca());
        param.put("PUNTO_PARTIDA", igr.getDirPartida());
        param.put("INFO_ADICIONAL", getInfoAdicional(guiaRemision));
        return param;
    }

    public List<InformacionAdicional> getInfoAdicional(GuiaRemision guiaRemision) {
        List infoAdicional = new ArrayList();
        if (guiaRemision.getInfoAdicional() != null) {
            for (GuiaRemision.InfoAdicional.CampoAdicional ca : guiaRemision.getInfoAdicional().getCampoAdicional()) {
                infoAdicional.add(new InformacionAdicional(ca.getValue(), ca.getNombre()));
            }
        }
        if ((infoAdicional != null) && (!infoAdicional.isEmpty())) {
            return infoAdicional;
        }
        return null;
    }

    private Map<String, Object> obtenerMapaParametrosReportes(Map<String, Object> mapa1, Map<String, Object> mapa2) {
        mapa1.putAll(mapa2);
        return mapa1;
    }
}
