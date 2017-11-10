/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.quijotelui.firmador;

import java.io.File;
import java.util.Scanner;
import java.io.Console;

/**
 *
 * @author jorgequiguango
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Console console = System.console();
        if (console == null) {
            System.out.println("No console: non-interactive mode!");
            System.exit(0);
        }

        System.out.println("QuijoteLui Firmador");
        XAdESBESSignature xadesBesFirma = new XAdESBESSignature();
        Scanner scanner = new Scanner(System.in);

        String urlArchivo = "/app/Quijotelui/comprobante/generado/"
                + "1210201701100245687700110010030000012481234567810.xml";
        String nombreArchivo = "1210201701100245687700110010030000012481234567810.xml";
        String urlOutArchivo = "/tmp";
        String PKCS12_RESOURCE = "/data/BCE";
        String PKCS12_PASSWORD = "";

        System.out.println("Ingrese el nombre del archivo P12: ");
        String nombreP12 = scanner.next();
        PKCS12_RESOURCE = PKCS12_RESOURCE + File.separatorChar + nombreP12;
        System.out.println("Archivo Firma P12: " + PKCS12_RESOURCE);

        char passwordArray[] = console.readPassword("Ingrese la contraseña: ");
        PKCS12_PASSWORD = new String(passwordArray);

        xadesBesFirma.firmar(urlArchivo, nombreArchivo, urlOutArchivo, PKCS12_RESOURCE, PKCS12_PASSWORD);
    }

}
