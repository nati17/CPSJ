package com.cpsj.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the ObraSocial entity.
 */
public class ObraSocialDTO implements Serializable {

    private Long id;

    private String codigoObraSocial;

    private String nombreObraSocial;

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

    public String getNombreObraSocial() {
        return nombreObraSocial;
    }

    public void setNombreObraSocial(String nombreObraSocial) {
        this.nombreObraSocial = nombreObraSocial;
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
            ", nombreObraSocial='" + getNombreObraSocial() + "'" +
            "}";
    }
}
