package com.cpsj.domain;


import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A PacienteTurno.
 */
@Entity
@Table(name = "paciente_turno")
public class PacienteTurno implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_paciente")
    private String idPaciente;

    @Column(name = "id_turno")
    private String idTurno;

    @Column(name = "observaciones_turno")
    private String observacionesTurno;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdPaciente() {
        return idPaciente;
    }

    public PacienteTurno idPaciente(String idPaciente) {
        this.idPaciente = idPaciente;
        return this;
    }

    public void setIdPaciente(String idPaciente) {
        this.idPaciente = idPaciente;
    }

    public String getIdTurno() {
        return idTurno;
    }

    public PacienteTurno idTurno(String idTurno) {
        this.idTurno = idTurno;
        return this;
    }

    public void setIdTurno(String idTurno) {
        this.idTurno = idTurno;
    }

    public String getObservacionesTurno() {
        return observacionesTurno;
    }

    public PacienteTurno observacionesTurno(String observacionesTurno) {
        this.observacionesTurno = observacionesTurno;
        return this;
    }

    public void setObservacionesTurno(String observacionesTurno) {
        this.observacionesTurno = observacionesTurno;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PacienteTurno pacienteTurno = (PacienteTurno) o;
        if (pacienteTurno.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), pacienteTurno.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "PacienteTurno{" +
            "id=" + getId() +
            ", idPaciente='" + getIdPaciente() + "'" +
            ", idTurno='" + getIdTurno() + "'" +
            ", observacionesTurno='" + getObservacionesTurno() + "'" +
            "}";
    }
}
