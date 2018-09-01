package com.cpsj.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the MedicoObraSocial entity.
 */
public class MedicoObraSocialDTO implements Serializable {

    private Long id;

    private String idMedico;

    private String idOSocial;

    private String observaciones;

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

    public String getIdOSocial() {
        return idOSocial;
    }

    public void setIdOSocial(String idOSocial) {
        this.idOSocial = idOSocial;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MedicoObraSocialDTO medicoObraSocialDTO = (MedicoObraSocialDTO) o;
        if (medicoObraSocialDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), medicoObraSocialDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MedicoObraSocialDTO{" +
            "id=" + getId() +
            ", idMedico='" + getIdMedico() + "'" +
            ", idOSocial='" + getIdOSocial() + "'" +
            ", observaciones='" + getObservaciones() + "'" +
            "}";
    }
}
