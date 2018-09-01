package com.cpsj.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import com.cpsj.domain.enumeration.EnfermedadesEnum;

/**
 * A DTO for the Enfermedad entity.
 */
public class EnfermedadDTO implements Serializable {

    private Long id;

    @NotNull
    private EnfermedadesEnum valor;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public EnfermedadesEnum getValor() {
        return valor;
    }

    public void setValor(EnfermedadesEnum valor) {
        this.valor = valor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        EnfermedadDTO enfermedadDTO = (EnfermedadDTO) o;
        if (enfermedadDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), enfermedadDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "EnfermedadDTO{" +
            "id=" + getId() +
            ", valor='" + getValor() + "'" +
            "}";
    }
}
