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
import com.quijotelui.printer.adicional.InfoTributaria;
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
@XmlType(name="", propOrder={"infoTributaria", "infoFactura", "detalles", "infoAdicional"})
@XmlRootElement(name="factura")
public class Factura
{
  @XmlElement(required=true)
  protected InfoTributaria infoTributaria;
  @XmlElement(required=true)
  protected InfoFactura infoFactura;
  @XmlElement(required=true)
  protected Detalles detalles;
  protected InfoAdicional infoAdicional;
  @XmlAttribute
  protected String id;
  @XmlAttribute
  @XmlSchemaType(name="anySimpleType")
  protected String version;
  
  public InfoTributaria getInfoTributaria()
  {
    return this.infoTributaria;
  }
  
  public void setInfoTributaria(InfoTributaria value)
  {
    this.infoTributaria = value;
  }
  
  public InfoFactura getInfoFactura()
  {
    return this.infoFactura;
  }
  
  public void setInfoFactura(InfoFactura value)
  {
    this.infoFactura = value;
  }
  
  public Detalles getDetalles()
  {
    return this.detalles;
  }
  
  public void setDetalles(Detalles value)
  {
    this.detalles = value;
  }
  
  public InfoAdicional getInfoAdicional()
  {
    return this.infoAdicional;
  }
  
  public void setInfoAdicional(InfoAdicional value)
  {
    this.infoAdicional = value;
  }
  
  public String getId()
  {
    return this.id;
  }
  
  public void setId(String value)
  {
    this.id = value;
  }
  
  public String getVersion()
  {
    return this.version;
  }
  
  public void setVersion(String value)
  {
    this.version = value;
  }
  
  @XmlAccessorType(XmlAccessType.FIELD)
  @XmlType(name="", propOrder={"detalle"})
  public static class Detalles
  {
    @XmlElement(required=true)
    protected List<Detalle> detalle;
    
    public List<Detalle> getDetalle()
    {
      if (this.detalle == null) {
        this.detalle = new ArrayList();
      }
      return this.detalle;
    }
    
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name="", propOrder={"codigoPrincipal", "codigoAuxiliar", "descripcion", "cantidad", "precioUnitario", "precioSinSubsidio", "descuento", "precioTotalSinImpuesto", "detallesAdicionales", "impuestos"})
    public static class Detalle
    {
      @XmlElement(required=true)
      protected String codigoPrincipal;
      protected String codigoAuxiliar;
      @XmlElement(required=true)
      protected String descripcion;
      @XmlElement(required=true)
      protected BigDecimal cantidad;
      @XmlElement(required=true)
      protected BigDecimal precioUnitario;
      @XmlElement(required=true)
      protected BigDecimal precioSinSubsidio;
      @XmlElement(required=true)
      protected BigDecimal descuento;
      @XmlElement(required=true)
      protected BigDecimal precioTotalSinImpuesto;
      protected DetallesAdicionales detallesAdicionales;
      @XmlElement(required=true)
      protected Impuestos impuestos;
      
      public String getCodigoPrincipal()
      {
        return this.codigoPrincipal;
      }
      
      public void setCodigoPrincipal(String value)
      {
        this.codigoPrincipal = value;
      }
      
      public String getCodigoAuxiliar()
      {
        return this.codigoAuxiliar;
      }
      
      public void setCodigoAuxiliar(String value)
      {
        this.codigoAuxiliar = value;
      }
      
      public String getDescripcion()
      {
        return this.descripcion;
      }
      
      public void setDescripcion(String value)
      {
        this.descripcion = value;
      }
      
      public BigDecimal getCantidad()
      {
        return this.cantidad;
      }
      
      public void setCantidad(BigDecimal value)
      {
        this.cantidad = value;
      }
      
      public BigDecimal getPrecioUnitario()
      {
        return this.precioUnitario;
      }
      
      public void setPrecioSinSubsidio(BigDecimal value)
      {
        this.precioSinSubsidio = value;
      }
      
      public BigDecimal getPrecioSinSubsidio()
      {
        return this.precioSinSubsidio;
      }
      
      public void setPrecioUnitario(BigDecimal value)
      {
        this.precioUnitario = value;
      }
      
      public BigDecimal getDescuento()
      {
        return this.descuento;
      }
      
      public void setDescuento(BigDecimal value)
      {
        this.descuento = value;
      }
      
      public BigDecimal getPrecioTotalSinImpuesto()
      {
        return this.precioTotalSinImpuesto;
      }
      
      public void setPrecioTotalSinImpuesto(BigDecimal value)
      {
        this.precioTotalSinImpuesto = value;
      }
      
      public DetallesAdicionales getDetallesAdicionales()
      {
        return this.detallesAdicionales;
      }
      
      public void setDetallesAdicionales(DetallesAdicionales value)
      {
        this.detallesAdicionales = value;
      }
      
      public Impuestos getImpuestos()
      {
        return this.impuestos;
      }
      
      public void setImpuestos(Impuestos value)
      {
        this.impuestos = value;
      }
      
      @XmlAccessorType(XmlAccessType.FIELD)
      @XmlType(name="", propOrder={"detAdicional"})
      public static class DetallesAdicionales
      {
        @XmlElement(required=true)
        protected List<DetAdicional> detAdicional;
        
        public List<DetAdicional> getDetAdicional()
        {
          if (this.detAdicional == null) {
            this.detAdicional = new ArrayList();
          }
          return this.detAdicional;
        }
        
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name="")
        public static class DetAdicional
        {
          @XmlAttribute
          protected String nombre;
          @XmlAttribute
          protected String valor;
          
          public String getNombre()
          {
            return this.nombre;
          }
          
          public void setNombre(String value)
          {
            this.nombre = value;
          }
          
          public String getValor()
          {
            return this.valor;
          }
          
          public void setValor(String value)
          {
            this.valor = value;
          }
        }
      }
      
      @XmlAccessorType(XmlAccessType.FIELD)
      @XmlType(name="", propOrder={"impuesto"})
      public static class Impuestos
      {
        @XmlElement(required=true)
        protected List<Impuesto> impuesto;
        
        public List<Impuesto> getImpuesto()
        {
          if (this.impuesto == null) {
            this.impuesto = new ArrayList();
          }
          return this.impuesto;
        }
      }
    }
  }
  
  @XmlAccessorType(XmlAccessType.FIELD)
  @XmlType(name="", propOrder={"campoAdicional"})
  public static class InfoAdicional
  {
    @XmlElement(required=true)
    protected List<CampoAdicional> campoAdicional;
    
    public List<CampoAdicional> getCampoAdicional()
    {
      if (this.campoAdicional == null) {
        this.campoAdicional = new ArrayList();
      }
      return this.campoAdicional;
    }
    
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name="", propOrder={"value"})
    public static class CampoAdicional
    {
      @XmlValue
      protected String value;
      @XmlAttribute
      protected String nombre;
      
      public String getValue()
      {
        return this.value;
      }
      
      public void setValue(String value)
      {
        this.value = value;
      }
      
      public String getNombre()
      {
        return this.nombre;
      }
      
      public void setNombre(String value)
      {
        this.nombre = value;
      }
    }
  }
  
  @XmlAccessorType(XmlAccessType.FIELD)
  @XmlType(name="", propOrder={"fechaEmision", "dirEstablecimiento", "contribuyenteEspecial", "obligadoContabilidad", "tipoIdentificacionComprador", "guiaRemision", "razonSocialComprador", "identificacionComprador", "direccionComprador", "totalSinImpuestos", "totalSubsidio", "totalDescuento", "totalConImpuestos", "compensaciones", "propina", "importeTotal", "moneda", "pagos"})
  public static class InfoFactura
  {
    @XmlElement(required=true)
    protected String fechaEmision;
    @XmlElement(required=true)
    protected String dirEstablecimiento;
    protected String contribuyenteEspecial;
    protected String obligadoContabilidad;
    @XmlElement(required=true)
    protected String tipoIdentificacionComprador;
    protected String guiaRemision;
    @XmlElement(required=true)
    protected String razonSocialComprador;
    @XmlElement(required=true)
    protected String identificacionComprador;
    protected String direccionComprador;
    @XmlElement(required=true)
    protected BigDecimal totalSinImpuestos;
    @XmlElement(required=true)
    protected BigDecimal totalSubsidio;
    @XmlElement(required=true)
    protected BigDecimal totalDescuento;
    @XmlElement(required=true)
    protected TotalConImpuestos totalConImpuestos;
    protected compensacion compensaciones;
    @XmlElement(required=true)
    protected BigDecimal propina;
    @XmlElement(required=true)
    protected BigDecimal importeTotal;
    protected String moneda;
    protected Pago pagos;
    
    public String getFechaEmision()
    {
      return this.fechaEmision;
    }
    
    public void setFechaEmision(String value)
    {
      this.fechaEmision = value;
    }
    
    public String getDirEstablecimiento()
    {
      return this.dirEstablecimiento;
    }
    
    public void setDirEstablecimiento(String value)
    {
      this.dirEstablecimiento = value;
    }
    
    public String getContribuyenteEspecial()
    {
      return this.contribuyenteEspecial;
    }
    
    public void setContribuyenteEspecial(String value)
    {
      this.contribuyenteEspecial = value;
    }
    
    public String getObligadoContabilidad()
    {
      return this.obligadoContabilidad;
    }
    
    public void setObligadoContabilidad(String value)
    {
      this.obligadoContabilidad = value;
    }
    
    public String getTipoIdentificacionComprador()
    {
      return this.tipoIdentificacionComprador;
    }
    
    public void setTipoIdentificacionComprador(String value)
    {
      this.tipoIdentificacionComprador = value;
    }
    
    public String getGuiaRemision()
    {
      return this.guiaRemision;
    }
    
    public void setGuiaRemision(String value)
    {
      this.guiaRemision = value;
    }
    
    public String getRazonSocialComprador()
    {
      return this.razonSocialComprador;
    }
    
    public void setRazonSocialComprador(String value)
    {
      this.razonSocialComprador = value;
    }
    
    public String getIdentificacionComprador()
    {
      return this.identificacionComprador;
    }
    
    public void setIdentificacionComprador(String value)
    {
      this.identificacionComprador = value;
    }
    
    public String getIDireccionComprador()
    {
      return this.direccionComprador;
    }
    
    public void setDireccionComprador(String value)
    {
      this.direccionComprador = value;
    }
    
    public BigDecimal getTotalSinImpuestos()
    {
      return this.totalSinImpuestos;
    }
    
    public void setTotalSinImpuestos(BigDecimal value)
    {
      this.totalSinImpuestos = value;
    }
    
    public BigDecimal getTotalSubsidio()
    {
      return this.totalSubsidio;
    }
    
    public void setTotalSubsidio(BigDecimal value)
    {
      this.totalSubsidio = value;
    }
    
    public BigDecimal getTotalDescuento()
    {
      return this.totalDescuento;
    }
    
    public void setTotalDescuento(BigDecimal value)
    {
      this.totalDescuento = value;
    }
    
    public TotalConImpuestos getTotalConImpuestos()
    {
      return this.totalConImpuestos;
    }
    
    public void setTotalConImpuestos(TotalConImpuestos value)
    {
      this.totalConImpuestos = value;
    }
    
    public Pago getPagos()
    {
      return this.pagos;
    }
    
    public void setCompensaciones(compensacion compensaciones)
    {
      this.compensaciones = compensaciones;
    }
    
    public compensacion getCompensaciones()
    {
      return this.compensaciones;
    }
    
    public void setPagos(Pago pagos)
    {
      this.pagos = pagos;
    }
    
    public BigDecimal getPropina()
    {
      return this.propina;
    }
    
    public void setPropina(BigDecimal value)
    {
      this.propina = value;
    }
    
    public BigDecimal getImporteTotal()
    {
      return this.importeTotal;
    }
    
    public void setImporteTotal(BigDecimal value)
    {
      this.importeTotal = value;
    }
    
    public String getMoneda()
    {
      return this.moneda;
    }
    
    public void setMoneda(String value)
    {
      this.moneda = value;
    }
    
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name="", propOrder={"totalImpuesto"})
    public static class TotalConImpuestos
    {
      @XmlElement(required=true)
      protected List<TotalImpuesto> totalImpuesto;
      
      public List<TotalImpuesto> getTotalImpuesto()
      {
        if (this.totalImpuesto == null) {
          this.totalImpuesto = new ArrayList();
        }
        return this.totalImpuesto;
      }
      
      @XmlAccessorType(XmlAccessType.FIELD)
      @XmlType(name="", propOrder={"codigo", "codigoPorcentaje", "baseImponible", "tarifa", "valor"})
      public static class TotalImpuesto
      {
        @XmlElement(required=true)
        protected String codigo;
        @XmlElement(required=true)
        protected String codigoPorcentaje;
        @XmlElement(required=true)
        protected BigDecimal baseImponible;
        protected BigDecimal tarifa;
        @XmlElement(required=true)
        protected BigDecimal valor;
        
        public String getCodigo()
        {
          return this.codigo;
        }
        
        public void setCodigo(String value)
        {
          this.codigo = value;
        }
        
        public String getCodigoPorcentaje()
        {
          return this.codigoPorcentaje;
        }
        
        public void setCodigoPorcentaje(String value)
        {
          this.codigoPorcentaje = value;
        }
        
        public BigDecimal getBaseImponible()
        {
          return this.baseImponible;
        }
        
        public void setBaseImponible(BigDecimal value)
        {
          this.baseImponible = value;
        }
        
        public BigDecimal getTarifa()
        {
          return this.tarifa;
        }
        
        public void setTarifa(BigDecimal value)
        {
          this.tarifa = value;
        }
        
        public BigDecimal getValor()
        {
          return this.valor;
        }
        
        public void setValor(BigDecimal value)
        {
          this.valor = value;
        }
      }
    }
    
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name="", propOrder={"compensacion"})
    public static class compensacion
    {
      @XmlElement(required=true)
      protected List<detalleCompensaciones> compensacion;
      
      public List<detalleCompensaciones> getCompensaciones()
      {
        if (this.compensacion == null) {
          this.compensacion = new ArrayList();
        }
        return this.compensacion;
      }
      
      @XmlAccessorType(XmlAccessType.FIELD)
      @XmlType(name="", propOrder={"codigo", "tarifa", "valor"})
      public static class detalleCompensaciones
      {
        @XmlElement(required=true)
        protected int codigo;
        @XmlElement(required=true)
        protected int tarifa;
        @XmlElement(required=true)
        protected BigDecimal valor;
        
        public int getCodigo()
        {
          return this.codigo;
        }
        
        public void setCodigo(int value)
        {
          this.codigo = value;
        }
        
        public int getTarifa()
        {
          return this.tarifa;
        }
        
        public void setTarifa(int value)
        {
          this.tarifa = value;
        }
        
        public BigDecimal getValor()
        {
          return this.valor;
        }
        
        public void setValor(BigDecimal valor)
        {
          this.valor = valor;
        }
      }
    }
    
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name="", propOrder={"pago"})
    public static class Pago
    {
      @XmlElement(required=true)
      protected List<DetallePago> pago;
      
      public List<DetallePago> getPagos()
      {
        if (this.pago == null) {
          this.pago = new ArrayList();
        }
        return this.pago;
      }
      
      @XmlAccessorType(XmlAccessType.FIELD)
      @XmlType(name="", propOrder={"formaPago", "total", "plazo", "unidadTiempo"})
      public static class DetallePago
      {
        @XmlElement(required=true)
        protected String formaPago;
        @XmlElement(required=true)
        protected BigDecimal total;
        protected String plazo;
        protected String unidadTiempo;
        
        public String getFormaPago()
        {
          return this.formaPago;
        }
        
        public void setFormaPago(String formaPago)
        {
          this.formaPago = formaPago;
        }
        
        public BigDecimal getTotal()
        {
          return this.total;
        }
        
        public void setTotal(BigDecimal total)
        {
          this.total = total;
        }
        
        public String getPlazo()
        {
          return this.plazo;
        }
        
        public void setPlazo(String plazo)
        {
          this.plazo = plazo;
        }
        
        public String getUnidadTiempo()
        {
          return this.unidadTiempo;
        }
        
        public void setUnidadTiempo(String unidadTiempo)
        {
          this.unidadTiempo = unidadTiempo;
        }
      }
    }
  }
}
