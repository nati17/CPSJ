package com.cpsj.domain;


import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A Consulta.
 */
@Entity
@Table(name = "consulta")
public class Consulta implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fecha_consulta")
    private LocalDate fechaConsulta;

    @Column(name = "observaciones_consulta")
    private String observacionesConsulta;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getFechaConsulta() {
        return fechaConsulta;
    }

    public Consulta fechaConsulta(LocalDate fechaConsulta) {
        this.fechaConsulta = fechaConsulta;
        return this;
    }

    public void setFechaConsulta(LocalDate fechaConsulta) {
        this.fechaConsulta = fechaConsulta;
    }

    public String getObservacionesConsulta() {
        return observacionesConsulta;
    }

    public Consulta observacionesConsulta(String observacionesConsulta) {
        this.observacionesConsulta = observacionesConsulta;
        return this;
    }

    public void setObservacionesConsulta(String observacionesConsulta) {
        this.observacionesConsulta = observacionesConsulta;
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
        Consulta consulta = (Consulta) o;
        if (consulta.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), consulta.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Consulta{" +
            "id=" + getId() +
            ", fechaConsulta='" + getFechaConsulta() + "'" +
            ", observacionesConsulta='" + getObservacionesConsulta() + "'" +
            "}";
    }
}
