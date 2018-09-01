package com.cpsj.domain;


import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A Paciente.
 */
@Entity
@Table(name = "paciente")
public class Paciente implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre_paciente")
    private String nombrePaciente;

    @Column(name = "apellido_paciente")
    private String apellidoPaciente;

    @Column(name = "direccion_paciente")
    private String direccionPaciente;

    @Column(name = "telefono_paciente")
    private String telefonoPaciente;

    @Column(name = "email_paciente")
    private String emailPaciente;

    @Column(name = "fecha_nac_paciente")
    private LocalDate fechaNacPaciente;

    @Column(name = "genero_paciente")
    private String generoPaciente;

    @OneToOne
    @JoinColumn(unique = true)
    private ObraSocial pacienteObraSocial;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombrePaciente() {
        return nombrePaciente;
    }

    public Paciente nombrePaciente(String nombrePaciente) {
        this.nombrePaciente = nombrePaciente;
        return this;
    }

    public void setNombrePaciente(String nombrePaciente) {
        this.nombrePaciente = nombrePaciente;
    }

    public String getApellidoPaciente() {
        return apellidoPaciente;
    }

    public Paciente apellidoPaciente(String apellidoPaciente) {
        this.apellidoPaciente = apellidoPaciente;
        return this;
    }

    public void setApellidoPaciente(String apellidoPaciente) {
        this.apellidoPaciente = apellidoPaciente;
    }

    public String getDireccionPaciente() {
        return direccionPaciente;
    }

    public Paciente direccionPaciente(String direccionPaciente) {
        this.direccionPaciente = direccionPaciente;
        return this;
    }

    public void setDireccionPaciente(String direccionPaciente) {
        this.direccionPaciente = direccionPaciente;
    }

    public String getTelefonoPaciente() {
        return telefonoPaciente;
    }

    public Paciente telefonoPaciente(String telefonoPaciente) {
        this.telefonoPaciente = telefonoPaciente;
        return this;
    }

    public void setTelefonoPaciente(String telefonoPaciente) {
        this.telefonoPaciente = telefonoPaciente;
    }

    public String getEmailPaciente() {
        return emailPaciente;
    }

    public Paciente emailPaciente(String emailPaciente) {
        this.emailPaciente = emailPaciente;
        return this;
    }

    public void setEmailPaciente(String emailPaciente) {
        this.emailPaciente = emailPaciente;
    }

    public LocalDate getFechaNacPaciente() {
        return fechaNacPaciente;
    }

    public Paciente fechaNacPaciente(LocalDate fechaNacPaciente) {
        this.fechaNacPaciente = fechaNacPaciente;
        return this;
    }

    public void setFechaNacPaciente(LocalDate fechaNacPaciente) {
        this.fechaNacPaciente = fechaNacPaciente;
    }

    public String getGeneroPaciente() {
        return generoPaciente;
    }

    public Paciente generoPaciente(String generoPaciente) {
        this.generoPaciente = generoPaciente;
        return this;
    }

    public void setGeneroPaciente(String generoPaciente) {
        this.generoPaciente = generoPaciente;
    }

    public ObraSocial getPacienteObraSocial() {
        return pacienteObraSocial;
    }

    public Paciente pacienteObraSocial(ObraSocial obraSocial) {
        this.pacienteObraSocial = obraSocial;
        return this;
    }

    public void setPacienteObraSocial(ObraSocial obraSocial) {
        this.pacienteObraSocial = obraSocial;
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
        Paciente paciente = (Paciente) o;
        if (paciente.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), paciente.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Paciente{" +
            "id=" + getId() +
            ", nombrePaciente='" + getNombrePaciente() + "'" +
            ", apellidoPaciente='" + getApellidoPaciente() + "'" +
            ", direccionPaciente='" + getDireccionPaciente() + "'" +
            ", telefonoPaciente='" + getTelefonoPaciente() + "'" +
            ", emailPaciente='" + getEmailPaciente() + "'" +
            ", fechaNacPaciente='" + getFechaNacPaciente() + "'" +
            ", generoPaciente='" + getGeneroPaciente() + "'" +
            "}";
    }
}
