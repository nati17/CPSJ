package com.cpsj.service.dto;

import java.time.LocalDate;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Paciente entity.
 */
public class PacienteDTO implements Serializable {

    private Long id;

    private String nombrePaciente;

    private String apellidoPaciente;

    private String direccionPaciente;

    private String telefonoPaciente;

    private String emailPaciente;

    private LocalDate fechaNacPaciente;

    private String generoPaciente;

    private Long pacienteObraSocialId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombrePaciente() {
        return nombrePaciente;
    }

    public void setNombrePaciente(String nombrePaciente) {
        this.nombrePaciente = nombrePaciente;
    }

    public String getApellidoPaciente() {
        return apellidoPaciente;
    }

    public void setApellidoPaciente(String apellidoPaciente) {
        this.apellidoPaciente = apellidoPaciente;
    }

    public String getDireccionPaciente() {
        return direccionPaciente;
    }

    public void setDireccionPaciente(String direccionPaciente) {
        this.direccionPaciente = direccionPaciente;
    }

    public String getTelefonoPaciente() {
        return telefonoPaciente;
    }

    public void setTelefonoPaciente(String telefonoPaciente) {
        this.telefonoPaciente = telefonoPaciente;
    }

    public String getEmailPaciente() {
        return emailPaciente;
    }

    public void setEmailPaciente(String emailPaciente) {
        this.emailPaciente = emailPaciente;
    }

    public LocalDate getFechaNacPaciente() {
        return fechaNacPaciente;
    }

    public void setFechaNacPaciente(LocalDate fechaNacPaciente) {
        this.fechaNacPaciente = fechaNacPaciente;
    }

    public String getGeneroPaciente() {
        return generoPaciente;
    }

    public void setGeneroPaciente(String generoPaciente) {
        this.generoPaciente = generoPaciente;
    }

    public Long getPacienteObraSocialId() {
        return pacienteObraSocialId;
    }

    public void setPacienteObraSocialId(Long obraSocialId) {
        this.pacienteObraSocialId = obraSocialId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        PacienteDTO pacienteDTO = (PacienteDTO) o;
        if (pacienteDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), pacienteDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "PacienteDTO{" +
            "id=" + getId() +
            ", nombrePaciente='" + getNombrePaciente() + "'" +
            ", apellidoPaciente='" + getApellidoPaciente() + "'" +
            ", direccionPaciente='" + getDireccionPaciente() + "'" +
            ", telefonoPaciente='" + getTelefonoPaciente() + "'" +
            ", emailPaciente='" + getEmailPaciente() + "'" +
            ", fechaNacPaciente='" + getFechaNacPaciente() + "'" +
            ", generoPaciente='" + getGeneroPaciente() + "'" +
            ", pacienteObraSocial=" + getPacienteObraSocialId() +
            "}";
    }
}
