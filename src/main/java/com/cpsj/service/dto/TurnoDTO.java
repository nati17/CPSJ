package com.cpsj.service.dto;

import java.time.LocalDate;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Turno entity.
 */
public class TurnoDTO implements Serializable {

    private Long id;

    private LocalDate fechaTurno;

    private String horaTurno;

    private String duracion;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getFechaTurno() {
        return fechaTurno;
    }

    public void setFechaTurno(LocalDate fechaTurno) {
        this.fechaTurno = fechaTurno;
    }

    public String getHoraTurno() {
        return horaTurno;
    }

    public void setHoraTurno(String horaTurno) {
        this.horaTurno = horaTurno;
    }

    public String getDuracion() {
        return duracion;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        TurnoDTO turnoDTO = (TurnoDTO) o;
        if (turnoDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), turnoDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "TurnoDTO{" +
            "id=" + getId() +
            ", fechaTurno='" + getFechaTurno() + "'" +
            ", horaTurno='" + getHoraTurno() + "'" +
            ", duracion='" + getDuracion() + "'" +
            "}";
    }
}
