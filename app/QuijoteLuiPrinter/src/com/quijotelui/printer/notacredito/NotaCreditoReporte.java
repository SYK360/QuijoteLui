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
package com.quijotelui.printer.notacredito;

import com.quijotelui.printer.DetallesAdicionalesReporte;
import com.quijotelui.printer.InformacionAdicional;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jorjoluiso
 */
public class NotaCreditoReporte {

    private NotaCredito notaCredito;
    private List<DetallesAdicionalesReporte> detallesAdiciones;
    private List<InformacionAdicional> infoAdicional;

    public NotaCreditoReporte(NotaCredito notaCredito) {
        this.notaCredito = notaCredito;
    }

    public NotaCredito getNotaCredito() {
        return this.notaCredito;
    }

    public void setNotaCredito(NotaCredito notaCredito) {
        this.notaCredito = notaCredito;
    }

    public List<DetallesAdicionalesReporte> getDetallesAdiciones() {
        this.detallesAdiciones = new ArrayList();

        for (NotaCredito.Detalles.Detalle det : getNotaCredito().getDetalles().getDetalle()) {
            DetallesAdicionalesReporte detAd = new DetallesAdicionalesReporte();
            detAd.setCodigoPrincipal(det.getCodigoInterno());
            detAd.setCodigoAuxiliar(det.getCodigoAdicional());
            detAd.setDescripcion(det.getDescripcion());
            detAd.setCantidad(det.getCantidad().toPlainString());
            detAd.setPrecioTotalSinImpuesto(det.getPrecioTotalSinImpuesto().toString());
            detAd.setPrecioUnitario(det.getPrecioUnitario().toString());
            detAd.setDescuento(det.getDescuento().toString());
            int i = 0;
            if ((det.getDetallesAdicionales() != null) && (det.getDetallesAdicionales().getDetAdicional() != null)) {
                for (NotaCredito.Detalles.Detalle.DetallesAdicionales.DetAdicional detAdicional : det.getDetallesAdicionales().getDetAdicional()) {
                    if (i == 0) {
                        detAd.setDetalle1(detAdicional.getNombre());
                    }
                    if (i == 1) {
                        detAd.setDetalle2(detAdicional.getNombre());
                    }
                    if (i == 2) {
                        detAd.setDetalle3(detAdicional.getNombre());
                    }
                    i++;
                }

            }

            detAd.setInfoAdicional(getInfoAdicional());
            this.detallesAdiciones.add(detAd);
        }
        return this.detallesAdiciones;
    }

    public void setDetallesAdiciones(List<DetallesAdicionalesReporte> detallesAdiciones) {
        this.detallesAdiciones = detallesAdiciones;
    }

    public List<InformacionAdicional> getInfoAdicional() {
        if (this.notaCredito.getInfoAdicional() != null) {
            this.infoAdicional = new ArrayList();
            if ((this.notaCredito.getInfoAdicional().getCampoAdicional() != null) && (!this.notaCredito.getInfoAdicional().getCampoAdicional().isEmpty())) {
                for (NotaCredito.InfoAdicional.CampoAdicional ca : this.notaCredito.getInfoAdicional().getCampoAdicional()) {
                    this.infoAdicional.add(new InformacionAdicional(ca.getValue(), ca.getNombre()));
                }
            }
        }
        return this.infoAdicional;
    }

    public void setInfoAdicional(List<InformacionAdicional> infoAdicional) {
        this.infoAdicional = infoAdicional;
    }
}
