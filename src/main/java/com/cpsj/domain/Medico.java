package com.cpsj.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

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

    @Column(name = "codigo_medico")
    private String codigoMedico;

    @Column(name = "nombre_medico")
    private String nombreMedico;

    @Column(name = "apellido_medico")
    private String apellidoMedico;

    @Column(name = "direccion_medico")
    private String direccionMedico;

    @Column(name = "telefono_medico")
    private String telefonoMedico;

    @Column(name = "email_medico")
    private String emailMedico;

    @Column(name = "matricula_medico")
    private String matriculaMedico;

    @Column(name = "horario_i_medico")
    private String horarioIMedico;

    @Column(name = "horario_e_medico")
    private String horarioEMedico;

    @Column(name = "porcentaje")
    private String porcentaje;

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

    @ManyToMany
    @JoinTable(name = "medico_dias",
               joinColumns = @JoinColumn(name = "medicos_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "dias_id", referencedColumnName = "id"))
    private Set<Dias> dias = new HashSet<>();

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

    public String getApellidoMedico() {
        return apellidoMedico;
    }

    public Medico apellidoMedico(String apellidoMedico) {
        this.apellidoMedico = apellidoMedico;
        return this;
    }

    public void setApellidoMedico(String apellidoMedico) {
        this.apellidoMedico = apellidoMedico;
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

    public String getMatriculaMedico() {
        return matriculaMedico;
    }

    public Medico matriculaMedico(String matriculaMedico) {
        this.matriculaMedico = matriculaMedico;
        return this;
    }

    public void setMatriculaMedico(String matriculaMedico) {
        this.matriculaMedico = matriculaMedico;
    }

    public String getHorarioIMedico() {
        return horarioIMedico;
    }

    public Medico horarioIMedico(String horarioIMedico) {
        this.horarioIMedico = horarioIMedico;
        return this;
    }

    public void setHorarioIMedico(String horarioIMedico) {
        this.horarioIMedico = horarioIMedico;
    }

    public String getHorarioEMedico() {
        return horarioEMedico;
    }

    public Medico horarioEMedico(String horarioEMedico) {
        this.horarioEMedico = horarioEMedico;
        return this;
    }

    public void setHorarioEMedico(String horarioEMedico) {
        this.horarioEMedico = horarioEMedico;
    }

    public String getPorcentaje() {
        return porcentaje;
    }

    public Medico porcentaje(String porcentaje) {
        this.porcentaje = porcentaje;
        return this;
    }

    public void setPorcentaje(String porcentaje) {
        this.porcentaje = porcentaje;
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

    public Set<Dias> getDias() {
        return dias;
    }

    public Medico dias(Set<Dias> dias) {
        this.dias = dias;
        return this;
    }

    public Medico addDias(Dias dias) {
        this.dias.add(dias);
        return this;
    }

    public Medico removeDias(Dias dias) {
        this.dias.remove(dias);
        return this;
    }

    public void setDias(Set<Dias> dias) {
        this.dias = dias;
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
            ", apellidoMedico='" + getApellidoMedico() + "'" +
            ", direccionMedico='" + getDireccionMedico() + "'" +
            ", telefonoMedico='" + getTelefonoMedico() + "'" +
            ", emailMedico='" + getEmailMedico() + "'" +
            ", matriculaMedico='" + getMatriculaMedico() + "'" +
            ", horarioIMedico='" + getHorarioIMedico() + "'" +
            ", horarioEMedico='" + getHorarioEMedico() + "'" +
            ", porcentaje='" + getPorcentaje() + "'" +
            "}";
    }
}
