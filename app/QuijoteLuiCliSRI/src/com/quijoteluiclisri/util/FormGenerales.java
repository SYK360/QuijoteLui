/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.quijoteluiclisri.util;

/**
 *
 * @author jorgequiguango
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import com.quijoteluiclisri.util.EnvioComprobantesWs;
import com.quijoteluiclisri.util.TipoComprobanteEnum;


import java.awt.event.KeyEvent;
import java.io.File;
import java.io.PrintStream;
import java.net.MalformedURLException;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.xml.ws.WebServiceException;

public class FormGenerales
{
  private static char SEPARADOR_DECIMAL = '.';
  

  
  public static boolean validaValoresNumericos(KeyEvent evt, JTextField componente)
  {
    boolean resp = false;
    String old = componente.getText();
    char c = evt.getKeyChar();
    if ((c == SEPARADOR_DECIMAL) && (old.indexOf(c) > -1))
    {
      JOptionPane.showMessageDialog(new JFrame(), "La cantidad ingresada no puede tener mas de un (.) decimal", "Se ha producido un error ", 0);
      
      componente.setText(old);
    }
    if (((Character.isLetter(c)) && (!evt.isAltDown())) || ("`~!@#$%^&*()_+=\\|\"':;?/><, -".indexOf(c) > -1))
    {
      JOptionPane.showMessageDialog(new JFrame(), "Solo se permiten valores num��ricos", "Se ha producido un error ", 0);
      
      componente.setText(old);
    }
    else
    {
      resp = true;
    }
    return resp;
  }
  
  public static boolean validaSoloNumeros(KeyEvent evt, JTextField componente)
  {
    boolean resp = false;
    String old = componente.getText();
    char c = evt.getKeyChar();
    if (((Character.isLetter(c)) && (!evt.isAltDown())) || ("`~!@#$%^&*()_+=\\|\"':;?/>.<, -".indexOf(c) > -1))
    {
      JOptionPane.showMessageDialog(new JFrame(), "Solo se permiten n��meros", "ERROR", 0);
      
      componente.setText(old);
    }
    else
    {
      resp = true;
    }
    return resp;
  }
  
  public static boolean validaLongitud(KeyEvent evt, int longitudMaxima, JTextField componente)
  {
    boolean resp = false;
    String old = componente.getText();
    char c = evt.getKeyChar();
    if ((old.length() >= longitudMaxima) && (c != '\b') && (!evt.isActionKey()))
    {
      JOptionPane.showMessageDialog(new JFrame(), "La longitud m��xima del campo ha sido alcanzada: " + longitudMaxima, "ERROR", 0);
      
      componente.setText(old);
    }
    else
    {
      resp = true;
    }
    return resp;
  }
  
  public static String devuelveUrlWs(String ambiente, String nombreServicio)
  {
    StringBuilder url = new StringBuilder();
    String direccionIPServicio = null;

      if (ambiente.equals("1") == true) {
        direccionIPServicio = "https://celcer.sri.gob.ec";
      } else if (ambiente.equals("2") == true) {
        direccionIPServicio = "https://celcer.sri.gob.ec";
      }
      url.append(direccionIPServicio);
      
      url.append("/comprobantes-electronicos-ws/");
      url.append(nombreServicio);
      url.append("?wsdl");
      System.out.print(url.toString());
    
    return url.toString();
  }
  
  public static String validarUrl(String tipoAmbiente, String nombreServicio)
  {
    String mensaje = null;
    String url = devuelveUrlWs(tipoAmbiente, nombreServicio);
    Object c = EnvioComprobantesWs.webService(url);
    if ((c instanceof MalformedURLException)) {
      mensaje = ((MalformedURLException)c).getMessage();
    }
    return mensaje;
  }
  
  public static boolean existConnection(String tipoAmbiente, String nombreServicio)
  {
    String url = devuelveUrlWs(tipoAmbiente, nombreServicio);
    int i = 0;
    Boolean respuesta = Boolean.valueOf(false);
    while (i < 3)
    {
      Object c = EnvioComprobantesWs.webService(url);
      if (c == null)
      {
        respuesta = Boolean.valueOf(true);
        break;
      }
      if ((c instanceof WebServiceException)) {
        respuesta = Boolean.valueOf(false);
      }
      i++;
    }
    return respuesta.booleanValue();
  }
  

  public static String obtieneTipoDeComprobante(String claveDeAcceso)
  {
    String abreviatura = null;
    if ((claveDeAcceso != null) && (claveDeAcceso.length() == 49))
    {
      String tipo = claveDeAcceso.substring(8, 10);
      if (tipo.equals(TipoComprobanteEnum.FACTURA.getCode())) {
        abreviatura = TipoComprobanteEnum.FACTURA.getDescripcion();
      } else if (tipo.equals(TipoComprobanteEnum.NOTA_DE_DEBITO.getCode())) {
        abreviatura = TipoComprobanteEnum.NOTA_DE_DEBITO.getDescripcion();
      } else if (tipo.equals(TipoComprobanteEnum.NOTA_DE_CREDITO.getCode())) {
        abreviatura = TipoComprobanteEnum.NOTA_DE_CREDITO.getDescripcion();
      } else if (tipo.equals(TipoComprobanteEnum.GUIA_DE_REMISION.getCode())) {
        abreviatura = TipoComprobanteEnum.GUIA_DE_REMISION.getDescripcion();
      } else if (tipo.equals(TipoComprobanteEnum.COMPROBANTE_DE_RETENCION.getCode())) {
        abreviatura = TipoComprobanteEnum.COMPROBANTE_DE_RETENCION.getDescripcion();
      } else if (tipo.equals(TipoComprobanteEnum.LOTE.getCode())) {
        abreviatura = TipoComprobanteEnum.LOTE.getDescripcion();
      }
    }
    return abreviatura;
  }
  
  public static TipoComprobanteEnum getTipoDeComprobante(String tipoDocumeto)
  {
    if (tipoDocumeto.equals(TipoComprobanteEnum.FACTURA.getCode())) {
      return TipoComprobanteEnum.FACTURA;
    }
    if (tipoDocumeto.equals(TipoComprobanteEnum.NOTA_DE_DEBITO.getCode())) {
      return TipoComprobanteEnum.NOTA_DE_DEBITO;
    }
    if (tipoDocumeto.equals(TipoComprobanteEnum.NOTA_DE_CREDITO.getCode())) {
      return TipoComprobanteEnum.NOTA_DE_CREDITO;
    }
    if (tipoDocumeto.equals(TipoComprobanteEnum.GUIA_DE_REMISION.getCode())) {
      return TipoComprobanteEnum.GUIA_DE_REMISION;
    }
    if (tipoDocumeto.equals(TipoComprobanteEnum.COMPROBANTE_DE_RETENCION.getCode())) {
      return TipoComprobanteEnum.COMPROBANTE_DE_RETENCION;
    }
    if (tipoDocumeto.equals(TipoComprobanteEnum.LOTE.getCode())) {
      return TipoComprobanteEnum.LOTE;
    }
    return null;
  }
  
  public static Date eliminaHora(Date date)
  {
    Calendar cal = Calendar.getInstance();
    cal.setTime(date);
    cal.set(11, 0);
    cal.set(12, 0);
    cal.set(13, 0);
    cal.set(14, 0);
    return cal.getTime();
  }
  

  
  public static String insertarCaracteres(String cadenaLarga, String aInsertar, int longitud)
  {
    StringBuilder sb = new StringBuilder(cadenaLarga);
    
    int i = 0;
    while ((i = sb.indexOf(" ", i + longitud)) != -1) {
      sb.replace(i, i + 1, aInsertar);
    }
    return sb.toString();
  }
  
  
  public static boolean validaTextArea(KeyEvent evt, int longitudMaxima, JTextArea componente)
  {
    boolean resp = false;
    String old = componente.getText();
    char c = evt.getKeyChar();
    int key = evt.getKeyCode();
    if (esTecladeControl(key) == true)
    {
      resp = true;
    }
    else if ((old.length() >= longitudMaxima) && (c != '\b') && (!evt.isActionKey()))
    {
      JOptionPane.showMessageDialog(new JFrame(), "La longitud m��xima del campo ha sido alcanzada: " + longitudMaxima, "ERROR", 0);
      
      componente.setText(old);
      resp = false;
    }
    else
    {
      resp = true;
    }
    return resp;
  }
  
  private static boolean esTecladeControl(int key)
  {
    boolean resp = false;
    if ((key == 226) || (key == 37) || (key == 227) || (key == 39) || (key == 38) || (key == 40) || (key == 10) || (key == 9) || (key == 127)) {
      resp = true;
    }
    return resp;
  }
  
  public static String ingresaPassword()
  {
    String password = null;
    
    JPasswordField pwd = new JPasswordField(20);
    int action = JOptionPane.showConfirmDialog(null, pwd, "Ingrese la Contrase��a del token", 2);
    if (action < 0) {
      JOptionPane.showMessageDialog(new JFrame(), "Usted cancel�� la operaci��n");
    } else {
      password = new String(pwd.getPassword());
    }
    return password;
  }
}
