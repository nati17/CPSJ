package com.cpsj.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the PacienteTurno entity.
 */
public class PacienteTurnoDTO implements Serializable {

    private Long id;

    private String idPaciente;

    private String idTurno;

    private String observacionesTurno;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(String idPaciente) {
        this.idPaciente = idPaciente;
    }

    public String getIdTurno() {
        return idTurno;
    }

    public void setIdTurno(String idTurno) {
        this.idTurno = idTurno;
    }

    public String getObservacionesTurno() {
        return observacionesTurno;
    }

    public void setObservacionesTurno(String observacionesTurno) {
        this.observacionesTurno = observacionesTurno;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        PacienteTurnoDTO pacienteTurnoDTO = (PacienteTurnoDTO) o;
        if (pacienteTurnoDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), pacienteTurnoDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "PacienteTurnoDTO{" +
            "id=" + getId() +
            ", idPaciente='" + getIdPaciente() + "'" +
            ", idTurno='" + getIdTurno() + "'" +
            ", observacionesTurno='" + getObservacionesTurno() + "'" +
            "}";
    }
}
