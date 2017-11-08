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

public class DetalleGuiaReporte
{
  private String cantidad;
  private String descripcion;
  private String codigoPrincipal;
  private String codigoAuxiliar;

  public String getCantidad()
  {
    return this.cantidad;
  }

  public void setCantidad(String cantidad)
  {
    this.cantidad = cantidad;
  }

  public String getDescripcion()
  {
    return this.descripcion;
  }

  public void setDescripcion(String descripcion)
  {
    this.descripcion = descripcion;
  }

  public String getCodigoPrincipal()
  {
    return this.codigoPrincipal;
  }

  public void setCodigoPrincipal(String codigoPrincipal)
  {
    this.codigoPrincipal = codigoPrincipal;
  }

  public String getCodigoAuxiliar()
  {
    return this.codigoAuxiliar;
  }

  public void setCodigoAuxiliar(String codigoAuxiliar)
  {
    this.codigoAuxiliar = codigoAuxiliar;
  }
}