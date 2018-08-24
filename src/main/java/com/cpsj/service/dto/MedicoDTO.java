package com.cpsj.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the Medico entity.
 */
public class MedicoDTO implements Serializable {

    private Long id;

    @NotNull
    private String codigoMedico;

    @NotNull
    private String nombreMedico;

    private String direccionMedico;

    private String telefonoMedico;

    private String emailMedico;

    private Set<ObraSocialDTO> obrasocials = new HashSet<>();

    private Set<EspecialidadDTO> especialidads = new HashSet<>();

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
            ", direccionMedico='" + getDireccionMedico() + "'" +
            ", telefonoMedico='" + getTelefonoMedico() + "'" +
            ", emailMedico='" + getEmailMedico() + "'" +
            "}";
    }
}
