package com.cpsj.domain;


import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A MedicoObraSocial.
 */
@Entity
@Table(name = "medico_obra_social")
public class MedicoObraSocial implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_medico")
    private String idMedico;

    @Column(name = "id_o_social")
    private String idOSocial;

    @Column(name = "observaciones")
    private String observaciones;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdMedico() {
        return idMedico;
    }

    public MedicoObraSocial idMedico(String idMedico) {
        this.idMedico = idMedico;
        return this;
    }

    public void setIdMedico(String idMedico) {
        this.idMedico = idMedico;
    }

    public String getIdOSocial() {
        return idOSocial;
    }

    public MedicoObraSocial idOSocial(String idOSocial) {
        this.idOSocial = idOSocial;
        return this;
    }

    public void setIdOSocial(String idOSocial) {
        this.idOSocial = idOSocial;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public MedicoObraSocial observaciones(String observaciones) {
        this.observaciones = observaciones;
        return this;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
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
        MedicoObraSocial medicoObraSocial = (MedicoObraSocial) o;
        if (medicoObraSocial.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), medicoObraSocial.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MedicoObraSocial{" +
            "id=" + getId() +
            ", idMedico='" + getIdMedico() + "'" +
            ", idOSocial='" + getIdOSocial() + "'" +
            ", observaciones='" + getObservaciones() + "'" +
            "}";
    }
}
