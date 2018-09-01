package com.cpsj.domain;


import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A MedicoEspecialidadDiagnostico.
 */
@Entity
@Table(name = "medico_especialidad_diagnostico")
public class MedicoEspecialidadDiagnostico implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_medico")
    private String idMedico;

    @Column(name = "id_especialidad")
    private String idEspecialidad;

    @Column(name = "id_diagnostico")
    private String idDiagnostico;

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

    public MedicoEspecialidadDiagnostico idMedico(String idMedico) {
        this.idMedico = idMedico;
        return this;
    }

    public void setIdMedico(String idMedico) {
        this.idMedico = idMedico;
    }

    public String getIdEspecialidad() {
        return idEspecialidad;
    }

    public MedicoEspecialidadDiagnostico idEspecialidad(String idEspecialidad) {
        this.idEspecialidad = idEspecialidad;
        return this;
    }

    public void setIdEspecialidad(String idEspecialidad) {
        this.idEspecialidad = idEspecialidad;
    }

    public String getIdDiagnostico() {
        return idDiagnostico;
    }

    public MedicoEspecialidadDiagnostico idDiagnostico(String idDiagnostico) {
        this.idDiagnostico = idDiagnostico;
        return this;
    }

    public void setIdDiagnostico(String idDiagnostico) {
        this.idDiagnostico = idDiagnostico;
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
        MedicoEspecialidadDiagnostico medicoEspecialidadDiagnostico = (MedicoEspecialidadDiagnostico) o;
        if (medicoEspecialidadDiagnostico.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), medicoEspecialidadDiagnostico.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MedicoEspecialidadDiagnostico{" +
            "id=" + getId() +
            ", idMedico='" + getIdMedico() + "'" +
            ", idEspecialidad='" + getIdEspecialidad() + "'" +
            ", idDiagnostico='" + getIdDiagnostico() + "'" +
            "}";
    }
}
