package com.cpsj.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the ConsultaPractica entity.
 */
public class ConsultaPracticaDTO implements Serializable {

    private Long id;

    private String attribute;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAttribute() {
        return attribute;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ConsultaPracticaDTO consultaPracticaDTO = (ConsultaPracticaDTO) o;
        if (consultaPracticaDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), consultaPracticaDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ConsultaPracticaDTO{" +
            "id=" + getId() +
            ", attribute='" + getAttribute() + "'" +
            "}";
    }
}
