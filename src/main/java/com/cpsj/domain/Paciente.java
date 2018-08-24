package com.cpsj.domain;


import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
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

    @NotNull
    @Column(name = "nombre_paciente", nullable = false)
    private String nombrePaciente;

    @NotNull
    @Column(name = "apellido_paciente", nullable = false)
    private String apellidoPaciente;

    @NotNull
    @Column(name = "documento_paciente", nullable = false)
    private String documentoPaciente;

    @Column(name = "direccion_paciente")
    private String direccionPaciente;

    @Column(name = "telefono_paciente")
    private String telefonoPaciente;

    @NotNull
    @Column(name = "email_paciente", nullable = false)
    private String emailPaciente;

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

    public String getDocumentoPaciente() {
        return documentoPaciente;
    }

    public Paciente documentoPaciente(String documentoPaciente) {
        this.documentoPaciente = documentoPaciente;
        return this;
    }

    public void setDocumentoPaciente(String documentoPaciente) {
        this.documentoPaciente = documentoPaciente;
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
            ", documentoPaciente='" + getDocumentoPaciente() + "'" +
            ", direccionPaciente='" + getDireccionPaciente() + "'" +
            ", telefonoPaciente='" + getTelefonoPaciente() + "'" +
            ", emailPaciente='" + getEmailPaciente() + "'" +
            "}";
    }
}
