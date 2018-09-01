package com.cpsj.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import com.cpsj.domain.enumeration.BebidasEnum;

/**
 * A DTO for the Bebida entity.
 */
public class BebidaDTO implements Serializable {

    private Long id;

    @NotNull
    private BebidasEnum valor;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BebidasEnum getValor() {
        return valor;
    }

    public void setValor(BebidasEnum valor) {
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

        BebidaDTO bebidaDTO = (BebidaDTO) o;
        if (bebidaDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), bebidaDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "BebidaDTO{" +
            "id=" + getId() +
            ", valor='" + getValor() + "'" +
            "}";
    }
}
