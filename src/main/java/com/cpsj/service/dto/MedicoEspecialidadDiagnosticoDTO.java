package com.cpsj.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the MedicoEspecialidadDiagnostico entity.
 */
public class MedicoEspecialidadDiagnosticoDTO implements Serializable {

    private Long id;

    private String idMedico;

    private String idEspecialidad;

    private String idDiagnostico;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdMedico() {
        return idMedico;
    }

    public void setIdMedico(String idMedico) {
        this.idMedico = idMedico;
    }

    public String getIdEspecialidad() {
        return idEspecialidad;
    }

    public void setIdEspecialidad(String idEspecialidad) {
        this.idEspecialidad = idEspecialidad;
    }

    public String getIdDiagnostico() {
        return idDiagnostico;
    }

    public void setIdDiagnostico(String idDiagnostico) {
        this.idDiagnostico = idDiagnostico;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MedicoEspecialidadDiagnosticoDTO medicoEspecialidadDiagnosticoDTO = (MedicoEspecialidadDiagnosticoDTO) o;
        if (medicoEspecialidadDiagnosticoDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), medicoEspecialidadDiagnosticoDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MedicoEspecialidadDiagnosticoDTO{" +
            "id=" + getId() +
            ", idMedico='" + getIdMedico() + "'" +
            ", idEspecialidad='" + getIdEspecialidad() + "'" +
            ", idDiagnostico='" + getIdDiagnostico() + "'" +
            "}";
    }
}
