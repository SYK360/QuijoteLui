/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quijoteluiprinter;

import com.quijotelui.printer.pdf.FacturaPDF;
import com.quijotelui.printer.pdf.GuiaRemisionPDF;
import com.quijotelui.printer.pdf.NotaCreditoPDF;

/**
 *
 * @author jorgequiguango
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
//        imprimirFacturaPDF();
        imprimirGuiaPDF();
    }

//    static void imprimirFacturaPDF() {
//        FacturaPDF pdf = new FacturaPDF("/data/git/QuijoteLui/app/QuijoteLuiPrinter/recursos/reportes",
//                "/data/git/QuijoteLui/app/QuijoteLuiPrinter/recursos/imagenes/logo.jpeg",
//                "/data/Quijotelui/comprobante/pdf");
//
//        pdf.genera("/data/Quijotelui/comprobante/generado/"
//                + "1712201701100245687700110010030030048911234567813.xml",
//                "1712201701100245687700110010030030048911234567813",
//                "01/01/0001 00:00:00");
//    }
    
    static void imprimirGuiaPDF() {
        GuiaRemisionPDF pdf = new GuiaRemisionPDF("/data/git/QuijoteLui/app/QuijoteLuiPrinter/recursos/reportes",
                "/data/git/QuijoteLui/app/QuijoteLuiPrinter/recursos/imagenes/logo.jpeg",
                "/data/Quijotelui/comprobante/pdf");

        pdf.genera("/data/Quijotelui/comprobante/generado/"
                + "0712201706100245687700110010020000005651234567811.xml",
                "0712201706100245687700110010020000005651234567811",
                "01/01/0001 00:00:00");
    }
}
