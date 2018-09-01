package com.cpsj.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Observacion entity.
 */
public class ObservacionDTO implements Serializable {

    private Long id;

    private String idObservacion;

    private String descripcionObservacion;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdObservacion() {
        return idObservacion;
    }

    public void setIdObservacion(String idObservacion) {
        this.idObservacion = idObservacion;
    }

    public String getDescripcionObservacion() {
        return descripcionObservacion;
    }

    public void setDescripcionObservacion(String descripcionObservacion) {
        this.descripcionObservacion = descripcionObservacion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ObservacionDTO observacionDTO = (ObservacionDTO) o;
        if (observacionDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), observacionDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ObservacionDTO{" +
            "id=" + getId() +
            ", idObservacion='" + getIdObservacion() + "'" +
            ", descripcionObservacion='" + getDescripcionObservacion() + "'" +
            "}";
    }
}
