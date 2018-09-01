package com.cpsj.domain;


import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A MedicoCalendario.
 */
@Entity
@Table(name = "medico_calendario")
public class MedicoCalendario implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_medico")
    private String idMedico;

    @Column(name = "id_calendario")
    private String idCalendario;

    @Column(name = "estado")
    private Boolean estado;

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

    public MedicoCalendario idMedico(String idMedico) {
        this.idMedico = idMedico;
        return this;
    }

    public void setIdMedico(String idMedico) {
        this.idMedico = idMedico;
    }

    public String getIdCalendario() {
        return idCalendario;
    }

    public MedicoCalendario idCalendario(String idCalendario) {
        this.idCalendario = idCalendario;
        return this;
    }

    public void setIdCalendario(String idCalendario) {
        this.idCalendario = idCalendario;
    }

    public Boolean isEstado() {
        return estado;
    }

    public MedicoCalendario estado(Boolean estado) {
        this.estado = estado;
        return this;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
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
        MedicoCalendario medicoCalendario = (MedicoCalendario) o;
        if (medicoCalendario.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), medicoCalendario.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MedicoCalendario{" +
            "id=" + getId() +
            ", idMedico='" + getIdMedico() + "'" +
            ", idCalendario='" + getIdCalendario() + "'" +
            ", estado='" + isEstado() + "'" +
            "}";
    }
}
