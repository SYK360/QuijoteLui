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
package com.quijotelui.printer.guia;

import com.quijotelui.printer.utilidades.StringUtil;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jorjoluiso
 */
public class GuiaRemisionReporte {

    private GuiaRemision guiaRemision;
    private String nombreComprobante;
    private String numDocSustento;
    private String fechaEmisionSustento;
    private String numeroAutorizacion;
    private String motivoTraslado;
    private String destino;
    private String rucDestinatario;
    private String razonSocial;
    private String docAduanero;
    private String codigoEstab;
    private String ruta;
    private List<DetalleGuiaReporte> detalles;
    private List<GuiaRemisionReporte> guiaRemisionList;

    public GuiaRemisionReporte(GuiaRemision guiaRemision) {
        this.guiaRemision = guiaRemision;
    }

    public GuiaRemisionReporte() {
    }

    public GuiaRemision getGuiaRemision() {
        return this.guiaRemision;
    }

    public void setGuiaRemision(GuiaRemision guiaRemision) {
        this.guiaRemision = guiaRemision;
    }

    public String getNombreComprobante() {
        return this.nombreComprobante;
    }

    public void setNombreComprobante(String nombreComprobante) {
        this.nombreComprobante = nombreComprobante;
    }

    public String getNumDocSustento() {
        return this.numDocSustento;
    }

    public void setNumDocSustento(String numDocSustento) {
        this.numDocSustento = numDocSustento;
    }

    public String getFechaEmisionSustento() {
        return this.fechaEmisionSustento;
    }

    public void setFechaEmisionSustento(String fechaEmisionSustento) {
        this.fechaEmisionSustento = fechaEmisionSustento;
    }

    public String getNumeroAutorizacion() {
        return this.numeroAutorizacion;
    }

    public void setNumeroAutorizacion(String numeroAutorizacion) {
        this.numeroAutorizacion = numeroAutorizacion;
    }

    public String getMotivoTraslado() {
        return this.motivoTraslado;
    }

    public void setMotivoTraslado(String motivoTraslado) {
        this.motivoTraslado = motivoTraslado;
    }

    public String getDestino() {
        return this.destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public String getRucDestinatario() {
        return this.rucDestinatario;
    }

    public void setRucDestinatario(String rucDestinatario) {
        this.rucDestinatario = rucDestinatario;
    }

    public String getRazonSocial() {
        return this.razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getDocAduanero() {
        return this.docAduanero;
    }

    public void setDocAduanero(String docAduanero) {
        this.docAduanero = docAduanero;
    }

    public String getCodigoEstab() {
        return this.codigoEstab;
    }

    public void setCodigoEstab(String cosdigoEstab) {
        this.codigoEstab = cosdigoEstab;
    }

    public String getRuta() {
        return this.ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public List<GuiaRemisionReporte> getGuiaRemisionList() {
        this.guiaRemisionList = new ArrayList();
        for (Destinatario dest : this.guiaRemision.getDestinatarios().getDestinatario()) {
            GuiaRemisionReporte gr = new GuiaRemisionReporte();
            gr.setNombreComprobante(StringUtil.obtenerDocumentoModificado(dest.getCodDocSustento()));
            gr.setNumDocSustento(dest.getNumDocSustento());
            gr.setFechaEmisionSustento(dest.getFechaEmisionDocSustento());
            gr.setNumeroAutorizacion(dest.getNumAutDocSustento());
            gr.setMotivoTraslado(dest.getMotivoTraslado());
            gr.setDestino(dest.getDirDestinatario());
            gr.setRucDestinatario(dest.getIdentificacionDestinatario());
            gr.setRazonSocial(dest.getRazonSocialDestinatario());
            gr.setDocAduanero(dest.getDocAduaneroUnico());
            gr.setCodigoEstab(dest.getCodEstabDestino());
            gr.setRuta(dest.getRuta());
            gr.setDetalles(obtenerDetalles(dest));

            this.guiaRemisionList.add(gr);
        }

        return this.guiaRemisionList;
    }

    private List<DetalleGuiaReporte> obtenerDetalles(Destinatario dest) {
        List list = new ArrayList();
        for (Detalle detalle : dest.getDetalles().getDetalle()) {
            DetalleGuiaReporte dgr = new DetalleGuiaReporte();
            dgr.setCantidad(detalle.getCantidad().toPlainString());
            dgr.setDescripcion(detalle.getDescripcion());
            dgr.setCodigoPrincipal(detalle.getCodigoInterno());
            dgr.setCodigoAuxiliar(detalle.getCodigoAdicional());
            list.add(dgr);
        }
        return list;
    }

    public void setGuiaRemisionList(List<GuiaRemisionReporte> guiaRemisionList) {
        this.guiaRemisionList = guiaRemisionList;
    }

    public List<DetalleGuiaReporte> getDetalles() {
        return this.detalles;
    }

    public void setDetalles(List<DetalleGuiaReporte> detalles) {
        this.detalles = detalles;
    }
}
