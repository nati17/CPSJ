package com.cpsj.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import com.cpsj.domain.enumeration.EjerciciosEnum;

/**
 * A DTO for the Ejercicio entity.
 */
public class EjercicioDTO implements Serializable {

    private Long id;

    @NotNull
    private EjerciciosEnum valor;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public EjerciciosEnum getValor() {
        return valor;
    }

    public void setValor(EjerciciosEnum valor) {
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

        EjercicioDTO ejercicioDTO = (EjercicioDTO) o;
        if (ejercicioDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), ejercicioDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "EjercicioDTO{" +
            "id=" + getId() +
            ", valor='" + getValor() + "'" +
            "}";
    }
}
