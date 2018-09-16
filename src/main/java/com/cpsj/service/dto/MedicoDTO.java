package com.cpsj.service.dto;

import java.io.File;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;


/**
 * A DTO for the Medico entity.
 */
public class MedicoDTO implements Serializable {

    private Long id;

    private String codigoMedico;

    private String nombreMedico;

    private String apellidoMedico;

    private String direccionMedico;

    private String telefonoMedico;

    private String emailMedico;

    private String matriculaMedico;

    private String horarioIMedico;

    private String horarioEMedico;

    private String porcentaje;

    private Set<ObraSocialDTO> obrasocials = new HashSet<>();

    private Set<EspecialidadDTO> especialidads = new HashSet<>();

    private Set<DiasDTO> dias = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigoMedico() {
        return codigoMedico;
    }

    public void setCodigoMedico(String codigoMedico) {
        this.codigoMedico = codigoMedico;
    }

    public String getNombreMedico() {
        return nombreMedico;
    }

    public void setNombreMedico(String nombreMedico) {
        this.nombreMedico = nombreMedico;
    }

    public String getApellidoMedico() {
        return apellidoMedico;
    }

    public void setApellidoMedico(String apellidoMedico) {
        this.apellidoMedico = apellidoMedico;
    }

    public String getDireccionMedico() {
        return direccionMedico;
    }

    public void setDireccionMedico(String direccionMedico) {
        this.direccionMedico = direccionMedico;
    }

    public String getTelefonoMedico() {
        return telefonoMedico;
    }

    public void setTelefonoMedico(String telefonoMedico) {
        this.telefonoMedico = telefonoMedico;
    }

    public String getEmailMedico() {
        return emailMedico;
    }

    public void setEmailMedico(String emailMedico) {
        this.emailMedico = emailMedico;
    }

    public String getMatriculaMedico() {
        return matriculaMedico;
    }

    public void setMatriculaMedico(String matriculaMedico) {
        this.matriculaMedico = matriculaMedico;
    }

    public String getHorarioIMedico() {
        return horarioIMedico;
    }

    public void setHorarioIMedico(String horarioIMedico) {
        this.horarioIMedico = horarioIMedico;
    }

    public String getHorarioEMedico() {
        return horarioEMedico;
    }

    public void setHorarioEMedico(String horarioEMedico) {
        this.horarioEMedico = horarioEMedico;
    }

    public String getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(String porcentaje) {
        this.porcentaje = porcentaje;
    }

    public Set<ObraSocialDTO> getObrasocials() {
        return obrasocials;
    }

    public void setObrasocials(Set<ObraSocialDTO> obraSocials) {
        this.obrasocials = obraSocials;
    }

    public Set<EspecialidadDTO> getEspecialidads() {
        return especialidads;
    }

    public void setEspecialidads(Set<EspecialidadDTO> especialidads) {
        this.especialidads = especialidads;
    }

    public Set<DiasDTO> getDias() {
        return dias;
    }

    public void setDias(Set<DiasDTO> dias) {
        this.dias = dias;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MedicoDTO medicoDTO = (MedicoDTO) o;
        if (medicoDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), medicoDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MedicoDTO{" +
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
