/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.quijotelui;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 *
 * @author jorgequiguango
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Boolean online = false;
        online = isWSDLAlive("https://celcer.sri.gob.ec/comprobantes-electronicos-ws/RecepcionComprobantesOffline?wsdl");
        System.out.println("OnLine Recepción: " + online.toString());
        
        online = isWSDLAlive("https://celcer.sri.gob.ec/comprobantes-electronicos-ws/AutorizacionComprobantesOffline?wsdl");
        System.out.println("OnLine Autorización: " + online.toString());
    }

    private static boolean isWSDLAlive(String wsdlAddr) {
        HttpURLConnection c = null;
        try {
            URL u = new URL(wsdlAddr);
            c = (HttpURLConnection) u.openConnection();
            c.setRequestMethod("GET");
            c.getInputStream();
            if (c.getResponseCode() == 200){
                return true;
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
            return false;
        } finally {
            if (c != null) {
                c.disconnect();
            }
        }
        return false;
    }
}
