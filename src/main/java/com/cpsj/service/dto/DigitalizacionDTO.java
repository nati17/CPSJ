package com.cpsj.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Digitalizacion entity.
 */
public class DigitalizacionDTO implements Serializable {

    private Long id;

    private String idDigitalizacion;

    private String descripcionDigitalizacion;

    private String elementoDigitalizacion;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdDigitalizacion() {
        return idDigitalizacion;
    }

    public void setIdDigitalizacion(String idDigitalizacion) {
        this.idDigitalizacion = idDigitalizacion;
    }

    public String getDescripcionDigitalizacion() {
        return descripcionDigitalizacion;
    }

    public void setDescripcionDigitalizacion(String descripcionDigitalizacion) {
        this.descripcionDigitalizacion = descripcionDigitalizacion;
    }

    public String getElementoDigitalizacion() {
        return elementoDigitalizacion;
    }

    public void setElementoDigitalizacion(String elementoDigitalizacion) {
        this.elementoDigitalizacion = elementoDigitalizacion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        DigitalizacionDTO digitalizacionDTO = (DigitalizacionDTO) o;
        if (digitalizacionDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), digitalizacionDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "DigitalizacionDTO{" +
            "id=" + getId() +
            ", idDigitalizacion='" + getIdDigitalizacion() + "'" +
            ", descripcionDigitalizacion='" + getDescripcionDigitalizacion() + "'" +
            ", elementoDigitalizacion='" + getElementoDigitalizacion() + "'" +
            "}";
    }
}
