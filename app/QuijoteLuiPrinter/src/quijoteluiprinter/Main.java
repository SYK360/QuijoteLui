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
        imprimirFacturaPDF();
    }

    static void imprimirNotaCreditoPDF() {
        NotaCreditoPDF pdf = new NotaCreditoPDF("/data/startup/quijotelu/Notas de Crédito Emitidas/Febrero2015/Disme/ele.xml");
        pdf.genera("0203201512343510024568770013312187486", 
                "02/03/2015 12:34:35.749",
                "urlLogo");
    }

    static void imprimirFacturaPDF() {
        FacturaPDF pdf = new FacturaPDF("/app/Quijotelui/comprobante/generado/"
                + "1010201701100197312000110010030000012461234567817.xml");
        
        pdf.genera("1010201701100197312000110010030000012461234567817", 
                "01/01/0001 00:00:00",
                "urlLogo");
    }

    static void imprimirGuiaRemisionPDF() {
        GuiaRemisionPDF pdf = new GuiaRemisionPDF("D:\\app\\quijotelu\\generado\\0409201506100245687700110010020000001211234567813.xml");
        pdf.genera("", "","urlLogo");
    }
}
