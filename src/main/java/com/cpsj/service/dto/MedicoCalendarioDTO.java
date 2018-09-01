package com.cpsj.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the MedicoCalendario entity.
 */
public class MedicoCalendarioDTO implements Serializable {

    private Long id;

    private String idMedico;

    private String idCalendario;

    private Boolean estado;

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

    public String getIdCalendario() {
        return idCalendario;
    }

    public void setIdCalendario(String idCalendario) {
        this.idCalendario = idCalendario;
    }

    public Boolean isEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MedicoCalendarioDTO medicoCalendarioDTO = (MedicoCalendarioDTO) o;
        if (medicoCalendarioDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), medicoCalendarioDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MedicoCalendarioDTO{" +
            "id=" + getId() +
            ", idMedico='" + getIdMedico() + "'" +
            ", idCalendario='" + getIdCalendario() + "'" +
            ", estado='" + isEstado() + "'" +
            "}";
    }
}
