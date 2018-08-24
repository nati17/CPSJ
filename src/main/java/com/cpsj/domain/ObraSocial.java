package com.cpsj.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A ObraSocial.
 */
@Entity
@Table(name = "obra_social")
public class ObraSocial implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "codigo_obra_social")
    private String codigoObraSocial;

    @Column(name = "nombre_obra_social")
    private String nombreObraSocial;

    @ManyToMany(mappedBy = "obrasocials")
    @JsonIgnore
    private Set<Medico> medicos = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigoObraSocial() {
        return codigoObraSocial;
    }

    public ObraSocial codigoObraSocial(String codigoObraSocial) {
        this.codigoObraSocial = codigoObraSocial;
        return this;
    }

    public void setCodigoObraSocial(String codigoObraSocial) {
        this.codigoObraSocial = codigoObraSocial;
    }

    public String getNombreObraSocial() {
        return nombreObraSocial;
    }

    public ObraSocial nombreObraSocial(String nombreObraSocial) {
        this.nombreObraSocial = nombreObraSocial;
        return this;
    }

    public void setNombreObraSocial(String nombreObraSocial) {
        this.nombreObraSocial = nombreObraSocial;
    }

    public Set<Medico> getMedicos() {
        return medicos;
    }

    public ObraSocial medicos(Set<Medico> medicos) {
        this.medicos = medicos;
        return this;
    }

    public ObraSocial addMedico(Medico medico) {
        this.medicos.add(medico);
        medico.getObrasocials().add(this);
        return this;
    }

    public ObraSocial removeMedico(Medico medico) {
        this.medicos.remove(medico);
        medico.getObrasocials().remove(this);
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
        ObraSocial obraSocial = (ObraSocial) o;
        if (obraSocial.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), obraSocial.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ObraSocial{" +
            "id=" + getId() +
            ", codigoObraSocial='" + getCodigoObraSocial() + "'" +
            ", nombreObraSocial='" + getNombreObraSocial() + "'" +
            "}";
    }
}
