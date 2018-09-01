package com.cpsj.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import com.cpsj.domain.enumeration.DiasSemanaEnum;

/**
 * A DTO for the Dias entity.
 */
public class DiasDTO implements Serializable {

    private Long id;

    @NotNull
    private DiasSemanaEnum valor;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DiasSemanaEnum getValor() {
        return valor;
    }

    public void setValor(DiasSemanaEnum valor) {
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

        DiasDTO diasDTO = (DiasDTO) o;
        if (diasDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), diasDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "DiasDTO{" +
            "id=" + getId() +
            ", valor='" + getValor() + "'" +
            "}";
    }
}
