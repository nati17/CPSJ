package com.cpsj.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the Bebida entity.
 */
public class BebidaDTO implements Serializable {

    private Long id;

    @NotNull
    private String valor;

    private Set<AntecedentesPersonalesDTO> values = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public Set<AntecedentesPersonalesDTO> getValues() {
        return values;
    }

    public void setValues(Set<AntecedentesPersonalesDTO> antecedentesPersonales) {
        this.values = antecedentesPersonales;
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