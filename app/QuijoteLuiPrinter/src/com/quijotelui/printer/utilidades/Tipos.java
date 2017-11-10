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
package com.quijotelui.printer.utilidades;

/**
 *
 * @author jorjoluiso
 */

public class Tipos {
    
    public static String obtenerDocumentoModificado(String codDoc) {
        if ("01".equals(codDoc)) {
            return "FACTURA";
        }
        if ("04".equals(codDoc)) {
            return "NOTA DE CRÉDITO";
        }
        if ("05".equals(codDoc)) {
            return "NOTA DE DÉBITO";
        }
        if ("06".equals(codDoc)) {
            return "GUÍA REMISIÓN";
        }
        if ("07".equals(codDoc)) {
            return "COMPROBANTE DE RETENCIÓN";
        }
        return null;
    }
}
