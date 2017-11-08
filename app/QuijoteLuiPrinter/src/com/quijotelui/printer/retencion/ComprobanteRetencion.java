/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.quijotelui.printer.retencion;

/**
 *
 * @author jorjoluiso
 */
import com.quijotelui.printer.InfoTributaria;
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
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="", propOrder={"infoTributaria", "infoCompRetencion", "impuestos", "infoAdicional"})
@XmlRootElement(name="comprobanteRetencion")
public class ComprobanteRetencion
{

  @XmlElement(required=true)
  protected InfoTributaria infoTributaria;

  @XmlElement(required=true)
  protected InfoCompRetencion infoCompRetencion;

  @XmlElement(required=true)
  protected Impuestos impuestos;
  protected InfoAdicional infoAdicional;

  @XmlAttribute
  protected String id;

  @XmlAttribute(required=true)
  @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
  @XmlSchemaType(name="NMTOKEN")
  protected String version;

  public InfoTributaria getInfoTributaria()
  {
    return this.infoTributaria;
  }

  public void setInfoTributaria(InfoTributaria value)
  {
    this.infoTributaria = value;
  }

  public InfoCompRetencion getInfoCompRetencion()
  {
    return this.infoCompRetencion;
  }

  public void setInfoCompRetencion(InfoCompRetencion value)
  {
    this.infoCompRetencion = value;
  }

  public Impuestos getImpuestos()
  {
    return this.impuestos;
  }

  public void setImpuestos(Impuestos value)
  {
    this.impuestos = value;
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
  @XmlType(name="", propOrder={"fechaEmision", "dirEstablecimiento", "contribuyenteEspecial", "obligadoContabilidad", "tipoIdentificacionSujetoRetenido", "razonSocialSujetoRetenido", "identificacionSujetoRetenido", "periodoFiscal"})
  public static class InfoCompRetencion
  {

    @XmlElement(required=true)
    protected String fechaEmision;
    protected String dirEstablecimiento;
    protected String contribuyenteEspecial;
    protected String obligadoContabilidad;

    @XmlElement(required=true)
    protected String tipoIdentificacionSujetoRetenido;

    @XmlElement(required=true)
    protected String razonSocialSujetoRetenido;

    @XmlElement(required=true)
    protected String identificacionSujetoRetenido;

    @XmlElement(required=true)
    protected String periodoFiscal;

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

    public String getTipoIdentificacionSujetoRetenido()
    {
      return this.tipoIdentificacionSujetoRetenido;
    }

    public void setTipoIdentificacionSujetoRetenido(String value)
    {
      this.tipoIdentificacionSujetoRetenido = value;
    }

    public String getRazonSocialSujetoRetenido()
    {
      return this.razonSocialSujetoRetenido;
    }

    public void setRazonSocialSujetoRetenido(String value)
    {
      this.razonSocialSujetoRetenido = value;
    }

    public String getIdentificacionSujetoRetenido()
    {
      return this.identificacionSujetoRetenido;
    }

    public void setIdentificacionSujetoRetenido(String value)
    {
      this.identificacionSujetoRetenido = value;
    }

    public String getPeriodoFiscal()
    {
      return this.periodoFiscal;
    }

    public void setPeriodoFiscal(String value)
    {
      this.periodoFiscal = value;
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