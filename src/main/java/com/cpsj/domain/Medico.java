package com.cpsj.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Medico.
 */
@Entity
@Table(name = "medico")
public class Medico implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "codigo_medico", nullable = false)
    private String codigoMedico;

    @NotNull
    @Column(name = "nombre_medico", nullable = false)
    private String nombreMedico;

    @Column(name = "direccion_medico")
    private String direccionMedico;

    @Column(name = "telefono_medico")
    private String telefonoMedico;

    @Column(name = "email_medico")
    private String emailMedico;

    @OneToMany(mappedBy = "medico")
    private Set<Ficha> fichas = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "medico_obrasocial",
               joinColumns = @JoinColumn(name = "medicos_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "obrasocials_id", referencedColumnName = "id"))
    private Set<ObraSocial> obrasocials = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "medico_especialidad",
               joinColumns = @JoinColumn(name = "medicos_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "especialidads_id", referencedColumnName = "id"))
    private Set<Especialidad> especialidads = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigoMedico() {
        return codigoMedico;
    }

    public Medico codigoMedico(String codigoMedico) {
        this.codigoMedico = codigoMedico;
        return this;
    }

    public void setCodigoMedico(String codigoMedico) {
        this.codigoMedico = codigoMedico;
    }

    public String getNombreMedico() {
        return nombreMedico;
    }

    public Medico nombreMedico(String nombreMedico) {
        this.nombreMedico = nombreMedico;
        return this;
    }

    public void setNombreMedico(String nombreMedico) {
        this.nombreMedico = nombreMedico;
    }

    public String getDireccionMedico() {
        return direccionMedico;
    }

    public Medico direccionMedico(String direccionMedico) {
        this.direccionMedico = direccionMedico;
        return this;
    }

    public void setDireccionMedico(String direccionMedico) {
        this.direccionMedico = direccionMedico;
    }

    public String getTelefonoMedico() {
        return telefonoMedico;
    }

    public Medico telefonoMedico(String telefonoMedico) {
        this.telefonoMedico = telefonoMedico;
        return this;
    }

    public void setTelefonoMedico(String telefonoMedico) {
        this.telefonoMedico = telefonoMedico;
    }

    public String getEmailMedico() {
        return emailMedico;
    }

    public Medico emailMedico(String emailMedico) {
        this.emailMedico = emailMedico;
        return this;
    }

    public void setEmailMedico(String emailMedico) {
        this.emailMedico = emailMedico;
    }

    public Set<Ficha> getFichas() {
        return fichas;
    }

    public Medico fichas(Set<Ficha> fichas) {
        this.fichas = fichas;
        return this;
    }

    public Medico addFicha(Ficha ficha) {
        this.fichas.add(ficha);
        ficha.setMedico(this);
        return this;
    }

    public Medico removeFicha(Ficha ficha) {
        this.fichas.remove(ficha);
        ficha.setMedico(null);
        return this;
    }

    public void setFichas(Set<Ficha> fichas) {
        this.fichas = fichas;
    }

    public Set<ObraSocial> getObrasocials() {
        return obrasocials;
    }

    public Medico obrasocials(Set<ObraSocial> obraSocials) {
        this.obrasocials = obraSocials;
        return this;
    }

    public Medico addObrasocial(ObraSocial obraSocial) {
        this.obrasocials.add(obraSocial);
        obraSocial.getMedicos().add(this);
        return this;
    }

    public Medico removeObrasocial(ObraSocial obraSocial) {
        this.obrasocials.remove(obraSocial);
        obraSocial.getMedicos().remove(this);
        return this;
    }

    public void setObrasocials(Set<ObraSocial> obraSocials) {
        this.obrasocials = obraSocials;
    }

    public Set<Especialidad> getEspecialidads() {
        return especialidads;
    }

    public Medico especialidads(Set<Especialidad> especialidads) {
        this.especialidads = especialidads;
        return this;
    }

    public Medico addEspecialidad(Especialidad especialidad) {
        this.especialidads.add(especialidad);
        especialidad.getMedicos().add(this);
        return this;
    }

    public Medico removeEspecialidad(Especialidad especialidad) {
        this.especialidads.remove(especialidad);
        especialidad.getMedicos().remove(this);
        return this;
    }

    public void setEspecialidads(Set<Especialidad> especialidads) {
        this.especialidads = especialidads;
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
        Medico medico = (Medico) o;
        if (medico.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), medico.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Medico{" +
            "id=" + getId() +
            ", codigoMedico='" + getCodigoMedico() + "'" +
            ", nombreMedico='" + getNombreMedico() + "'" +
            ", direccionMedico='" + getDireccionMedico() + "'" +
            ", telefonoMedico='" + getTelefonoMedico() + "'" +
            ", emailMedico='" + getEmailMedico() + "'" +
            "}";
    }
}
