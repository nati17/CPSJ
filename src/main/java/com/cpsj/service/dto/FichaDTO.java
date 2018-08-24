package com.cpsj.service.dto;

import java.time.LocalDate;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Ficha entity.
 */
public class FichaDTO implements Serializable {

    private Long id;

    private LocalDate fechaIngreso;

    private Long medicoId;

    private String medicoNombreMedico;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(LocalDate fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public Long getMedicoId() {
        return medicoId;
    }

    public void setMedicoId(Long medicoId) {
        this.medicoId = medicoId;
    }

    public String getMedicoNombreMedico() {
        return medicoNombreMedico;
    }

    public void setMedicoNombreMedico(String medicoNombreMedico) {
        this.medicoNombreMedico = medicoNombreMedico;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        FichaDTO fichaDTO = (FichaDTO) o;
        if (fichaDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), fichaDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "FichaDTO{" +
            "id=" + getId() +
            ", fechaIngreso='" + getFechaIngreso() + "'" +
            ", medico=" + getMedicoId() +
            ", medico='" + getMedicoNombreMedico() + "'" +
            "}";
    }
}
