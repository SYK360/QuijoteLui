/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.quijotelui.printer.factura;

/**
 *
 * @author jorjoluiso
 */
import com.quijotelui.printer.adicional.DetallesAdicionalesReporte;
import com.quijotelui.printer.adicional.FormasPago;
import com.quijotelui.printer.adicional.InformacionAdicional;
import java.util.ArrayList;
import java.util.List;

public class FacturaReporte {

    private Factura factura;
    private String detalle1;
    private String detalle2;
    private String detalle3;
    private List<DetallesAdicionalesReporte> detallesAdiciones;
    private List<InformacionAdicional> infoAdicional;
    private List<FormasPago> formasPago;

    public void setFormasPago(List<FormasPago> formasPago) {
        this.formasPago = formasPago;
    }

    public FacturaReporte(Factura factura) {
        this.factura = factura;
    }

    public Factura getFactura() {
        return this.factura;
    }

    public void setFactura(Factura factura) {
        this.factura = factura;
    }

    public String getDetalle1() {
        return this.detalle1;
    }

    public void setDetalle1(String detalle1) {
        this.detalle1 = detalle1;
    }

    public String getDetalle2() {
        return this.detalle2;
    }

    public void setDetalle2(String detalle2) {
        this.detalle2 = detalle2;
    }

    public String getDetalle3() {
        return this.detalle3;
    }

    public void setDetalle3(String detalle3) {
        this.detalle3 = detalle3;
    }

    public List<DetallesAdicionalesReporte> getDetallesAdiciones() {
        this.detallesAdiciones = new ArrayList();

        for (Factura.Detalles.Detalle det : getFactura().getDetalles().getDetalle()) {
            DetallesAdicionalesReporte detAd = new DetallesAdicionalesReporte();
            detAd.setCodigoPrincipal(det.getCodigoPrincipal());
            detAd.setCodigoAuxiliar(det.getCodigoAuxiliar());
            detAd.setDescripcion(det.getDescripcion());
            detAd.setCantidad(det.getCantidad().toPlainString());
            detAd.setPrecioTotalSinImpuesto(det.getPrecioTotalSinImpuesto().toString());
            detAd.setPrecioUnitario(det.getPrecioUnitario().toString());
            if (det.getDescuento() != null) {
                detAd.setDescuento(det.getDescuento().toString());
            }
            int i = 0;
            if ((det.getDetallesAdicionales() != null) && (det.getDetallesAdicionales().getDetAdicional() != null) && (!det.getDetallesAdicionales().getDetAdicional().isEmpty())) {
                for (Factura.Detalles.Detalle.DetallesAdicionales.DetAdicional detAdicional : det.getDetallesAdicionales().getDetAdicional()) {
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
            
            if (getFormasPago() != null) {
                System.out.println("Forma de Pago");
                detAd.setFormasPago(getFormasPago());
            }
            this.detallesAdiciones.add(detAd);
        }

        return this.detallesAdiciones;
    }

    public void setDetallesAdiciones(List<DetallesAdicionalesReporte> detallesAdiciones) {
        this.detallesAdiciones = detallesAdiciones;
    }

    public List<InformacionAdicional> getInfoAdicional() {
        System.out.println("--->" + getFactura());
        if (getFactura().getInfoAdicional() != null) {
            this.infoAdicional = new ArrayList();
            if ((getFactura().getInfoAdicional().getCampoAdicional() != null) && (!this.factura.getInfoAdicional().getCampoAdicional().isEmpty())) {
                for (Factura.InfoAdicional.CampoAdicional ca : getFactura().getInfoAdicional().getCampoAdicional()) {
                    this.infoAdicional.add(new InformacionAdicional(ca.getValue(), ca.getNombre()));
                }
            }
        }
        return this.infoAdicional;
    }

    public void setInfoAdicional(List<InformacionAdicional> infoAdicional) {
        this.infoAdicional = infoAdicional;
    }

    public List<FormasPago> getFormasPago() {
        System.out.println("--->" + getFactura());
        if (getFactura().getInfoFactura().getPagos() != null) {
            this.formasPago = new ArrayList();
            if ((getFactura().getInfoFactura().getPagos().getPagos() != null) && (!this.factura.getInfoFactura().getPagos().getPagos().isEmpty())) {
                for (Factura.InfoFactura.Pago.DetallePago pa : getFactura().getInfoFactura().getPagos().getPagos()) {
                    this.formasPago.add(new FormasPago(obtenerDetalleFormaPago(pa.getFormaPago()), pa.getTotal().setScale(2).toString()));
                }
            }
        }
        return this.formasPago;
    }

    private String obtenerDetalleFormaPago(String codigo) {

        return "";

    }
}
