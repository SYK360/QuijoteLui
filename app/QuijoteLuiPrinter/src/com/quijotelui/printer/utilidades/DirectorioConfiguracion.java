/*
 * Copyright (C) 2014 jorjoluiso
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
package com.quijotelui.printer.utilidades;

/**
 *
 * @author jorjoluiso
 */
public class DirectorioConfiguracion {

    String RutaArchivoGenerado;
    String RutaArchivoFirmado;
    String RutaArchivoAutorizado;
    String RutaArchivoNoAutorizado;
    String RutaArchivoPDF;

    public DirectorioConfiguracion() {

        RutaArchivoNoAutorizado = "/app/quijotelu/noautorizado";
        RutaArchivoAutorizado = "/app/quijotelu/autorizado";
        RutaArchivoFirmado = "/app/quijotelu/firmado";
        RutaArchivoGenerado = "/app/quijotelu/generado";
        RutaArchivoPDF = "/app/quijotelu/pdf";
    }

    public String getRutaArchivoGenerado() {
        return RutaArchivoGenerado;
    }

    public String getRutaArchivoFirmado() {
        return RutaArchivoFirmado;
    }

    public String getRutaArchivoAutorizado() {
        return RutaArchivoAutorizado;
    }

    public String getRutaArchivoNoAutorizado() {
        return RutaArchivoNoAutorizado;
    }

    public String getRutaArchivoPDF() {
        return RutaArchivoPDF;
    }

    public void setRutaArchivoPDF(String RutaArchivoPDF) {
        this.RutaArchivoPDF = RutaArchivoPDF;
    }

}
