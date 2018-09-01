package com.cpsj.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the ConsPractObservacion entity.
 */
public class ConsPractObservacionDTO implements Serializable {

    private Long id;

    private String idConsPract;

    private String idObservacion;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdConsPract() {
        return idConsPract;
    }

    public void setIdConsPract(String idConsPract) {
        this.idConsPract = idConsPract;
    }

    public String getIdObservacion() {
        return idObservacion;
    }

    public void setIdObservacion(String idObservacion) {
        this.idObservacion = idObservacion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ConsPractObservacionDTO consPractObservacionDTO = (ConsPractObservacionDTO) o;
        if (consPractObservacionDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), consPractObservacionDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ConsPractObservacionDTO{" +
            "id=" + getId() +
            ", idConsPract='" + getIdConsPract() + "'" +
            ", idObservacion='" + getIdObservacion() + "'" +
            "}";
    }
}
