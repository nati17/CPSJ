package com.cpsj.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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

    @Column(name = "nombre_o_social")
    private String nombreOSocial;

    @Column(name = "direecion_o_social")
    private String direecionOSocial;

    @Column(name = "telefono_o_social")
    private String telefonoOSocial;

    @Column(name = "email_o_social")
    private String emailOSocial;

    @ManyToOne
    @JsonIgnoreProperties("")
    private PacienteObraSocial pacienteObraSocial;

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

    public String getNombreOSocial() {
        return nombreOSocial;
    }

    public ObraSocial nombreOSocial(String nombreOSocial) {
        this.nombreOSocial = nombreOSocial;
        return this;
    }

    public void setNombreOSocial(String nombreOSocial) {
        this.nombreOSocial = nombreOSocial;
    }

    public String getDireecionOSocial() {
        return direecionOSocial;
    }

    public ObraSocial direecionOSocial(String direecionOSocial) {
        this.direecionOSocial = direecionOSocial;
        return this;
    }

    public void setDireecionOSocial(String direecionOSocial) {
        this.direecionOSocial = direecionOSocial;
    }

    public String getTelefonoOSocial() {
        return telefonoOSocial;
    }

    public ObraSocial telefonoOSocial(String telefonoOSocial) {
        this.telefonoOSocial = telefonoOSocial;
        return this;
    }

    public void setTelefonoOSocial(String telefonoOSocial) {
        this.telefonoOSocial = telefonoOSocial;
    }

    public String getEmailOSocial() {
        return emailOSocial;
    }

    public ObraSocial emailOSocial(String emailOSocial) {
        this.emailOSocial = emailOSocial;
        return this;
    }

    public void setEmailOSocial(String emailOSocial) {
        this.emailOSocial = emailOSocial;
    }

    public PacienteObraSocial getPacienteObraSocial() {
        return pacienteObraSocial;
    }

    public ObraSocial pacienteObraSocial(PacienteObraSocial pacienteObraSocial) {
        this.pacienteObraSocial = pacienteObraSocial;
        return this;
    }

    public void setPacienteObraSocial(PacienteObraSocial pacienteObraSocial) {
        this.pacienteObraSocial = pacienteObraSocial;
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
            ", nombreOSocial='" + getNombreOSocial() + "'" +
            ", direecionOSocial='" + getDireecionOSocial() + "'" +
            ", telefonoOSocial='" + getTelefonoOSocial() + "'" +
            ", emailOSocial='" + getEmailOSocial() + "'" +
            "}";
    }
}
