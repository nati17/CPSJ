package com.cpsj.domain;


import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Observacion.
 */
@Entity
@Table(name = "observacion")
public class Observacion implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_observacion")
    private String idObservacion;

    @Column(name = "descripcion_observacion")
    private String descripcionObservacion;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdObservacion() {
        return idObservacion;
    }

    public Observacion idObservacion(String idObservacion) {
        this.idObservacion = idObservacion;
        return this;
    }

    public void setIdObservacion(String idObservacion) {
        this.idObservacion = idObservacion;
    }

    public String getDescripcionObservacion() {
        return descripcionObservacion;
    }

    public Observacion descripcionObservacion(String descripcionObservacion) {
        this.descripcionObservacion = descripcionObservacion;
        return this;
    }

    public void setDescripcionObservacion(String descripcionObservacion) {
        this.descripcionObservacion = descripcionObservacion;
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
        Observacion observacion = (Observacion) o;
        if (observacion.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), observacion.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Observacion{" +
            "id=" + getId() +
            ", idObservacion='" + getIdObservacion() + "'" +
            ", descripcionObservacion='" + getDescripcionObservacion() + "'" +
            "}";
    }
}
