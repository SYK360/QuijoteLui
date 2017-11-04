/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quijoteencriptar;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jorgequiguango
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        try {
            String claveEncriptada = Encriptar.encrypt("MiClaveSecreta");
            String claveDesencriptada = Encriptar.decrypt(claveEncriptada);
            
            System.out.println("Clave encriptada: " + claveEncriptada); 
            System.out.println("Clave desencriptada: " + claveDesencriptada);
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
