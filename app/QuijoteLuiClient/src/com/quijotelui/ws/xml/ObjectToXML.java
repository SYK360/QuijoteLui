package com.quijotelui.ws.xml;

import ec.gob.sri.comprobantes.ws.RespuestaSolicitud;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

public class ObjectToXML {

    public static byte[] convierteEnXml(Object comprobante) {
        try {
            StringWriter xmlComprobante = new StringWriter();
            JAXBContext context = JAXBContext.newInstance(new Class[]{comprobante.getClass()});
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty("jaxb.formatted.output", Boolean.valueOf(true));
            marshaller.marshal(comprobante, xmlComprobante);
            xmlComprobante.close();
            return xmlComprobante.toString().getBytes("UTF-8");
        } catch (IOException | JAXBException | ClassCastException ex) {
            Logger.getLogger(ObjectToXML.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Se produjo un error al convetir el archivo al formato XML");
            StringWriter xmlError = new StringWriter();                        
            xmlError.write("Se produjo un error al convetir el archivo al formato XML");            
            return xmlError.toString().getBytes();
        }
    }

    public static String convierteRespuestaSolicitudXml(RespuestaSolicitud respuesta, String pathArchivoSalida) {
        try {
            JAXBContext context = JAXBContext.newInstance(new Class[]{RespuestaSolicitud.class});
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty("jaxb.encoding", "UTF-8");
            marshaller.setProperty("jaxb.formatted.output", Boolean.valueOf(true));
            FileOutputStream fileOutputStream = new FileOutputStream(pathArchivoSalida);
            OutputStreamWriter out = new OutputStreamWriter(fileOutputStream, "UTF-8");
            marshaller.marshal(respuesta, out);
            fileOutputStream.close();
        } catch (Exception ex) {
            Logger.getLogger(ObjectToXML.class.getName()).log(Level.SEVERE, null, ex);
            return ex.getMessage();
        }
        return null;
    }
}
