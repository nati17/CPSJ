package com.cpsj.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import com.cpsj.domain.enumeration.RegimenesEnum;

/**
 * A DTO for the Regimen entity.
 */
public class RegimenDTO implements Serializable {

    private Long id;

    @NotNull
    private RegimenesEnum valor;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RegimenesEnum getValor() {
        return valor;
    }

    public void setValor(RegimenesEnum valor) {
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

        RegimenDTO regimenDTO = (RegimenDTO) o;
        if (regimenDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), regimenDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "RegimenDTO{" +
            "id=" + getId() +
            ", valor='" + getValor() + "'" +
            "}";
    }
}
