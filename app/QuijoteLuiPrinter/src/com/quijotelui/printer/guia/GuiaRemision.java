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
import com.quijotelui.printer.adicional.InfoTributaria;
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
@XmlType(name = "", propOrder = {"infoTributaria", "infoGuiaRemision", "destinatarios", "infoAdicional"})
@XmlRootElement(name = "guiaRemision")
public class GuiaRemision {

    @XmlElement(required = true)
    protected InfoTributaria infoTributaria;

    @XmlElement(required = true)
    protected InfoGuiaRemision infoGuiaRemision;

    @XmlElement(required = true)
    protected Destinatarios destinatarios;
    protected InfoAdicional infoAdicional;

    @XmlAttribute(required = true)
    protected String id;

    @XmlAttribute(required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "NMTOKEN")
    protected String version;

    public InfoTributaria getInfoTributaria() {
        return this.infoTributaria;
    }

    public void setInfoTributaria(InfoTributaria value) {
        this.infoTributaria = value;
    }

    public InfoGuiaRemision getInfoGuiaRemision() {
        return this.infoGuiaRemision;
    }

    public void setInfoGuiaRemision(InfoGuiaRemision value) {
        this.infoGuiaRemision = value;
    }

    public Destinatarios getDestinatarios() {
        return this.destinatarios;
    }

    public void setDestinatarios(Destinatarios value) {
        this.destinatarios = value;
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
    @XmlType(name = "", propOrder = {"dirEstablecimiento", "dirPartida", "razonSocialTransportista", "tipoIdentificacionTransportista", "rucTransportista", "rise", "obligadoContabilidad", "contribuyenteEspecial", "fechaIniTransporte", "fechaFinTransporte", "placa"})
    public static class InfoGuiaRemision {

        protected String dirEstablecimiento;

        @XmlElement(required = true)
        protected String dirPartida;

        @XmlElement(required = true)
        protected String razonSocialTransportista;

        @XmlElement(required = true)
        protected String tipoIdentificacionTransportista;

        @XmlElement(required = true)
        protected String rucTransportista;
        protected String rise;
        protected String obligadoContabilidad;
        protected String contribuyenteEspecial;

        @XmlElement(required = true)
        protected String fechaIniTransporte;

        @XmlElement(required = true)
        protected String fechaFinTransporte;

        @XmlElement(required = true)
        protected String placa;

        public String getDirEstablecimiento() {
            return this.dirEstablecimiento;
        }

        public void setDirEstablecimiento(String value) {
            this.dirEstablecimiento = value;
        }

        public String getDirPartida() {
            return this.dirPartida;
        }

        public void setDirPartida(String value) {
            this.dirPartida = value;
        }

        public String getRazonSocialTransportista() {
            return this.razonSocialTransportista;
        }

        public void setRazonSocialTransportista(String value) {
            this.razonSocialTransportista = value;
        }

        public String getTipoIdentificacionTransportista() {
            return this.tipoIdentificacionTransportista;
        }

        public void setTipoIdentificacionTransportista(String value) {
            this.tipoIdentificacionTransportista = value;
        }

        public String getRucTransportista() {
            return this.rucTransportista;
        }

        public void setRucTransportista(String value) {
            this.rucTransportista = value;
        }

        public String getRise() {
            return this.rise;
        }

        public void setRise(String value) {
            this.rise = value;
        }

        public String getObligadoContabilidad() {
            return this.obligadoContabilidad;
        }

        public void setObligadoContabilidad(String value) {
            this.obligadoContabilidad = value;
        }

        public String getContribuyenteEspecial() {
            return this.contribuyenteEspecial;
        }

        public void setContribuyenteEspecial(String value) {
            this.contribuyenteEspecial = value;
        }

        public String getFechaIniTransporte() {
            return this.fechaIniTransporte;
        }

        public void setFechaIniTransporte(String value) {
            this.fechaIniTransporte = value;
        }

        public String getFechaFinTransporte() {
            return this.fechaFinTransporte;
        }

        public void setFechaFinTransporte(String value) {
            this.fechaFinTransporte = value;
        }

        public String getPlaca() {
            return this.placa;
        }

        public void setPlaca(String value) {
            this.placa = value;
        }
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {"campoAdicional"})
    public static class InfoAdicional {

        @XmlElement(required = true)
        protected List<CampoAdicional> campoAdicional;

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
    @XmlType(name = "", propOrder = {"destinatario"})
    public static class Destinatarios {

        @XmlElement(required = true)
        protected List<Destinatario> destinatario;

        public List<Destinatario> getDestinatario() {
            if (this.destinatario == null) {
                this.destinatario = new ArrayList();
            }
            return this.destinatario;
        }
    }
}
