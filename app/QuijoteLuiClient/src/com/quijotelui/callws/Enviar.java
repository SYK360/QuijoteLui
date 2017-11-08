package com.quijotelui.callws;

import com.quijotelui.ws.util.ArchivoUtils;
import com.quijotelui.ws.util.EnvioComprobantesWs;
import ec.gob.sri.comprobantes.ws.RespuestaSolicitud;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Enviar {

    String archivoFirmado;
    String destinoEnviado;
    String destinoRechazado;
    String direccionWebService;

    public Enviar(String archivoFirmado, String destinoEnviado, String destinoRechazado, String direccionWebService) {
        this.archivoFirmado = archivoFirmado;
        this.destinoEnviado = destinoEnviado;
        this.destinoRechazado = destinoRechazado;
        /*
        *Web Service de Pruevas
        *Recepción
        *https://celcer.sri.gob.ec/comprobantes-electronicos-ws/RecepcionComprobantesOffline?wsdl
        *
        *Autorización
        *https://celcer.sri.gob.ec/comprobantes-electronicos-ws/AutorizacionComprobantesOffline?wsdl
         */
        this.direccionWebService = direccionWebService;
    }

    public RespuestaSolicitud executeEnviar() {

        RespuestaSolicitud respuestaSolicitudEnvio = new RespuestaSolicitud();

        try {

            File archivoXMLFirmadoFile = new File(this.archivoFirmado);
            String nombreArchivo = archivoXMLFirmadoFile.getName();
            byte[] archivoXMLFirmadoByte = ArchivoUtils.archivoToByte(archivoXMLFirmadoFile);

            respuestaSolicitudEnvio = EnvioComprobantesWs.obtenerRespuestaEnvio(archivoXMLFirmadoFile, this.direccionWebService);
            ArchivoUtils.validarRespuestaEnvio(respuestaSolicitudEnvio, archivoXMLFirmadoByte, nombreArchivo, this.destinoEnviado, this.destinoRechazado);
            System.out.println(respuestaSolicitudEnvio.getEstado() + " " + "El comprobante fue enviado, está pendiente de autorización");
            
            return respuestaSolicitudEnvio;

        } catch (IOException ex) {
            Logger.getLogger(Enviar.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
