/*
 * Copyright (C) 2015 jorjoluiso
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.quijotelui.printer.parametros;

import com.quijotelui.printer.adicional.InfoTributaria;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jorjoluiso
 */
public class Parametros {
    
    String direccionReportes;
    String direccionLogoJpeg;
    

    public Parametros(String direccionReportes, String direccionLogoJpeg) {
        /*
        Ejemplo:
        resources/images/logo.jpeg
        */
        this.direccionReportes = direccionReportes;
        this.direccionLogoJpeg = direccionLogoJpeg;
    }
    

    public Map<String, Object> obtenerParametrosInfoTriobutaria(InfoTributaria infoTributaria, String numAut, String fechaAut) {
        Map param = new HashMap();
        param.put("RUC", infoTributaria.getRuc());
        param.put("CLAVE_ACC", infoTributaria.getClaveAcceso());
        param.put("RAZON_SOCIAL", infoTributaria.getRazonSocial());
        param.put("DIR_MATRIZ", infoTributaria.getDirMatriz());
        try {
            param.put("LOGO", new FileInputStream(this.direccionLogoJpeg));
//            param.put("LOGO", new FileInputStream("resources/images/logo.jpeg"));

        } catch (FileNotFoundException ex) {
            Logger.getLogger(Parametros.class.getName()).log(Level.SEVERE, null, ex);
        }
//        param.put("SUBREPORT_DIR", "./resources/reportes/");

        param.put("SUBREPORT_DIR", this.direccionReportes + File.separator);
        if (infoTributaria.tipoEmision.equals("1")) {
            param.put("TIPO_EMISION", "Normal");
        } else {
            param.put("TIPO_EMISION", "Indisponibilidad del Sistema");
        }
        param.put("NUM_AUT", numAut);
        param.put("FECHA_AUT", fechaAut);
        param.put("MARCA_AGUA", "");
        param.put("NUM_FACT", infoTributaria.getEstab() + "-" + infoTributaria.getPtoEmi() + "-" + infoTributaria.getSecuencial());
        if (infoTributaria.ambiente.equals("1")) {
            param.put("AMBIENTE", "Pruebas");
        } else {
            param.put("AMBIENTE", "Producción");
        }
        param.put("NOM_COMERCIAL", infoTributaria.getNombreComercial());
        return param;
    }
}
