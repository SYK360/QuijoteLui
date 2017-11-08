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

/**
 *
 * @author jorjoluiso
 */

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="detalle", propOrder={"codigoInterno", "codigoAdicional", "descripcion", "cantidad", "detallesAdicionales"})
public class Detalle
{

  @XmlElement(required=true)
  protected String codigoInterno;
  protected String codigoAdicional;

  @XmlElement(required=true)
  protected String descripcion;

  @XmlElement(required=true)
  protected BigDecimal cantidad;
  protected DetallesAdicionales detallesAdicionales;

  public String getCodigoInterno()
  {
    return this.codigoInterno;
  }

  public void setCodigoInterno(String value)
  {
    this.codigoInterno = value;
  }

  public String getCodigoAdicional()
  {
    return this.codigoAdicional;
  }

  public void setCodigoAdicional(String value)
  {
    this.codigoAdicional = value;
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

  public DetallesAdicionales getDetallesAdicionales()
  {
    return this.detallesAdicionales;
  }

  public void setDetallesAdicionales(DetallesAdicionales value)
  {
    this.detallesAdicionales = value;
  }

  @XmlAccessorType(XmlAccessType.FIELD)
  @XmlType(name="", propOrder={"detAdicional"})
  public static class DetallesAdicionales
  {
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
}