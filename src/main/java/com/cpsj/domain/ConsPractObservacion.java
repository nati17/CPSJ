package com.cpsj.domain;


import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A ConsPractObservacion.
 */
@Entity
@Table(name = "cons_pract_observacion")
public class ConsPractObservacion implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_cons_pract")
    private String idConsPract;

    @Column(name = "id_observacion")
    private String idObservacion;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdConsPract() {
        return idConsPract;
    }

    public ConsPractObservacion idConsPract(String idConsPract) {
        this.idConsPract = idConsPract;
        return this;
    }

    public void setIdConsPract(String idConsPract) {
        this.idConsPract = idConsPract;
    }

    public String getIdObservacion() {
        return idObservacion;
    }

    public ConsPractObservacion idObservacion(String idObservacion) {
        this.idObservacion = idObservacion;
        return this;
    }

    public void setIdObservacion(String idObservacion) {
        this.idObservacion = idObservacion;
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
        ConsPractObservacion consPractObservacion = (ConsPractObservacion) o;
        if (consPractObservacion.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), consPractObservacion.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ConsPractObservacion{" +
            "id=" + getId() +
            ", idConsPract='" + getIdConsPract() + "'" +
            ", idObservacion='" + getIdObservacion() + "'" +
            "}";
    }
}
