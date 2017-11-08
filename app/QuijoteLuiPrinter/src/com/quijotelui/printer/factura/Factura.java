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

import com.quijotelui.printer.InfoTributaria;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {"infoTributaria", "infoFactura", "detalles", "infoAdicional"})
@XmlRootElement(name = "factura")
public class Factura {

    @XmlElement(required = true)
    protected InfoTributaria infoTributaria;

    @XmlElement(required = true)
    protected InfoFactura infoFactura;

    @XmlElement(required = true)
    protected Detalles detalles;
    protected InfoAdicional infoAdicional;

    @XmlAttribute
    protected String id;

    @XmlAttribute
    @XmlSchemaType(name = "anySimpleType")
    protected String version;

    public Factura() {
    }

    public Factura(InfoTributaria infoTributaria, InfoFactura infoFactura, Detalles detalles, InfoAdicional infoAdicional, String id, String version) {
        this.infoTributaria = infoTributaria;
        this.infoFactura = infoFactura;
        this.detalles = detalles;
        this.infoAdicional = infoAdicional;
        this.id = id;
        this.version = version;
    }
    

    public InfoTributaria getInfoTributaria() {
        return this.infoTributaria;
    }

    public void setInfoTributaria(InfoTributaria value) {
        this.infoTributaria = value;
    }

    public InfoFactura getInfoFactura() {
        return this.infoFactura;
    }

    public void setInfoFactura(InfoFactura value) {
        this.infoFactura = value;
    }

    public Detalles getDetalles() {
        return this.detalles;
    }

    public void setDetalles(Detalles value) {
        this.detalles = value;
    }

    public InfoAdicional getInfoAdicional() {
        return this.infoAdicional;
    }

    public void setInfoAdicional(InfoAdicional value) {
        this.infoAdicional = value;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String value) {
        this.id = value;
    }

    public String getVersion() {
        return this.version;
    }

    public void setVersion(String value) {
        this.version = value;
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {"fechaEmision", "dirEstablecimiento", "contribuyenteEspecial", "obligadoContabilidad", "tipoIdentificacionComprador", "guiaRemision", "razonSocialComprador", "identificacionComprador", "totalSinImpuestos", "totalDescuento", "totalConImpuestos", "propina", "importeTotal", "moneda"})
    public static class InfoFactura {

        @XmlElement(required = true)
        protected String fechaEmision;

        @XmlElement(required = true)
        protected String dirEstablecimiento;
        protected String contribuyenteEspecial;
        protected String obligadoContabilidad;

        @XmlElement(required = true)
        protected String tipoIdentificacionComprador;
        protected String guiaRemision;

        @XmlElement(required = true)
        protected String razonSocialComprador;

        @XmlElement(required = true)
        protected String identificacionComprador;

        @XmlElement(required = true)
        protected BigDecimal totalSinImpuestos;

        @XmlElement(required = true)
        protected BigDecimal totalDescuento;

        @XmlElement(required = true)
        protected TotalConImpuestos totalConImpuestos;

        @XmlElement(required = true)
        protected BigDecimal propina;

        @XmlElement(required = true)
        protected BigDecimal importeTotal;
        protected String moneda;

        public InfoFactura() {
        }
        
        

        public InfoFactura(String fechaEmision, String dirEstablecimiento, String contribuyenteEspecial, String obligadoContabilidad, String tipoIdentificacionComprador, String guiaRemision, String razonSocialComprador, String identificacionComprador, BigDecimal totalSinImpuestos, BigDecimal totalDescuento, TotalConImpuestos totalConImpuestos, BigDecimal propina, BigDecimal importeTotal, String moneda) {
            this.fechaEmision = fechaEmision;
            this.dirEstablecimiento = dirEstablecimiento;
            this.contribuyenteEspecial = contribuyenteEspecial;
            this.obligadoContabilidad = obligadoContabilidad;
            this.tipoIdentificacionComprador = tipoIdentificacionComprador;
            this.guiaRemision = guiaRemision;
            this.razonSocialComprador = razonSocialComprador;
            this.identificacionComprador = identificacionComprador;
            this.totalSinImpuestos = totalSinImpuestos;
            this.totalDescuento = totalDescuento;
            this.totalConImpuestos = totalConImpuestos;
            this.propina = propina;
            this.importeTotal = importeTotal;
            this.moneda = moneda;
        }

        
        public String getFechaEmision() {
            return this.fechaEmision;
        }

        public void setFechaEmision(String value) {
            this.fechaEmision = value;
        }

        public String getDirEstablecimiento() {
            return this.dirEstablecimiento;
        }

        public void setDirEstablecimiento(String value) {
            this.dirEstablecimiento = value;
        }

        public String getContribuyenteEspecial() {
            return this.contribuyenteEspecial;
        }

        public void setContribuyenteEspecial(String value) {
            this.contribuyenteEspecial = value;
        }

        public String getObligadoContabilidad() {
            return this.obligadoContabilidad;
        }

        public void setObligadoContabilidad(String value) {
            this.obligadoContabilidad = value;
        }

        public String getTipoIdentificacionComprador() {
            return this.tipoIdentificacionComprador;
        }

        public void setTipoIdentificacionComprador(String value) {
            this.tipoIdentificacionComprador = value;
        }

        public String getGuiaRemision() {
            return this.guiaRemision;
        }

        public void setGuiaRemision(String value) {
            this.guiaRemision = value;
        }

        public String getRazonSocialComprador() {
            return this.razonSocialComprador;
        }

        public void setRazonSocialComprador(String value) {
            this.razonSocialComprador = value;
        }

        public String getIdentificacionComprador() {
            return this.identificacionComprador;
        }

        public void setIdentificacionComprador(String value) {
            this.identificacionComprador = value;
        }

        public BigDecimal getTotalSinImpuestos() {
            return this.totalSinImpuestos;
        }

        public void setTotalSinImpuestos(BigDecimal value) {
            this.totalSinImpuestos = value;
        }

        public BigDecimal getTotalDescuento() {
            return this.totalDescuento;
        }

        public void setTotalDescuento(BigDecimal value) {
            this.totalDescuento = value;
        }

        public TotalConImpuestos getTotalConImpuestos() {
            return this.totalConImpuestos;
        }

        public void setTotalConImpuestos(TotalConImpuestos value) {
            this.totalConImpuestos = value;
        }

        public BigDecimal getPropina() {
            return this.propina;
        }

        public void setPropina(BigDecimal value) {
            this.propina = value;
        }

        public BigDecimal getImporteTotal() {
            return this.importeTotal;
        }

        public void setImporteTotal(BigDecimal value) {
            this.importeTotal = value;
        }

        public String getMoneda() {
            return this.moneda;
        }

        public void setMoneda(String value) {
            this.moneda = value;
        }

        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {"totalImpuesto"})
        public static class TotalConImpuestos {

            @XmlElement(required = true)
            protected List<TotalImpuesto> totalImpuesto;

            public TotalConImpuestos() {
            }

            public TotalConImpuestos(List<TotalImpuesto> totalImpuesto) {
                this.totalImpuesto = totalImpuesto;
            }
            
            

            public List<TotalImpuesto> getTotalImpuesto() {
                if (this.totalImpuesto == null) {
                    this.totalImpuesto = new ArrayList();
                }
                return this.totalImpuesto;
            }

            public void setTotalImpuesto(List<TotalImpuesto> totalImpuesto) {
                this.totalImpuesto = totalImpuesto;
            }

            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "", propOrder = {"codigo", "codigoPorcentaje", "baseImponible", "tarifa", "valor"})
            public static class TotalImpuesto {

                @XmlElement(required = true)
                protected String codigo;

                @XmlElement(required = true)
                protected String codigoPorcentaje;

                @XmlElement(required = true)
                protected BigDecimal baseImponible;
                protected BigDecimal tarifa;

                @XmlElement(required = true)
                protected BigDecimal valor;

                public TotalImpuesto() {
                }
                
                

                public TotalImpuesto(String codigo, String codigoPorcentaje, BigDecimal baseImponible, BigDecimal tarifa, BigDecimal valor) {
                    this.codigo = codigo;
                    this.codigoPorcentaje = codigoPorcentaje;
                    this.baseImponible = baseImponible;
                    this.tarifa = tarifa;
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

                public BigDecimal getBaseImponible() {
                    return this.baseImponible;
                }

                public void setBaseImponible(BigDecimal value) {
                    this.baseImponible = value;
                }

                public BigDecimal getTarifa() {
                    return this.tarifa;
                }

                public void setTarifa(BigDecimal value) {
                    this.tarifa = value;
                }

                public BigDecimal getValor() {
                    return this.valor;
                }

                public void setValor(BigDecimal value) {
                    this.valor = value;
                }
            }
        }
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {"campoAdicional"})
    public static class InfoAdicional {

        @XmlElement(required = true)
        protected List<CampoAdicional> campoAdicional;

        public InfoAdicional() {
        }

        
        public InfoAdicional(List<CampoAdicional> campoAdicional) {
            this.campoAdicional = campoAdicional;
        }

        public List<CampoAdicional> getCampoAdicional() {
            if (this.campoAdicional == null) {
                this.campoAdicional = new ArrayList();
            }
            return this.campoAdicional;
        }

        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {"value"})
        public static class CampoAdicional {

            @XmlValue
            protected String value;

            @XmlAttribute
            protected String nombre;

            public CampoAdicional() {
            }
            
            

            public CampoAdicional(String value, String nombre) {
                this.value = value;
                this.nombre = nombre;
            }

            public String getValue() {
                return this.value;
            }

            public void setValue(String value) {
                this.value = value;
            }

            public String getNombre() {
                return this.nombre;
            }

            public void setNombre(String value) {
                this.nombre = value;
            }
        }
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {"detalle"})
    public static class Detalles {

        @XmlElement(required = true)
        protected List<Detalle> detalle;

        public Detalles() {
        }

        
        
        public Detalles(List<Detalle> detalle) {
            this.detalle = detalle;
        }

        public List<Detalle> getDetalle() {
            if (this.detalle == null) {
                this.detalle = new ArrayList();
            }
            return this.detalle;
        }

        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {"codigoPrincipal", "codigoAuxiliar", "descripcion", "cantidad", "precioUnitario", "descuento", "precioTotalSinImpuesto", "detallesAdicionales", "impuestos"})
        public static class Detalle {

            @XmlElement(required = true)
            protected String codigoPrincipal;
            protected String codigoAuxiliar;

            @XmlElement(required = true)
            protected String descripcion;

            @XmlElement(required = true)
            protected BigDecimal cantidad;

            @XmlElement(required = true)
            protected BigDecimal precioUnitario;

            @XmlElement(required = true)
            protected BigDecimal descuento;

            @XmlElement(required = true)
            protected BigDecimal precioTotalSinImpuesto;
            protected DetallesAdicionales detallesAdicionales;

            @XmlElement(required = true)
            protected Impuestos impuestos;

            public Detalle() {
            }
            
            

            public Detalle(String codigoPrincipal, String codigoAuxiliar, String descripcion, BigDecimal cantidad, BigDecimal precioUnitario, BigDecimal descuento, BigDecimal precioTotalSinImpuesto, DetallesAdicionales detallesAdicionales, Impuestos impuestos) {
                this.codigoPrincipal = codigoPrincipal;
                this.codigoAuxiliar = codigoAuxiliar;
                this.descripcion = descripcion;
                this.cantidad = cantidad;
                this.precioUnitario = precioUnitario;
                this.descuento = descuento;
                this.precioTotalSinImpuesto = precioTotalSinImpuesto;
                this.detallesAdicionales = detallesAdicionales;
                this.impuestos = impuestos;
            }

            public String getCodigoPrincipal() {
                return this.codigoPrincipal;
            }

            public void setCodigoPrincipal(String value) {
                this.codigoPrincipal = value;
            }

            public String getCodigoAuxiliar() {
                return this.codigoAuxiliar;
            }

            public void setCodigoAuxiliar(String value) {
                this.codigoAuxiliar = value;
            }

            public String getDescripcion() {
                return this.descripcion;
            }

            public void setDescripcion(String value) {
                this.descripcion = value;
            }

            public BigDecimal getCantidad() {
                return this.cantidad;
            }

            public void setCantidad(BigDecimal value) {
                this.cantidad = value;
            }

            public BigDecimal getPrecioUnitario() {
                return this.precioUnitario;
            }

            public void setPrecioUnitario(BigDecimal value) {
                this.precioUnitario = value;
            }

            public BigDecimal getDescuento() {
                return this.descuento;
            }

            public void setDescuento(BigDecimal value) {
                this.descuento = value;
            }

            public BigDecimal getPrecioTotalSinImpuesto() {
                return this.precioTotalSinImpuesto;
            }

            public void setPrecioTotalSinImpuesto(BigDecimal value) {
                this.precioTotalSinImpuesto = value;
            }

            public DetallesAdicionales getDetallesAdicionales() {
                return this.detallesAdicionales;
            }

            public void setDetallesAdicionales(DetallesAdicionales value) {
                this.detallesAdicionales = value;
            }

            public Impuestos getImpuestos() {
                return this.impuestos;
            }

            public void setImpuestos(Impuestos value) {
                this.impuestos = value;
            }

            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "", propOrder = {"impuesto"})
            public static class Impuestos {

                @XmlElement(required = true)
                protected List<Impuesto> impuesto;

                public Impuestos() {
                }
                
                

                public Impuestos(List<Impuesto> impuesto) {
                    this.impuesto = impuesto;
                }

                public List<Impuesto> getImpuesto() {
                    if (this.impuesto == null) {
                        this.impuesto = new ArrayList();
                    }
                    return this.impuesto;
                }
            }

            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "", propOrder = {"detAdicional"})
            public static class DetallesAdicionales {

                @XmlElement(required = true)
                protected List<DetAdicional> detAdicional;

                public List<DetAdicional> getDetAdicional() {
                    if (this.detAdicional == null) {
                        this.detAdicional = new ArrayList();
                    }
                    return this.detAdicional;
                }

                @XmlAccessorType(XmlAccessType.FIELD)
                @XmlType(name = "")
                public static class DetAdicional {

                    @XmlAttribute
                    protected String nombre;

                    @XmlAttribute
                    protected String valor;

                    public DetAdicional() {
                    }

                    public DetAdicional(String nombre, String valor) {
                        this.nombre = nombre;
                        this.valor = valor;
                    }
                    
                    

                    public String getNombre() {
                        return this.nombre;
                    }

                    public void setNombre(String value) {
                        this.nombre = value;
                    }

                    public String getValor() {
                        return this.valor;
                    }

                    public void setValor(String value) {
                        this.valor = value;
                    }
                }
            }
        }
    }
}
