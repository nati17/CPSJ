package com.cpsj.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Especialidad entity.
 */
public class EspecialidadDTO implements Serializable {

    private Long id;

    private String codigoEspecialidad;

    private String nombreEspecialidad;

    private String descripcionEspecialidad;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigoEspecialidad() {
        return codigoEspecialidad;
    }

    public void setCodigoEspecialidad(String codigoEspecialidad) {
        this.codigoEspecialidad = codigoEspecialidad;
    }

    public String getNombreEspecialidad() {
        return nombreEspecialidad;
    }

    public void setNombreEspecialidad(String nombreEspecialidad) {
        this.nombreEspecialidad = nombreEspecialidad;
    }

    public String getDescripcionEspecialidad() {
        return descripcionEspecialidad;
    }

    public void setDescripcionEspecialidad(String descripcionEspecialidad) {
        this.descripcionEspecialidad = descripcionEspecialidad;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        EspecialidadDTO especialidadDTO = (EspecialidadDTO) o;
        if (especialidadDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), especialidadDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "EspecialidadDTO{" +
            "id=" + getId() +
            ", codigoEspecialidad='" + getCodigoEspecialidad() + "'" +
            ", nombreEspecialidad='" + getNombreEspecialidad() + "'" +
            ", descripcionEspecialidad='" + getDescripcionEspecialidad() + "'" +
            "}";
    }
}
