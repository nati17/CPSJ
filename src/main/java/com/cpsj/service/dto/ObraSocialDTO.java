package com.cpsj.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the ObraSocial entity.
 */
public class ObraSocialDTO implements Serializable {

    private Long id;

    private String codigoObraSocial;

    private String nombreOSocial;

    private String direecionOSocial;

    private String telefonoOSocial;

    private String emailOSocial;

    private Long pacienteObraSocialId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigoObraSocial() {
        return codigoObraSocial;
    }

    public void setCodigoObraSocial(String codigoObraSocial) {
        this.codigoObraSocial = codigoObraSocial;
    }

    public String getNombreOSocial() {
        return nombreOSocial;
    }

    public void setNombreOSocial(String nombreOSocial) {
        this.nombreOSocial = nombreOSocial;
    }

    public String getDireecionOSocial() {
        return direecionOSocial;
    }

    public void setDireecionOSocial(String direecionOSocial) {
        this.direecionOSocial = direecionOSocial;
    }

    public String getTelefonoOSocial() {
        return telefonoOSocial;
    }

    public void setTelefonoOSocial(String telefonoOSocial) {
        this.telefonoOSocial = telefonoOSocial;
    }

    public String getEmailOSocial() {
        return emailOSocial;
    }

    public void setEmailOSocial(String emailOSocial) {
        this.emailOSocial = emailOSocial;
    }

    public Long getPacienteObraSocialId() {
        return pacienteObraSocialId;
    }

    public void setPacienteObraSocialId(Long pacienteObraSocialId) {
        this.pacienteObraSocialId = pacienteObraSocialId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ObraSocialDTO obraSocialDTO = (ObraSocialDTO) o;
        if (obraSocialDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), obraSocialDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ObraSocialDTO{" +
            "id=" + getId() +
            ", codigoObraSocial='" + getCodigoObraSocial() + "'" +
            ", nombreOSocial='" + getNombreOSocial() + "'" +
            ", direecionOSocial='" + getDireecionOSocial() + "'" +
            ", telefonoOSocial='" + getTelefonoOSocial() + "'" +
            ", emailOSocial='" + getEmailOSocial() + "'" +
            ", pacienteObraSocial=" + getPacienteObraSocialId() +
            "}";
    }
}
