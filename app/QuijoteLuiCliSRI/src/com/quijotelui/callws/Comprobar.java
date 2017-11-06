/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.quijotelui.callws;

/**
 *
 * @author jorgequiguango
 */
public class Comprobar {
    
    String archivoEnviado;
    String destinoAutorizado;
    String destinoNoAutorizado;
    String direccionWebService;

    public Comprobar(String archivoEnviado, String destinoAutorizado, String destinoNoAutorizado, String direccionWebService) {
        this.archivoEnviado = archivoEnviado;
        this.destinoAutorizado = destinoAutorizado;
        this.destinoNoAutorizado = destinoNoAutorizado;
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
    
    public void executeComprobar() {
        
    }
    
}
