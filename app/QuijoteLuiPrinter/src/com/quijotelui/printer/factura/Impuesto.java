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
import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "impuesto", propOrder = {"codigo", "codigoPorcentaje", "tarifa", "baseImponible", "valor"})
public class Impuesto {

    @XmlElement(required = true)
    protected String codigo;

    @XmlElement(required = true)
    protected String codigoPorcentaje;

    @XmlElement(required = true)
    protected BigDecimal tarifa;

    @XmlElement(required = true)
    protected BigDecimal baseImponible;

    @XmlElement(required = true)
    protected BigDecimal valor;

    public Impuesto() {
    }

    public Impuesto(String codigo, String codigoPorcentaje, BigDecimal tarifa, BigDecimal baseImponible, BigDecimal valor) {
        this.codigo = codigo;
        this.codigoPorcentaje = codigoPorcentaje;
        this.tarifa = tarifa;
        this.baseImponible = baseImponible;
        this.valor = valor;
    }

    public String getCodigo() {
        return this.codigo;
    }

    public void setCodigo(String value) {
        this.codigo = value;
    }

    public String getCodigoPorcentaje() {
        return this.codigoPorcentaje;
    }

    public void setCodigoPorcentaje(String value) {
        this.codigoPorcentaje = value;
    }

    public BigDecimal getTarifa() {
        return this.tarifa;
    }

    public void setTarifa(BigDecimal value) {
        this.tarifa = value;
    }

    public BigDecimal getBaseImponible() {
        return this.baseImponible;
    }

    public void setBaseImponible(BigDecimal value) {
        this.baseImponible = value;
    }

    public BigDecimal getValor() {
        return this.valor;
    }

    public void setValor(BigDecimal value) {
        this.valor = value;
    }
}
