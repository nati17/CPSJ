package com.cpsj.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import com.cpsj.domain.enumeration.AlergiasEnum;

/**
 * A DTO for the Alergia entity.
 */
public class AlergiaDTO implements Serializable {

    private Long id;

    @NotNull
    private AlergiasEnum valor;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AlergiasEnum getValor() {
        return valor;
    }

    public void setValor(AlergiasEnum valor) {
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

        AlergiaDTO alergiaDTO = (AlergiaDTO) o;
        if (alergiaDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), alergiaDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "AlergiaDTO{" +
            "id=" + getId() +
            ", valor='" + getValor() + "'" +
            "}";
    }
}
