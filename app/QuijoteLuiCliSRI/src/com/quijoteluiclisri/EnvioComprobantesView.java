/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.quijoteluiclisri;

/**
 *
 * @author jorgequiguango
 */

import com.quijoteluiclisri.util.ArchivoUtils;
import com.quijoteluiclisri.util.DirectorioEnum;
import com.quijoteluiclisri.util.EnvioComprobantesWs;
import com.quijoteluiclisri.util.FormGenerales;
import com.quijoteluiclisri.util.xml.LectorXMLPath;


import ec.gob.sri.comprobantes.ws.RespuestaSolicitud;

import java.io.File;
import java.util.ArrayList;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.xpath.XPathConstants;

public class EnvioComprobantesView
{
  private String claveAcceso = null;  
  private void moverListaArchivos(List<File> listaArchivos)
    throws  ClassNotFoundException
  {
    for (File file : listaArchivos) {
        ArchivoUtils.copiarArchivo(file, DirectorioEnum.ENVIADOS);
        file.delete();
    }
  }
  
  private void btnEnvioIndividualActionPerformed()
  {
    try
    {      
      RespuestaSolicitud respuestaSolicitudEnvio = new RespuestaSolicitud();
      List<File> archivosSeleccionados = new ArrayList();
      if (!archivosSeleccionados.isEmpty())
      {
        for (int i = 0; i < archivosSeleccionados.size(); i++)
        {
          String nombreArchivo = ((File)archivosSeleccionados.get(i)).getName();
          String claveAccesoComprobante = "";
          File archivoXMLFirmadoFile = (File)archivosSeleccionados.get(i);
          byte[] archivoXMLFirmadoByte = ArchivoUtils.archivoToByte((File)archivosSeleccionados.get(i));
          LectorXMLPath lectorXMLPath = new LectorXMLPath(ArchivoUtils.archivoToByte((File)archivosSeleccionados.get(i)), XPathConstants.STRING);
          claveAccesoComprobante = lectorXMLPath.getClaveAcceso();
          String codDoc = lectorXMLPath.getCodDoc();
          String tipoComprobante = codDoc.substring(1);
          respuestaSolicitudEnvio = EnvioComprobantesWs.obtenerRespuestaEnvio(archivoXMLFirmadoFile, "1002456877001", tipoComprobante, claveAccesoComprobante, FormGenerales.devuelveUrlWs(/*this.emisor.getTipoAmbiente()*/"1", "RecepcionComprobantesOffline"));
          ArchivoUtils.validarRespuestaEnvio(respuestaSolicitudEnvio, archivoXMLFirmadoByte, nombreArchivo);
          System.out.println(respuestaSolicitudEnvio.getEstado() + " " +"El comprobante fue enviado, est�� pendiente de autorizaci��n");
          
        }
        System.out.println("Ha finalizado envio de archivos.\nRevisar tabla con resultados individuales ");
      }
      else
      {
        System.out.println("Seleccione al menos un archivo - Seleccione archivos");
      }
    }
    catch (Exception ex)
    {
      Logger.getLogger(EnvioComprobantesView.class.getName()).log(Level.SEVERE, null, ex);
      System.out.println("Error al tratar de enviar el comprobante hacia el SRI:");
    }
    
  }
  

}
