package com.cpsj.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Especialidad.
 */
@Entity
@Table(name = "especialidad")
public class Especialidad implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "codigo_especialidad")
    private String codigoEspecialidad;

    @Column(name = "nombre_especialidad")
    private String nombreEspecialidad;

    @Column(name = "descripcion_especialidad")
    private String descripcionEspecialidad;

    @ManyToMany(mappedBy = "especialidads")
    @JsonIgnore
    private Set<Medico> medicos = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigoEspecialidad() {
        return codigoEspecialidad;
    }

    public Especialidad codigoEspecialidad(String codigoEspecialidad) {
        this.codigoEspecialidad = codigoEspecialidad;
        return this;
    }

    public void setCodigoEspecialidad(String codigoEspecialidad) {
        this.codigoEspecialidad = codigoEspecialidad;
    }

    public String getNombreEspecialidad() {
        return nombreEspecialidad;
    }

    public Especialidad nombreEspecialidad(String nombreEspecialidad) {
        this.nombreEspecialidad = nombreEspecialidad;
        return this;
    }

    public void setNombreEspecialidad(String nombreEspecialidad) {
        this.nombreEspecialidad = nombreEspecialidad;
    }

    public String getDescripcionEspecialidad() {
        return descripcionEspecialidad;
    }

    public Especialidad descripcionEspecialidad(String descripcionEspecialidad) {
        this.descripcionEspecialidad = descripcionEspecialidad;
        return this;
    }

    public void setDescripcionEspecialidad(String descripcionEspecialidad) {
        this.descripcionEspecialidad = descripcionEspecialidad;
    }

    public Set<Medico> getMedicos() {
        return medicos;
    }

    public Especialidad medicos(Set<Medico> medicos) {
        this.medicos = medicos;
        return this;
    }

    public Especialidad addMedico(Medico medico) {
        this.medicos.add(medico);
        medico.getEspecialidads().add(this);
        return this;
    }

    public Especialidad removeMedico(Medico medico) {
        this.medicos.remove(medico);
        medico.getEspecialidads().remove(this);
        return this;
    }

    public void setMedicos(Set<Medico> medicos) {
        this.medicos = medicos;
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
        Especialidad especialidad = (Especialidad) o;
        if (especialidad.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), especialidad.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Especialidad{" +
            "id=" + getId() +
            ", codigoEspecialidad='" + getCodigoEspecialidad() + "'" +
            ", nombreEspecialidad='" + getNombreEspecialidad() + "'" +
            ", descripcionEspecialidad='" + getDescripcionEspecialidad() + "'" +
            "}";
    }
}
